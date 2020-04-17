package dining_problem.domain;

import lombok.Getter;
import lombok.Setter;
import dining_problem.strategies.ActingStrategy;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class Philosopher extends Thread {
    private String name;
    private Fork rightFork;
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

    public Philosopher(String name, Fork rightFork, Fork leftFork, ActingStrategy strategy) {
        this.name = name;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.strategy = strategy;
        this.thinking = true;
    }

    @Override
    public void run() {
        act();
    }

    void act(){
        while (thinking){

            log.info(name + " trying to get forks -" + rightFork.getId()+" and "+leftFork.getId());
            tookForks = strategy.getForks(rightFork, leftFork);
            if(tookForks) {
                useForks();

                log.info(name + " is going to return the forks -" + rightFork.getId()+" and "+leftFork.getId());
                strategy.returnForks();
                tookForks = false;
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
