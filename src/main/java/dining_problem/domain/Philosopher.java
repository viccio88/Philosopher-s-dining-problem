package dining_problem.domain;

import lombok.Getter;
import lombok.Setter;
import dining_problem.strategies.ActingStrategy;

@Setter
@Getter
public class Philosopher extends Thread {

    private String name;
    private Fork rightFork;
    private Fork leftFork;
    private boolean thinking = true;
    private boolean tookFirstFork;
    private boolean tookSecondFork;
    private boolean doneEating;
    private boolean tookForks;
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
            System.out.println(name + " trying to get forks -" + rightFork.getId()+" and "+leftFork.getId());
            tookForks = strategy.getForks(rightFork, leftFork);
            if(tookForks) {
                useForks();

                System.out.println(name + " is going to return the forks -" + rightFork.getId()+" and "+leftFork.getId());
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

        System.out.println(name + " is eating...!!!!!!!!!!!!!!");
        Thread.sleep(3000);
        this.doneEating = true;
    }






}
