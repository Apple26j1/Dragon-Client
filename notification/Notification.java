// 
// Decompiled by Procyon v0.5.36
// 

package Benz.notification;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.gui.FontRenderer;
import Benz.ClientColor;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;

public class Notification
{
    private NotificationType type;
    private String title;
    private String messsage;
    private long start;
    private long fadedIn;
    private long fadeOut;
    private long end;
    
    public Notification(final NotificationType type, final String title, final String messsage, final int length) {
        this.type = type;
        this.title = title;
        this.messsage = messsage;
        this.fadedIn = 200 * length;
        this.fadeOut = this.fadedIn + 500 * length;
        this.end = this.fadeOut + this.fadedIn;
    }
    
    public void show() {
        this.start = System.currentTimeMillis();
    }
    
    public boolean isShown() {
        return this.getTime() <= this.end;
    }
    
    private long getTime() {
        return System.currentTimeMillis() - this.start;
    }
    
    public void render() {
        final Minecraft mc = Minecraft.func_71410_x();
        final ScaledResolution sr = new ScaledResolution(mc);
        double offset = 0.0;
        final byte width = 120;
        final byte height = 30;
        final long time = this.getTime();
        if (time < this.fadedIn) {
            offset = Math.tanh(time / (double)this.fadedIn * 3.0) * width;
        }
        else if (time > this.fadeOut) {
            offset = Math.tanh(3.0 - (time - this.fadeOut) / (double)(this.end - this.fadeOut) * 3.0) * width;
        }
        else {
            offset = width;
        }
        final FontRenderer fontRenderer = Minecraft.func_71410_x().field_71466_p;
        drawRect(sr.func_78326_a() - offset, sr.func_78328_b() - 5 - height, sr.func_78326_a(), sr.func_78328_b() - 5, ClientColor.getClientBackground().getRGB());
        drawRect(sr.func_78326_a() - offset, sr.func_78328_b() - 5 - height, sr.func_78326_a() - offset + 4.0, sr.func_78328_b() - 5, ClientColor.getClientColorNormal().getRGB());
        fontRenderer.func_78276_b(this.title, (int)(sr.func_78326_a() - offset + 8.0), sr.func_78328_b() - 2 - height, -1);
        fontRenderer.func_78276_b(this.messsage, (int)(sr.func_78326_a() - offset + 8.0), sr.func_78328_b() - 15, -1);
    }
    
    public static void drawRect(double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double f3 = left;
            left = right;
            right = f3;
        }
        if (top < bottom) {
            final double f3 = top;
            top = bottom;
            bottom = f3;
        }
        final float f4 = (color >> 24 & 0xFF) / 255.0f;
        final float f5 = (color >> 16 & 0xFF) / 255.0f;
        final float f6 = (color >> 8 & 0xFF) / 255.0f;
        final float f7 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179131_c(f5, f6, f7, f4);
        worldrenderer.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(left, bottom, 0.0).func_181675_d();
        worldrenderer.func_181662_b(right, bottom, 0.0).func_181675_d();
        worldrenderer.func_181662_b(right, top, 0.0).func_181675_d();
        worldrenderer.func_181662_b(left, top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
    
    public static void drawRect(final int mode, double left, double top, double right, double bottom, final int color) {
        if (left < right) {
            final double f3 = left;
            left = right;
            right = f3;
        }
        if (top < bottom) {
            final double f3 = top;
            top = bottom;
            bottom = f3;
        }
        final float f4 = (color >> 24 & 0xFF) / 255.0f;
        final float f5 = (color >> 16 & 0xFF) / 255.0f;
        final float f6 = (color >> 8 & 0xFF) / 255.0f;
        final float f7 = (color & 0xFF) / 255.0f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        GlStateManager.func_179147_l();
        GlStateManager.func_179090_x();
        GlStateManager.func_179120_a(770, 771, 1, 0);
        GlStateManager.func_179131_c(f5, f6, f7, f4);
        worldrenderer.func_181668_a(mode, DefaultVertexFormats.field_181705_e);
        worldrenderer.func_181662_b(left, bottom, 0.0).func_181675_d();
        worldrenderer.func_181662_b(right, bottom, 0.0).func_181675_d();
        worldrenderer.func_181662_b(right, top, 0.0).func_181675_d();
        worldrenderer.func_181662_b(left, top, 0.0).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179098_w();
        GlStateManager.func_179084_k();
    }
}
