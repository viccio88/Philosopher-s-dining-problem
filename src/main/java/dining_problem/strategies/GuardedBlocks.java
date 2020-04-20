package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Thread.sleep;

@Slf4j
public class GuardedBlocks implements ActingStrategy {
    Fork rightFork;
    Fork leftFork;

    @Override
    public synchronized void returnForks() {
        rightFork.setAvailable(true);
        leftFork.setAvailable(true);
        log.info(" returning forks");

        notifyAll();
        try {
            sleep(1000);
        } catch (InterruptedException e) {}
    }

    @Override
    public synchronized boolean getForks(Fork rightFork, Fork leftFork, Philosopher philosopher) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        while (!rightFork.isAvailable() || !leftFork.isAvailable()){
            try {
                log.info(philosopher.getName()+" is going to wait..........");
                wait();
            } catch (InterruptedException ignored) {}
        }

        if(rightFork.isAvailable() && leftFork.isAvailable()){
            rightFork.setAvailable(false);
            leftFork.setAvailable(false);
            log.info(philosopher.getName()+" took forks");
            return true;
        }
        return false;
    }
}
