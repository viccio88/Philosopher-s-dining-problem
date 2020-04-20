package dining_problem.strategies;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HierarchicalStrategy implements ActingStrategy {
    private boolean tookMinFork = false;
    private boolean gaveMaxFork = false;
    private Fork minFork;
    private Fork maxFork;


    @Override
    public void returnForks() {
        log.info("returning");
        if( !maxFork.isAvailable()){
            maxFork.setAvailable(true);
            gaveMaxFork = true;
            log.info( "returned max fork "+ maxFork.getName());
        }
        if(gaveMaxFork && !minFork.isAvailable()){
            minFork.setAvailable(true);
            log.info("returned min fork "+ minFork.getName());
        }
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork, Philosopher philosopher) {
        minFork = rightFork.getId() > leftFork.getId() ? leftFork : rightFork;
        maxFork = rightFork.getId() > leftFork.getId() ? rightFork : leftFork;

        if (!tookMinFork && minFork.isAvailable()) {
            minFork.setAvailable(false);
            tookMinFork = true;
            log.info("took min fork "+ minFork.getName());
        }
        if (tookMinFork && maxFork.isAvailable()) {
            maxFork.setAvailable(false);

            gaveMaxFork = false;
            log.info("took max fork "+ maxFork.getName());
            return true;
        }
        return false;
    }
}

