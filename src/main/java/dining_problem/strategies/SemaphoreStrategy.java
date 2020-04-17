package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;

import java.util.PriorityQueue;
import java.util.Queue;

public class SemaphoreStrategy implements ActingStrategy{
    Fork rightFork;
    Fork leftFork;

   // private Queue<Philosopher> queue = new PriorityQueue<>();


    @Override
    public void returnForks() {
        rightFork.setAvailable(true);
        leftFork.setAvailable(true);

        //TODO
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;

        if(rightFork.isAvailable() && leftFork.isAvailable()){
            rightFork.setAvailable(false);
            leftFork.setAvailable(false);
            return true;
        }/*else{
            //TODO
        }
*/
        return false;
    }
}
