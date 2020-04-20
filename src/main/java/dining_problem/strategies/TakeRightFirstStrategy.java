package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TakeRightFirstStrategy implements ActingStrategy {

    boolean tookRight;
    boolean tookLeft;
    Fork rightFork;
    Fork leftFork;

    @Override
    public void returnForks() {
        if (tookRight){
            rightFork.setAvailable(true);
            tookRight = false;
            log.info("returned right fork "+ rightFork.getName());
        }
        if (tookLeft){
            leftFork.setAvailable(true);
            tookLeft = false;
            log.info("returned left fork "+ leftFork.getName());
        }
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork, Philosopher philosopher) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        if (!tookRight && rightFork.isAvailable()){
            rightFork.setAvailable(false);
            tookRight = true;
            log.info("took the right fork "+ rightFork.getName());
        }
        if (tookRight && leftFork.isAvailable()){
            leftFork.setAvailable(false);
            tookLeft = true;
            log.info("took the left fork "+ leftFork.getName());
            return true;
        }
        return false;
    }
}
