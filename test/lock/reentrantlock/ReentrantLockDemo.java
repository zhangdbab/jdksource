package lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo implements Runnable {
    private  static  int count =0 ;

    public ReentrantLockDemo(Lock lock) {
        this.lock = lock;
    }

    Lock lock=null;

    @Override
    public void run() {
        lock.lock();
        try {
            Thread.sleep(1);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock reentrantLock =new ReentrantLock(false);

        for (int a=0;a<1000;a++){
                Thread thread =new Thread(new ReentrantLockDemo(reentrantLock));
                thread.start();
        }

        Thread.sleep(3000);
        System.out.println(count);
    }
}
