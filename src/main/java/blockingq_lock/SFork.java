package blockingq_lock;

import lombok.Getter;

import java.util.concurrent.locks.ReentrantLock;

@Getter
public class SFork {
    private final int id;
    protected ReentrantLock lock = new ReentrantLock(true);

    public SFork(int id) {
        this.id = id;
    }
}
