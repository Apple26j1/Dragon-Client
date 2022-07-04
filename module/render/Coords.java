// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.util.BlockPos;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraft.client.Minecraft;
import Benz.settings.Setting;
import Benz.Client;
import Benz.module.Category;
import Benz.module.Module;

public class Coords extends Module
{
    private String biomeName;
    int posXCoords;
    int posYCoords;
    private int subwidth;
    private int truewidth;
    
    public Coords() {
        super("Coords", "Coords", Category.PVP);
        this.posXCoords = 0;
        this.posYCoords = 0;
        Client.instance.settingsManager.rSetting(new Setting("Coords: X", this, 0.0, 0.0, 900.0, false));
        Client.instance.settingsManager.rSetting(new Setting("Coords: Y", this, 0.0, 0.0, 550.0, false));
    }
    
    public int getWidth() {
        final int xwidth = Coords.fr.getStringWidth("X: " + Math.round(Minecraft.getMinecraft().thePlayer.posX * 1000.0) / 1000L);
        final int Ywidth = Coords.fr.getStringWidth("Y: " + Math.round(Minecraft.getMinecraft().thePlayer.posY * 1000.0) / 1000L);
        final int Zwidth = Coords.fr.getStringWidth("Z: " + Math.round(Minecraft.getMinecraft().thePlayer.posZ * 1000.0) / 1000L);
        final int BiomeWidth = Coords.fr.getStringWidth("Biome: " + this.biomeName);
        if (xwidth > Ywidth && xwidth > Zwidth) {
            this.subwidth = xwidth;
        }
        else if (Ywidth > xwidth && Ywidth > Zwidth) {
            this.subwidth = Ywidth;
        }
        else {
            this.subwidth = Zwidth;
        }
        if (BiomeWidth > this.subwidth) {
            this.truewidth = BiomeWidth;
        }
        else {
            this.truewidth = this.subwidth;
        }
        if (this.truewidth < 90) {
            this.truewidth = 90;
        }
        return this.truewidth + 6;
    }
    
    public int getHeight() {
        return 45;
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        this.posXCoords = (int)Client.instance.settingsManager.getSettingByName("Coords: X").getValDouble() + 2;
        this.posYCoords = (int)Client.instance.settingsManager.getSettingByName("Coords: Y").getValDouble() + 2;
        Gui.drawRect(this.posXCoords, this.posYCoords, this.posXCoords + this.getWidth() + 1, this.posYCoords + this.getHeight(), -1879048192);
        Coords.fr.drawStringWithShadow("X: " + Math.round(Minecraft.getMinecraft().thePlayer.posX * 1000.0) / 1000L, (float)(this.posXCoords + 3), (float)(this.posYCoords + 4), -1);
        Coords.fr.drawStringWithShadow("Y: " + Math.round(Minecraft.getMinecraft().thePlayer.posY * 1000.0) / 1000L, (float)(this.posXCoords + 3), (float)(this.posYCoords + 10 + 4), -1);
        Coords.fr.drawStringWithShadow("Z: " + Math.round(Minecraft.getMinecraft().thePlayer.posZ * 1000.0) / 1000L, (float)(this.posXCoords + 3), (float)(this.posYCoords + 20 + 4), -1);
        final BlockPos blockpos = new BlockPos(Coords.mc.getRenderViewEntity().posX, Coords.mc.getRenderViewEntity().getEntityBoundingBox().minY, Coords.mc.getRenderViewEntity().posZ);
        if (Coords.mc.theWorld != null && Coords.mc.theWorld.isBlockLoaded(blockpos)) {
            final Chunk chunk = Coords.mc.theWorld.getChunkFromBlockCoords(blockpos);
            this.biomeName = chunk.getBiome(blockpos, Coords.mc.theWorld.getWorldChunkManager()).biomeName;
            Coords.fr.drawStringWithShadow("Biome: " + this.biomeName, (float)(this.posXCoords + 3), (float)(this.posYCoords + 30 + 4), -1);
        }
    }
}
