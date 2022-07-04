// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import java.util.Collection;
import java.awt.Color;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import Benz.settings.Setting;
import Benz.Client;
import Benz.module.Category;
import net.minecraft.util.ResourceLocation;
import Benz.module.Module;

public class PotionStatus extends Module
{
    int PotionStatusX;
    int PotionStatusY;
    protected float zLevel;
    private static final ResourceLocation potionInventory;
    
    public PotionStatus() {
        super("PotionStatus", "RenderPotionStatus", Category.PVP);
        this.PotionStatusX = 0;
        this.PotionStatusY = 0;
        Client.instance.settingsManager.rSetting(new Setting("PotionStatus: X", this, 0.0, 0.0, 900.0, false));
        Client.instance.settingsManager.rSetting(new Setting("PotionStatus: Y", this, 0.0, 0.0, 550.0, false));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        this.PotionStatusX = (int)Client.instance.settingsManager.getSettingByName("PotionStatus: X").getValDouble() + 2;
        this.PotionStatusY = (int)Client.instance.settingsManager.getSettingByName("PotionStatus: Y").getValDouble() + 2;
        GlStateManager.enableAlpha();
        final Collection activePotions = PotionStatus.mc.field_71439_g.func_70651_bq();
        if (!activePotions.isEmpty()) {
            int defaultposY = 3;
            for (final PotionEffect potionEffect : activePotions) {
                final Potion potion = Potion.field_76425_a[potionEffect.func_76456_a()];
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                PotionStatus.mc.getTextureManager().bindTexture(PotionStatus.potionInventory);
                if (potion.hasStatusIcon()) {
                    final int potionName = potion.getStatusIconIndex();
                    this.drawTexturedModalRect(this.PotionStatusX, this.PotionStatusY + defaultposY, 0 + potionName % 8 * 18, 198 + potionName / 8 * 18, 18, 18);
                }
                final String potionName2 = I18n.format(potion.getName(), new Object[0]);
                PotionStatus.fr.drawStringWithShadow(potionName2, (float)(this.PotionStatusX + 20), (float)(this.PotionStatusY + defaultposY + 1), Color.WHITE.getRGB());
                PotionStatus.fr.drawStringWithShadow(Potion.getDurationString(potionEffect), (float)(this.PotionStatusX + 20), (float)(this.PotionStatusY + defaultposY + 10), Color.LIGHT_GRAY.getRGB());
                defaultposY += 20;
            }
        }
    }
    
    public void drawTexturedModalRect(final int x, final int y, final int textureX, final int textureY, final int width, final int height) {
        final float f = 0.00390625f;
        final float f2 = 0.00390625f;
        final Tessellator tessellator = Tessellator.func_178181_a();
        final WorldRenderer worldrenderer = tessellator.func_178180_c();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex((double)((textureX + 0) * f), (double)((textureY + height) * f2)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex((double)((textureX + width) * f), (double)((textureY + height) * f2)).endVertex();
        worldrenderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex((double)((textureX + width) * f), (double)((textureY + 0) * f2)).endVertex();
        worldrenderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex((double)((textureX + 0) * f), (double)((textureY + 0) * f2)).endVertex();
        tessellator.func_78381_a();
    }
    
    static {
        potionInventory = new ResourceLocation("textures/gui/container/inventory.png");
    }
}
