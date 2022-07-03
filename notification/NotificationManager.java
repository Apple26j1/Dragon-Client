// 
// Decompiled by Procyon v0.5.36
// 

package Benz.notification;

import java.util.concurrent.LinkedBlockingQueue;

public class NotificationManager
{
    private static LinkedBlockingQueue pendingNotifications;
    private static Notification currentNotification;
    
    public static void show(final Notification notification) {
        NotificationManager.pendingNotifications.add(notification);
    }
    
    public static void update() {
        if (NotificationManager.currentNotification != null && !NotificationManager.currentNotification.isShown()) {
            NotificationManager.currentNotification = null;
        }
        if (NotificationManager.currentNotification == null && !NotificationManager.pendingNotifications.isEmpty()) {
            (NotificationManager.currentNotification = NotificationManager.pendingNotifications.poll()).show();
        }
    }
    
    public static void render() {
        update();
        if (NotificationManager.currentNotification != null) {
            NotificationManager.currentNotification.render();
        }
    }
    
    static {
        NotificationManager.pendingNotifications = new LinkedBlockingQueue();
        NotificationManager.currentNotification = null;
    }
}
