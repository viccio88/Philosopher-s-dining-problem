package dining_problem.strategies;

import dining_problem.domain.Fork;

public interface ActingStrategy {

    void returnForks();

    boolean getForks(Fork rightFork, Fork leftFork);
}
