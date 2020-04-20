package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;

public interface ActingStrategy {

    void returnForks();

    boolean getForks(Fork rightFork, Fork leftFork, Philosopher philosopher);
}
