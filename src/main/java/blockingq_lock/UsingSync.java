package blockingq_lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class UsingSync implements Runnable {
    private SFork fork1;
    private SFork fork2;

    @Override
    public void run() {
        int counter = 0;
        String name = Thread.currentThread().getName();
        while (counter<10){
            counter++;
            synchronized (fork1){
                log.info(name+" got fork1, id: "+ fork1.getId());
                synchronized (fork2){
                    log.info(name+" got fork2, id" +fork2.getId()+
                            " and eats !!!");

                }
            }
        }
    }
}
