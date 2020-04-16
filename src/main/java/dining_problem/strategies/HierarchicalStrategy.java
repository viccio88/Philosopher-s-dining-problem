package dining_problem.strategies;

import dining_problem.domain.Fork;

public class HierarchicalStrategy implements ActingStrategy {
    private boolean tookMinFork = false;
    private boolean gaveMaxFork = false;
    private Fork minFork;
    private Fork maxFork;


    @Override
    public void returnForks() {
        System.out.println("returning");
        if( !maxFork.isAvailable()){
            maxFork.setAvailable(true);
            gaveMaxFork = true;
            System.out.println( "returned max fork "+ maxFork.getName());
        }
        if(gaveMaxFork && !minFork.isAvailable()){
            minFork.setAvailable(true);
            System.out.println("returned min fork "+ minFork.getName());
        }
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork) {
        minFork = rightFork.getId() > leftFork.getId() ? leftFork : rightFork;
        maxFork = rightFork.getId() > leftFork.getId() ? rightFork : leftFork;

        if (!tookMinFork && minFork.isAvailable()) {
            minFork.setAvailable(false);
            tookMinFork = true;
            System.out.println("took min fork "+ minFork.getName());
        }
        if (tookMinFork && maxFork.isAvailable()) {
            maxFork.setAvailable(false);

            gaveMaxFork = false;
            System.out.println("took max fork "+ maxFork.getName());
            return true;
        }
        return false;
    }
}

