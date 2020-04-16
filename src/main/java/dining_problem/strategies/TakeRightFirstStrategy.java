package dining_problem.strategies;

import dining_problem.domain.Fork;

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
            System.out.println("returned right fork "+ rightFork.getName());
        }
        if (tookLeft){
            leftFork.setAvailable(true);
            tookLeft = false;
            System.out.println("returned left fork "+ leftFork.getName());
        }
    }

    @Override
    public boolean getForks(Fork rightFork, Fork leftFork) {
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        if (!tookRight && rightFork.isAvailable()){
            rightFork.setAvailable(false);
            tookRight = true;
            System.out.println("took the right fork "+ rightFork.getName());
        }
        if (tookRight && leftFork.isAvailable()){
            leftFork.setAvailable(false);
            tookLeft = true;
            System.out.println("took the left fork "+ leftFork.getName());
            return true;
        }
        return false;
    }
}
