import java.util.concurrent.locks.Lock;

public class Inc extends Thread {

    Int increase;
    Lock lock;

    Inc(Int number) {
        this.increase = number;
    }

    public void run() {

        for (int i = 0; i < 1000000; i++) {
            lock.lock();
            
            int add = increase.getInt();
            add++;
            increase.setInt(add);
            
            lock.unlock();
            System.out.println(add);
        }
    }
}
