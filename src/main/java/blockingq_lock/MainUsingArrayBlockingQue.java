package blockingq_lock;

import java.util.concurrent.ArrayBlockingQueue;

public class MainUsingArrayBlockingQue {
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

    ArrayBlockingQueue<SFork> qfork1;
    ArrayBlockingQueue<SFork> qfork2;
    ArrayBlockingQueue<SFork> qfork3;
    ArrayBlockingQueue<SFork> qfork4;
    ArrayBlockingQueue<SFork> qfork5;


    public static void main(String[] args) {
        MainUsingArrayBlockingQue m = new MainUsingArrayBlockingQue();
        m.forksInit();
        m.initArrBlockQue();
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

    private void initArrBlockQue(){
        qfork1 = new ArrayBlockingQueue<>(1);
        qfork1.add(fork1);
        qfork2 = new ArrayBlockingQueue<>(1);
        qfork2.add(fork2);
        qfork3 = new ArrayBlockingQueue<>(1);
        qfork3.add(fork3);
        qfork4 = new ArrayBlockingQueue<>(1);
        qfork4.add(fork4);
        qfork5 = new ArrayBlockingQueue<>(1);
        qfork5.add(fork5);
    }

    private void runnableInit(){
        runnable1 = new UsingBQ(qfork1, qfork2);
        runnable2 = new UsingBQ(qfork2, qfork3);
        runnable3 = new UsingBQ(qfork3, qfork4);
        runnable4 = new UsingBQ(qfork4, qfork5);
        runnable5 = new UsingBQ(qfork5, qfork1);
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
