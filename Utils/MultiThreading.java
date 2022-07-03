// 
// Decompiled by Procyon v0.5.36
// 

package Benz.Utils;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;

public class MultiThreading
{
    public static ExecutorService POOL;
    private static ScheduledExecutorService RUNNABLE_POOL;
    
    public static void schedule(final Runnable r, final long initialDelay, final long delay, final TimeUnit unit) {
        MultiThreading.RUNNABLE_POOL.scheduleAtFixedRate(r, initialDelay, delay, unit);
    }
    
    public static void runAsync(final Runnable runnable) {
        MultiThreading.POOL.execute(runnable);
    }
    
    public static int getTotal() {
        final ThreadPoolExecutor tpe = (ThreadPoolExecutor)MultiThreading.POOL;
        return tpe.getActiveCount();
    }
    
    static {
        MultiThreading.POOL = Executors.newCachedThreadPool(new ThreadFactory() {
            AtomicInteger counter = new AtomicInteger(0);
            
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(r, String.format("Thread %s", this.counter.incrementAndGet()));
            }
        });
        MultiThreading.RUNNABLE_POOL = Executors.newScheduledThreadPool(3, new ThreadFactory() {
            private AtomicInteger counter = new AtomicInteger(0);
            
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(r, "Thread " + this.counter.incrementAndGet());
            }
        });
    }
}
