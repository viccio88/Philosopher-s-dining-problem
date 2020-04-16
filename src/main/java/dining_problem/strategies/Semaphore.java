package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;

import java.util.PriorityQueue;
import java.util.Queue;

public class Semaphore implements ActingStrategy{

    private Queue<Philosopher> queue = new PriorityQueue<>();


    @Override
    public void returnForks() {
        //TODO
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork) {
        if(rightFork.isAvailable() && leftFork.isAvailable()){
            rightFork.setAvailable(false);
            leftFork.setAvailable(false);
            return true;
        }else{
            //TODO
        }

        return false;
    }
}
