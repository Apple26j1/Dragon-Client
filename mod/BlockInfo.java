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
        final Minecraft mc = Minecraft.func_71410_x();
        final FontRenderer fr = Minecraft.func_71410_x().field_71466_p;
        final ScaledResolution sr = new ScaledResolution(mc);
        if (Client.instance.moduleManager.getModule("BlockInfo").isToggled() && mc.field_71476_x != null && mc.field_71476_x.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            final BlockPos pos = mc.field_71476_x.func_178782_a();
            final IBlockState state = mc.field_71441_e.func_180495_p(pos);
            final Block block = state.func_177230_c();
            if (!block.equals(Blocks.field_150427_aO) && !block.equals(Blocks.field_150384_bq)) {
                if (this.slideIn < 40) {
                    ++this.slideIn;
                }
                RenderHelper.func_74520_c();
                mc.func_175599_af().func_180450_b(new ItemStack(block), sr.func_78326_a() / 2 - 8, this.slideIn - 27);
                RenderHelper.func_74518_a();
                GlStateManager.func_179094_E();
                GlStateManager.func_179139_a(0.7, 0.7, 0.7);
                if (this.slideIn > 0) {
                    --this.slideIn;
                }
                GlStateManager.func_179121_F();
                Gui.func_73734_a(sr.func_78326_a() / 2 - 50, this.slideIn, sr.func_78326_a() / 2 + 50, 0, ClientColor.getClientBackground().getRGB());
                GlStateManager.func_179094_E();
                GlStateManager.func_179139_a(0.8, 0.8, 0.8);
                this.func_73732_a(fr, block.func_149732_F(), (int)(sr.func_78326_a() / 2 * 1.25f), this.slideIn - 35, -1);
                GlStateManager.func_179121_F();
            }
        }
    }
}
