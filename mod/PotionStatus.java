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
        final Minecraft mc = Minecraft.func_71410_x();
        final FontRenderer fr = Minecraft.func_71410_x().field_71466_p;
        GlStateManager.func_179141_d();
        final Collection activePotions = mc.field_71439_g.func_70651_bq();
        if (!activePotions.isEmpty()) {
            int defaultposY = 3;
            for (final PotionEffect potionEffect : activePotions) {
                final Potion potion = Potion.field_76425_a[potionEffect.func_76456_a()];
                GlStateManager.func_179131_c(1.0f, 1.0f, 1.0f, 1.0f);
                mc.func_110434_K().func_110577_a(PotionStatus.potionInventory);
                if (potion.func_76400_d()) {
                    final int potionName = potion.func_76392_e();
                    this.func_73729_b(posX, posY + defaultposY, 0 + potionName % 8 * 18, 198 + potionName / 8 * 18, 18, 18);
                }
                final String potionName2 = I18n.func_135052_a(potion.func_76393_a(), new Object[0]);
                fr.func_175063_a(potionName2, (float)(posX + 20), (float)(posY + defaultposY + 1), Color.WHITE.getRGB());
                fr.func_175063_a(Potion.func_76389_a(potionEffect), (float)(posX + 20), (float)(posY + defaultposY + 10), Color.LIGHT_GRAY.getRGB());
                defaultposY += 20;
            }
        }
    }
    
    static {
        potionInventory = new ResourceLocation("textures/gui/container/inventory.png");
    }
}
