package dining_problem.domain;

import lombok.Getter;
import lombok.Setter;
import dining_problem.strategies.ActingStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;


@Slf4j
public class Philosopher extends Thread {
    private String name;
    @Getter
    private Fork rightFork;
    @Getter
    private Fork leftFork;
    private boolean thinking;
    @Setter
    @Getter
    private boolean tookFirstFork;
    @Setter
    @Getter
    private boolean tookSecondFork;
    private boolean doneEating;
    private boolean tookForks;
    @Getter
    private ActingStrategy strategy;
    private CountDownLatch entryCountDown;

    public Philosopher(String name, Fork rightFork, Fork leftFork, ActingStrategy strategy, CountDownLatch entryCountDown) {
        this.name = name;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.strategy = strategy;
        this.thinking = true;
        this.entryCountDown = entryCountDown;
    }

    @Override
    public void run() {
        /*try {
            System.out.println(this.name + " is waiting");
            entryCountDown.await();
        } catch (InterruptedException e) {}
*/
        act();
    }

    void act(){
        while (thinking){
            log.info(name + " request -" + rightFork.getId()+" and "+leftFork.getId());
            tookForks = strategy.getForks(rightFork, leftFork, this);
            log.info("took forks - " + tookForks);
            if(tookForks) {
                doneEating = false;
                useForks();

                if(doneEating){
                    log.info(name + " is going to return the forks -" + rightFork.getId()+" and "+leftFork.getId());
                    strategy.returnForks();
                    tookForks = false;
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    private void useForks() {
        try {
            eating();
        } catch (InterruptedException e) {}
    }



    void eating() throws InterruptedException {

        log.info(name + " is eating...!!!!!!!!!!!!!!");
        Thread.sleep(3000);
        this.doneEating = true;
    }






}
