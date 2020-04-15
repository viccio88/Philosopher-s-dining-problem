package dining_problem;

public class App {
    Fork fork1 = new Fork(1,"fork1");
    Fork fork2 = new Fork(2,"fork2");
    Fork fork3 = new Fork(3,"fork3");
    Fork fork4 = new Fork(4,"fork4");
    Fork fork5 = new Fork(5,"fork5");

    Philosopher philosopher1 = new Philosopher("philosopher1", fork1, fork5);
    Philosopher philosopher2 = new Philosopher("philosopher2", fork1, fork2);
    Philosopher philosopher3 = new Philosopher("philosopher3", fork2, fork3);
    Philosopher philosopher4 = new Philosopher("philosopher4", fork3, fork4);
    Philosopher philosopher5 = new Philosopher("philosopher5", fork4, fork5);

    public static void main(String[] args) throws InterruptedException {
        App app = new App();

        app.startDiner();

    }

    private void startDiner(){
        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();
    }
}
