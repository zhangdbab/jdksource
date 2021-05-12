package lock.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A B线程交替执行
 *
 * 调用await和signal方法都需要先获得锁，否则会抛异常。
 * 调用await方法会新建一个waitStatus为CONDITION、线程为当前线程的节点到条件队列尾部，然后当前线程会释放掉锁，并进入阻塞状态，直到该节点被移到同步队列或者被中断。该节点被移动到同步队列，并不代表该节点线程能立马获得锁，还是需要在同步队列中排队并在必要时候（前驱节点为head）调用tryAcquire方法去获取，如果获取成功则代表获得了锁。
 * 调用signal方法会将条件队列的头节点移动到同步队列。
 */
public class ThreadAlternateConditionDemo {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ConditionThread conditionThread = new ConditionThread(lock, condition, 100);
        new Thread(conditionThread, "Thread-A").start(); // 传入同一个 conditionThread
        new Thread(conditionThread, "Thread-B").start();//  传入同一个 conditionThread
    }

    static class ConditionThread implements Runnable {

        Lock lock;

        Condition condition;

        int count;

        public ConditionThread(Lock lock, Condition condition, int count) {
            this.lock = lock;
            this.condition = condition;
            this.count = count;
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                try {
                    lock.lock();
//                    调用signal方法会将条件队列的头节点移动到同步队列
                    condition.signal();
                     System.out.println(Thread.currentThread().getName() + "-> 执行");
                     Thread.sleep(3000);
//                    调用await方法会新建一个waitStatus为CONDITION、线程为当前线程的节点到条件队列尾部，然后当前线程会释放掉锁，
//                    并进入阻塞状态，直到该节点被移到同步队列或者被中断。该节点被移动到同步队列，并不代表该节点线程能立马获得锁，
//                    还是需要在同步队列中排队并在必要时候（前驱节点为head）调用tryAcquire方法去获取，如果获取成功则代表获得了锁。
                    condition.await();//当前线程进入等待状态，同时释放锁
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                     lock.unlock();
                }
            }
        }
    }

}
