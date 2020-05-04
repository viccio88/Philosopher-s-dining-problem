package blockingq_lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public class UsingReentrantLock implements Runnable {
    private SFork fork1;
    private SFork fork2;

    @Override
    public void run() {
        int counter = 0;
        String name = Thread.currentThread().getName();
        int eatCount = 0;
        while (counter<10){
            counter++;
            try {
            fork1.getLock().lock();
            log.info(name+" got fork1, id: "+ fork1.getId());

                if (fork2.getLock().tryLock(1, TimeUnit.SECONDS)) {
                    log.info(name+" got fork2, id" +fork2.getId()+
                            " and eats !!!");
                    eatCount++;
                    fork1.getLock().unlock();
                    fork2.getLock().unlock();
                }else {
                    fork1.getLock().unlock();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(name+" eat count = "+ eatCount);
    }
}
