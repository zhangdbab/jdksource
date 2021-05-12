package lock;

import java.util.concurrent.locks.Lock;

public class MyLockTest  implements Runnable {
    private  static  int count =0 ;

    public MyLockTest(Lock lock) {
        this.lock = lock;
    }

    Lock lock=null;

    @Override
    public void run() {
        lock.lock();
        try {
            Thread.sleep(1);
            System.out.println("线程名称："+Thread.currentThread().getName());
             count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        MyLock myLock =new MyLock();

        for (int a=0;a<5;a++){
            Thread thread =new Thread(new MyLockTest(myLock),"myThread:"+a);
            thread.start();
        }






        Thread.sleep(3000);
        System.out.println(count);
    }

}
