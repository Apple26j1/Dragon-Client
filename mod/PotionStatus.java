// 
// Decompiled by Procyon v0.5.36
// 

package Benz.mod;

import java.util.Iterator;
import java.util.Collection;
import net.minecraft.client.gui.FontRenderer;
import java.awt.Color;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.gui.Gui;

public class PotionStatus extends Gui
{
    private static final ResourceLocation potionInventory;
    
    public void renderPotionStatus(final int posX, final int posY) {
        final Minecraft mc = Minecraft.getMinecraft();
        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        GlStateManager.enableAlpha();
        final Collection activePotions = mc.thePlayer.getActivePotionEffects();
        if (!activePotions.isEmpty()) {
            int defaultposY = 3;
            for (final PotionEffect potionEffect : activePotions) {
                final Potion potion = Potion.potionTypes[potionEffect.getPotionID()];
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                mc.getTextureManager().bindTexture(PotionStatus.potionInventory);
                if (potion.hasStatusIcon()) {
                    final int potionName = potion.getStatusIconIndex();
                    this.drawTexturedModalRect(posX, posY + defaultposY, 0 + potionName % 8 * 18, 198 + potionName / 8 * 18, 18, 18);
                }
                final String potionName2 = I18n.format(potion.getName(), new Object[0]);
                fr.drawStringWithShadow(potionName2, (float)(posX + 20), (float)(posY + defaultposY + 1), Color.WHITE.getRGB());
                fr.drawStringWithShadow(Potion.getDurationString(potionEffect), (float)(posX + 20), (float)(posY + defaultposY + 10), Color.LIGHT_GRAY.getRGB());
                defaultposY += 20;
            }
        }
    }
    
    static {
        potionInventory = new ResourceLocation("textures/gui/container/inventory.png");
    }
}
