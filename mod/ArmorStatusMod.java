// 
// Decompiled by Procyon v0.5.36
// 

package Benz.mod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.client.gui.ScaledResolution;

public class ArmorStatusMod
{
    public static void renderArmorStatus(final ScaledResolution sr, final int pos, final ItemStack itemStack) {
        if (itemStack != null) {
            final boolean posX = false;
            final boolean posY = false;
            final int posXAdd = -16 * pos + 48;
            GlStateManager.func_179094_E();
            RenderHelper.func_74520_c();
            Minecraft.func_71410_x().func_175599_af().func_180450_b(itemStack, sr.func_78326_a() / 2 + 20 + posXAdd, sr.func_78328_b() - 55);
            GlStateManager.func_179121_F();
        }
    }
}
