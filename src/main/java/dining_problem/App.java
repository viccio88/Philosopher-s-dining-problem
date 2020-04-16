package dining_problem;

import dining_problem.domain.Fork;
import dining_problem.domain.Philosopher;
import dining_problem.strategies.StrategyFactory;

public class App {
    StrategyFactory factory = new StrategyFactory();
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
        app.init("HierarchicalStrategy");

        app.startDiner();

    }

    private void startDiner(){
        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
    }

    private void init(String className){
         philosopher1 = new Philosopher("philosopher1", fork1, fork5, factory.create(className));
         philosopher2 = new Philosopher("philosopher2", fork2, fork1, factory.create(className));
         philosopher3 = new Philosopher("philosopher3", fork3, fork2, factory.create(className));
         philosopher4 = new Philosopher("philosopher4", fork4, fork3, factory.create(className));
         philosopher5 = new Philosopher("philosopher5", fork5, fork4, factory.create(className));
    }
}
