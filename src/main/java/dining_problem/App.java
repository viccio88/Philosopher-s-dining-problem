package dining_problem;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;
import dining_problem.strategies.StrategyFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import static java.lang.Thread.sleep;

@Slf4j
public class App {
    StrategyFactory factory = new StrategyFactory();
    CountDownLatch entryCountDown;
    Fork fork1 = new Fork(1,"fork1");
    Fork fork2 = new Fork(2,"fork2");
    Fork fork3 = new Fork(3,"fork3");
    Fork fork4 = new Fork(4,"fork4");
    Fork fork5 = new Fork(5,"fork5");

    Philosopher philosopher1;
    Philosopher philosopher2;
    Philosopher philosopher3;
    Philosopher philosopher4;
    Philosopher philosopher5;

    public static void main(String[] args) {
        App app = new App();
        //app.init("default");
        //app.init("HierarchicalStrategy");
        app.init("SemaphoreStrategy");
        //app.init("GuardedBlocks");

        log.info("Strategy - " + app.philosopher1.getStrategy().getClass());

        app.startDiner();

    }

    private void startDiner(){
        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //entryCountDown.countDown();
    }

    private void init(String className){
        entryCountDown = new CountDownLatch(1);
         philosopher1 = new Philosopher("philosopher1", fork5, fork1, factory.create(className), entryCountDown);
         philosopher2 = new Philosopher("philosopher2", fork1, fork2, factory.create(className), entryCountDown);
         philosopher3 = new Philosopher("philosopher3", fork2, fork3, factory.create(className), entryCountDown);
         philosopher4 = new Philosopher("philosopher4", fork3, fork4, factory.create(className), entryCountDown);
         philosopher5 = new Philosopher("philosopher5", fork4, fork5, factory.create(className), entryCountDown);
    }
}
