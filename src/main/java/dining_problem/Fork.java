package dining_problem;

public class Fork {
    private int id;
    private String name;
    private boolean available = true;
    private Philosopher whoTookLast;

    public Fork(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Philosopher getWhoTook() {
        return whoTookLast;
    }

    public synchronized void setWhoTook(Philosopher whoTook) {
        this.whoTookLast = whoTook;
    }



    public boolean isAvailable() {
        return available;
    }

    public synchronized void setAvailable(boolean available) {
        this.available = available;
    }


}
