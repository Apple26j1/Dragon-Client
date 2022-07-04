// 
// Decompiled by Procyon v0.5.36
// 

package Benz.mod;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.client.gui.FontRenderer;
import Benz.ClientColor;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.MovingObjectPosition;
import Benz.Client;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.gui.Gui;

public class BlockInfo extends Gui
{
    private int slideIn;
    
    public BlockInfo() {
        this.slideIn = 40;
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final Minecraft mc = Minecraft.getMinecraft();
        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        final ScaledResolution sr = new ScaledResolution(mc);
        if (Client.instance.moduleManager.getModule("BlockInfo").isToggled() && mc.objectMouseOver != null && mc.objectMouseOver.typeOfHit == MovingObjectType.BLOCK) {
            final BlockPos pos = mc.objectMouseOver.getBlockPos();
            final IBlockState state = mc.theWorld.getBlockState(pos);
            final Block block = state.getBlock();
            if (!block.equals(Blocks.portal) && !block.equals(Blocks.end_portal)) {
                if (this.slideIn < 40) {
                    ++this.slideIn;
                }
                RenderHelper.enableGUIStandardItemLighting();
                mc.getRenderItem().renderItemAndEffectIntoGUI(new ItemStack(block), sr.getScaledWidth() / 2 - 8, this.slideIn - 27);
                RenderHelper.disableStandardItemLighting();
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.7D, 0.7D, 0.7D);
                if (this.slideIn > 0) {
                    --this.slideIn;
                }
                GlStateManager.popMatrix();
                Gui.drawRect(sr.getScaledWidth() / 2 - 50, this.slideIn, sr.getScaledWidth() / 2 + 50, 0, ClientColor.getClientBackground().getRGB());
                GlStateManager.pushMatrix();
                GlStateManager.scale(0.8D, 0.8D, 0.8D);
                this.drawCenteredString(fr, block.getLocalizedName(), (int)(sr.getScaledWidth() / 2 * 1.25f), this.slideIn - 35, -1);
                GlStateManager.popMatrix();
            }
        }
    }
}
