package blockingq_lock;

public class Main {
    SFork fork1;
    SFork fork2;
    SFork fork3;
    SFork fork4;
    SFork fork5;
    Runnable runnable1;
    Runnable runnable2;
    Runnable runnable3;
    Runnable runnable4;
    Runnable runnable5;

    Thread philosopher1;
    Thread philosopher2;
    Thread philosopher3;
    Thread philosopher4;
    Thread philosopher5;

    public static void main(String[] args) {
        Main m = new Main();
        m.forksInit();
        m.runnableInit();
        m.philosophersInit();
        m.start();

    }

    private void forksInit(){
        fork1 = new SFork(1);
        fork2 = new SFork(2);
        fork3 = new SFork(3);
        fork4 = new SFork(4);
        fork5 = new SFork(5);
    }

    private void runnableInit(){
        runnable1 = new UsingReentrantLock(fork1, fork2);
        runnable2 = new UsingReentrantLock(fork2, fork3);
        runnable3 = new UsingReentrantLock(fork3, fork4);
        runnable4 = new UsingReentrantLock(fork4, fork5);
        runnable5 = new UsingReentrantLock(fork5, fork1);
    }

    private void philosophersInit(){
        philosopher1 = new Thread(runnable1, "philosopher1");
        philosopher2 = new Thread(runnable2, "philosopher2");
        philosopher3 = new Thread(runnable3, "philosopher3");
        philosopher4 = new Thread(runnable4, "philosopher4");
        philosopher5 = new Thread(runnable5, "philosopher5");
    }

    private void start(){
        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
    }

}
