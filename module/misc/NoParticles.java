// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.misc;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import Benz.module.Category;
import Benz.module.Module;

public class NoParticles extends Module
{
    public NoParticles() {
        super("NoParticles", "Always holds down the sprint key", Category.FPSBOOST);
        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        this.setToggled(false);
    }
    
    @SubscribeEvent
    public void removeParticle(final RenderGameOverlayEvent.Text text) {
        MemoryFix.mc.gameSettings.particleSetting = 10;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        MemoryFix.mc.gameSettings.particleSetting = 100;
    }
}
