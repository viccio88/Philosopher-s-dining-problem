package blockingq_lock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
@AllArgsConstructor
public class UsingBQ implements Runnable{
    private ArrayBlockingQueue<SFork> qfork1;
    private ArrayBlockingQueue<SFork> qfork2;


    @Override
    public void run() {
        int counter = 0;
        String name = Thread.currentThread().getName();
        int eatCount = 0;
        while (counter<10){
            counter++;
            try {
                SFork fork1 = qfork1.take();
                log.info(name+" got fork1, id: "+ fork1.getId());

                SFork fork2 = qfork2.poll(1, TimeUnit.SECONDS);
                if(Objects.isNull(fork2)){
                    qfork1.add(fork1);
                    Thread.sleep(100);
                    continue;
                }
                log.info(name+" got fork2, id" +fork2.getId()+
                            " and eats !!!");
                eatCount++;
                qfork1.add(fork1);
                qfork2.add(fork2);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(name+" eat count = "+ eatCount);
    }
}
