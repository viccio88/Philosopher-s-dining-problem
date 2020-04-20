package dining_problem.strategies;

import dining_problem.domain.Fork;

public class LocalSemaphore {
    public static synchronized boolean getResources(Fork rightFork, Fork leftFork) {
        if(rightFork.isAvailable() && leftFork.isAvailable()) {
            rightFork.setAvailable(false);
            leftFork.setAvailable(false);
            return true;
        }
        return false;
    }
    private LocalSemaphore(){}
}
