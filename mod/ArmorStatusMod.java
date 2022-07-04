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
            GlStateManager.pushMatrix();
            RenderHelper.enableGUIStandardItemLighting();
            Minecraft.getMinecraft().getRenderItem().renderItemAndEffectIntoGUI(itemStack, sr.getScaledWidth() / 2 + 20 + posXAdd, sr.getScaledHeight() - 55);
            GlStateManager.popMatrix();
        }
    }
}
