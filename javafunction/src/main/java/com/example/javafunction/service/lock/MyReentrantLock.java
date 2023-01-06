package com.example.javafunction.service.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Author wangjiaxing
 * @Date 2021/11/3
 */
public class MyReentrantLock implements Lock, Serializable {

    private final Syn syn;

    public MyReentrantLock() {
        syn = new NoFairSyn();
    }

    public MyReentrantLock(boolean fair) {
        syn = fair?new FairSyn():new NoFairSyn();
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
        return syn.nonFairTryAcquire(1);
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
        protected boolean tryRelease(int arg) {
            return super.tryRelease(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread()==Thread.currentThread();
        }

        protected boolean nonFairTryAcquire(int args) {
            int state = getState();
            if(state==0) {
                if (compareAndSetState(0,1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }else if (getExclusiveOwnerThread()==Thread.currentThread()) {
                int nextState = state + args;
                if(nextState<0) {
                    throw new Error("Maximum lock count exceeded");
                }
                setState(nextState);
                return true;
            }
            return false;
        }

        public Condition newCondition() {
            return new ConditionObject();
        }
    }

    class  FairSyn extends  Syn{

    }

    class NoFairSyn extends Syn {

    }


}
