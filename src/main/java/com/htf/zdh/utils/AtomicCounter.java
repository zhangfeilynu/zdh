package com.htf.zdh.utils;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * @author zhangfei
 * @version 1.0.0
 * @className AtomicCounter.java
 * @description TODO
 * @createTime 2020/12/14 14:25
 */
public class AtomicCounter {

    private static final AtomicCounter atomicCounter = new AtomicCounter();

    /**
     * 单例，不允许外界主动实例化
     */
    private AtomicCounter() {

    }

    public static AtomicCounter getInstance() {
        return atomicCounter;
    }

    private static AtomicInteger counter = new AtomicInteger();

    public int getValue() {
        return counter.get();
    }

    public int increase() {
        return counter.incrementAndGet();
    }

    public int decrease() {
        return counter.decrementAndGet();
    }
}
