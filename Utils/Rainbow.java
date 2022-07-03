// 
// Decompiled by Procyon v0.5.36
// 

package Benz.Utils;

import java.awt.Color;
import net.minecraft.client.Minecraft;

public class Rainbow
{
    public static void drawChromaString(final String string, final float f, final float g, final boolean b) {
        final Minecraft mc = Minecraft.func_71410_x();
        int xTmp = (int)f;
        for (final char textChar : string.toCharArray()) {
            final long l = (long)(System.currentTimeMillis() - (xTmp * 10 - g * 10.0f));
            final int i2 = Color.HSBtoRGB(l % 2000L / 2000.0f, 0.8f, 0.8f);
            final String tmp = String.valueOf(textChar);
            mc.field_71466_p.func_78276_b(tmp, xTmp, (int)g, i2);
            xTmp += mc.field_71466_p.func_78263_a(textChar);
        }
    }
    
    public static int getChromaStringWidth(final String string) {
        return getChromaStringWidth(null);
    }
}
