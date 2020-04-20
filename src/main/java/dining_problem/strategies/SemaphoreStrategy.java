package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;

public class SemaphoreStrategy implements ActingStrategy{
    Fork rightFork;
    Fork leftFork;

    @Override
    public void returnForks() {
        rightFork.setAvailable(true);
        leftFork.setAvailable(true);
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork, Philosopher philosopher) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;

        return LocalSemaphore.getResources(rightFork, leftFork);
    }
}
