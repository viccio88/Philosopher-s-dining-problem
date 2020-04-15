package dining_problem;

import semaphore.Semaphore;

public class Philosopher extends Thread {

    private String name;
    private Fork rightFork;
    private Fork leftFork;
    boolean thinking = true;

    public Philosopher(String name, Fork rightFork, Fork leftFork) {
        this.name = name;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }
    public Fork getRightFork() {
        return rightFork;
    }

    public Fork getLeftFork() {
        return leftFork;
    }


    @Override
    public void run() {
        //noStrategy();
        //askWaiter();
        hierarchy();
    }

    private void askWaiter() {
        try {
            while (thinking) {
                if (waiterConfirms()) {
                    takeOneFork(rightFork);
                    takeOneFork(leftFork);
                    thinking = false;

                    System.out.println(name + " is eating...!!!!!!!!!!!!!!");
                    Thread.sleep(3000);

                    putForksOnTable(true);
                } else {
                System.out.println(name + " is thinking...");
            }
            Thread.sleep(3000);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private boolean waiterConfirms() {
        Semaphore semaphore = new Semaphore();
        return semaphore.isGreen(this);
    }

    private void noStrategy() {
        try {
            boolean tookRightFork = false;
            while (thinking) {
                if (rightFork.isAvailable()) {
                    takeOneFork(rightFork);
                    tookRightFork = true;
                }
                if (tookRightFork && leftFork.isAvailable()) {
                    takeOneFork(leftFork);
                    thinking = false;

                    System.out.println(name + " is eating...!!!!!!!!!!!!!!");
                    Thread.sleep(3000);

                    putForksOnTable(true);
                }
                Thread.sleep(3000);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void hierarchy() {
        try {
            Fork minFork = rightFork.getId() > leftFork.getId() ? leftFork : rightFork;
            Fork maxFork = rightFork.getId() > leftFork.getId() ? rightFork : leftFork;
            boolean tookMinFork = false;
            while (thinking) {
                if (!tookMinFork && minFork.isAvailable()) {
                    takeOneFork(minFork);
                    tookMinFork = true;
                }
                if (tookMinFork && maxFork.isAvailable()) {
                    takeOneFork(maxFork);
                    thinking = false;

                    System.out.println(name + " is eating...!!!!!!!!!!!!!!");
                    Thread.sleep(3000);

                    putOneFork(maxFork);
                    putOneFork(minFork);
                    tookMinFork = false;
                    }

                Thread.sleep(3000);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void takeOneFork(Fork fork) {
        fork.setAvailable(false);
        fork.setWhoTook(this);
        System.out.println(this.name + " took " + fork.getName());
    }

    private void putOneFork(Fork fork) {
        fork.setAvailable(true);
        System.out.println(this.name + " put on table the " + rightFork.getName());
        thinking = true;
    }


    private void putForksOnTable(boolean forksAvailability) {
        thinking = forksAvailability;
        rightFork.setAvailable(forksAvailability);
        System.out.println(this.name + " put on table the " + rightFork.getName());
        leftFork.setAvailable(forksAvailability);
        System.out.println(this.name + " put on table the " + leftFork.getName());
    }
}
