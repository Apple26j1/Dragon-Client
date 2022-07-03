// 
// Decompiled by Procyon v0.5.36
// 

package Benz;

import java.awt.Color;

public class ClientColor
{
    public static Color getClientColorNormal() {
        return new Color((int)Client.instance.settingsManager.getSettingByName("Red").getValDouble(), (int)Client.instance.settingsManager.getSettingByName("Green").getValDouble(), (int)Client.instance.settingsManager.getSettingByName("Blue").getValDouble(), 170);
    }
    
    public static Color getClientColorText() {
        return new Color((int)Client.instance.settingsManager.getSettingByName("Red").getValDouble(), (int)Client.instance.settingsManager.getSettingByName("Green").getValDouble(), (int)Client.instance.settingsManager.getSettingByName("Blue").getValDouble());
    }
    
    public static Color getClientBackground() {
        return new Color(0, 0, 0, 150);
    }
    
    public static int getRainbow(final float secounds, final float saturation, final float brightness, final long index) {
        final float hue = (System.currentTimeMillis() + index) % (int)(secounds * 1000.0f) / (secounds * 1000.0f);
        final int color = Color.HSBtoRGB(hue, saturation, brightness);
        return color;
    }
}
