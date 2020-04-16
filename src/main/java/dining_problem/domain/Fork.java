package dining_problem.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Fork {
    private int id;
    private String name;
    private boolean available = true;
    private Philosopher whoTookLast;

    public Fork(int id, String name){
        this.id = id;
        this.name = name;
    }

    public synchronized void setWhoTook(Philosopher whoTook) {
        this.whoTookLast = whoTook;
    }

    public synchronized void setAvailable(boolean available) {
        this.available = available;
    }

}
