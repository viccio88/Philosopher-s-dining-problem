package semaphore;

import dining_problem.Philosopher;

public class Semaphore {

    public boolean isGreen(Philosopher philosopher) {
        if(philosopher.getRightFork().isAvailable() && philosopher.getRightFork().getWhoTook()!=philosopher
                && philosopher.getLeftFork().isAvailable() ){
            return true;
        }
        return false;
    }
}
