package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private Helper helper = new Helper();


     private class Helper extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {

            /*
             * 基于AQS手写非公平可重入锁
             * 主要实现tryAcquire方法和tryRelease方法
             *对于Lock接口的方法，进行委托，交给AQS中的方法即可
             * */
            int state = getState();
            Thread t = Thread.currentThread();

            /*
             * 当前锁是否被获取
             * 		否，直接CAS拿走
             * 		是，判断当前线程是否为重入线程
             * 			是，count++，拿走
             * 			否，返回false，交给AQS的acquire处置
             * */
            if (state == 0) {
                if (compareAndSetState(0, arg)) {
                    setExclusiveOwnerThread(t);
                    return true;
                }
            } else if (getExclusiveOwnerThread() == t) {
                setState(state + 1);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {

            // 锁的获取和释放肯定是一一对应的，那么调用此方法的线程一定是当前线程

            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new RuntimeException();
            }

            int state = getState() - arg;

            boolean flag = false;

            if (state == 0) {
                setExclusiveOwnerThread(null);
                flag = true;
            }

            setState(state);

            return flag;
        }

        Condition newCondition() {
            return new ConditionObject();
        }

    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newCondition();
    }

}
