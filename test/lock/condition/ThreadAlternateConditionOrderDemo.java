package lock.condition;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替顺序执行
 */
public class ThreadAlternateConditionOrderDemo {

    private static LinkedBlockingQueue<String> queue = new LinkedBlockingQueue();

    static {
        try {
            for (int i = 0; i < 100; i++) {
                queue.put("A");
                queue.put("B");
                queue.put("C");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();// 默认非公平
        Condition condition = lock.newCondition();
        ConditionThread conditionThreadA = new ConditionThread(lock, condition, "A");
        ConditionThread conditionThreadB = new ConditionThread(lock, condition, "B");
        ConditionThread conditionThreadC = new ConditionThread(lock, condition, "C");
        new Thread(conditionThreadA, "Thread-A").start();
        new Thread(conditionThreadB, "Thread-B").start();
        new Thread(conditionThreadC, "Thread-C").start();

    }

    static class ConditionThread implements Runnable {

        Lock lock;

        Condition condition;

        String threadFlag;

        public ConditionThread(Lock lock, Condition condition, String threadFlag) {
            this.lock = lock;
            this.condition = condition;
            this.threadFlag = threadFlag;
        }

        @Override
        public void run() {
            while (queue.peek() != null) {
                try {
                    lock.lock();
                    condition.signal();
                    String element = queue.peek();// 拿到阻塞队列头部的元素的 flag
                    if (null != element && element.equals(threadFlag)) {// 如果和当前线程的 flag 相同，则执行线程
                        queue.poll();// 删除这个头部的元素
                        System.out.println(Thread.currentThread().getName() + "-> 执行");
                        atomicInteger.incrementAndGet();
                        System.out.println(atomicInteger.get());
                        condition.await();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
