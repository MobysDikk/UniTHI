import java.util.concurrent.locks.Lock;

public class Dec extends Thread {

    Int decrease;
    Lock lock;
    Dec(Int number) {
        this.decrease = number;
    }

    public void run() {

        for (int i = 0; i < 1000000; i++) {
            lock.lock();
            int add = decrease.getInt();
            add--;
            decrease.setInt(add);
            lock.unlock();
            System.out.println(add);
        }
    }

}
