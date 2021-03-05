package com.tf56.cps.action.lock;


import com.tf56.oms.action.lock.Locker;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * created by yeliming on 2020/8/1 10:39 <br/>
 */
@Service
public class LocalLocker implements Locker {

    private ConcurrentHashMap<String, Semaphore> locker = new ConcurrentHashMap<>();

    @Override
    public boolean lock(String lockKey) {
        Semaphore newLocker = new Semaphore(1);
        Semaphore semaphore = this.locker.putIfAbsent(lockKey, newLocker);
        Semaphore semaphoreNotNull = Optional.ofNullable(semaphore).orElse(newLocker);
        return this.lock(semaphoreNotNull, 0, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryLock(String lockKey) {
        Semaphore newLocker = new Semaphore(1);
        Semaphore semaphore = this.locker.putIfAbsent(lockKey, newLocker);
        Semaphore semaphoreNotNull = Optional.ofNullable(semaphore).orElse(newLocker);
        return this.lock(semaphoreNotNull, 5, TimeUnit.SECONDS);
    }

    private boolean lock(Semaphore semaphore, long timeout, TimeUnit timeUnit) {
        boolean locked;
        try {
            locked = semaphore.tryAcquire(1, timeout, timeUnit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            locked = false;
        }
        return locked;
    }

    @Override
    public void release(String lockKey) {
        Semaphore semaphore = this.locker.remove(lockKey);
        if (semaphore != null) {
            semaphore.release();
        }
    }
}
