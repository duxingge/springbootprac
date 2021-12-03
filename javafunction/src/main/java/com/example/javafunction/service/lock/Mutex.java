package com.example.javafunction.service.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 互斥锁实现
 * @Author wangjiaxing
 * @Date 2021/10/13
 */
public class Mutex implements Lock, Serializable {

    private Syn syn;

    public Mutex() {
        this.syn = new Syn();
    }

    @Override
    public void lock() {
        syn.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        syn.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return syn.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return syn.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public void unlock() {
        syn.release(1);
    }

    @Override
    public Condition newCondition() {
        return syn.newCondition();
    }


    class Syn extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if(compareAndSetState(0,1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (getState()==0 || !isHeldExclusively()) {
                throw new IllegalMonitorStateException();
            }
            setState(0);
            setExclusiveOwnerThread(null);
            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread()==Thread.currentThread();
        }

        final Condition newCondition() {
            return new ConditionObject();
        }
    }
}
