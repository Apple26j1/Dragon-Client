// 
// Decompiled by Procyon v0.5.36
// 

package Benz.Utils;

public class TimeUtil
{
    private long lastMS;
    
    public TimeUtil() {
        this.lastMS = this.getCurrentMS();
    }
    
    public long getLastMS() {
        return this.lastMS;
    }
    
    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }
    
    public boolean completed(final long milliseconds) {
        return this.getCurrentMS() - this.lastMS >= milliseconds;
    }
    
    public void reset() {
        this.lastMS = this.getCurrentMS();
    }
}
