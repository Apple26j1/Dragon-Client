// 
// Decompiled by Procyon v0.5.36
// 

package Benz;

import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class ChunkEdgeRenderer
{
    private int chunkEdgeState;
    
    public ChunkEdgeRenderer() {
        this.chunkEdgeState = 0;
    }
    
    @SubscribeEvent
    public void resetOverlay(final WorldEvent.Load event) {
        this.chunkEdgeState = 0;
    }
    
    @SubscribeEvent
    public void getKeyPress(final TickEvent.ClientTickEvent event) {
        if (Minecraft.getMinecraft().theWorld != null && Client.keyBindChunkOverlay.isPressed()) {
            this.chunkEdgeState = (this.chunkEdgeState + 1) % 3;
        }
    }
    
    @SubscribeEvent
    public void renderChunkEdges(final RenderWorldLastEvent event) {
        if (this.chunkEdgeState != 0) {
            final EntityPlayerSP entity = Minecraft.getMinecraft().thePlayer;
            final Tessellator tessellator = Tessellator.getInstance();
            final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            GL11.glPushMatrix();
            final float frame = event.partialTicks;
            final double inChunkPosX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * frame;
            final double inChunkPosY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * frame;
            final double inChunkPosZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * frame;
            GL11.glTranslated(-inChunkPosX, -inChunkPosY, -inChunkPosZ);
            GL11.glDisable(3553);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(2.0f);
            worldrenderer.begin(1, DefaultVertexFormats.POSITION_COLOR);
            GL11.glTranslatef((float)(entity.chunkCoordX * 16), 0.0f, (float)((entity.chunkCoordZ * 16));
            double x = 0.0;
            double z = 0.0;
            final float redColourR = 0.9f;
            final float redColourG = 0.0f;
            final float redColourB = 0.0f;
            float redColourA = 1.0f;
            final float greenColourR = 0.0f;
            final float greenColourG = 0.9f;
            final float greenColourB = 0.0f;
            float greenColourA = 0.4f;
            for (int f = -2; f <= 2; ++f) {
                for (int eyeHeightBlock = -2; eyeHeightBlock <= 2; ++eyeHeightBlock) {
                    if (Math.abs(eyeHeightBlock) != 2 || Math.abs(f) != 2) {
                        x = eyeHeightBlock * 16;
                        z = f * 16;
                        redColourA = Math.round(Math.pow(1.25, -(eyeHeightBlock * eyeHeightBlock + f * f)) * 6.0) / 6.0f;
                        worldrenderer.pos(x, 0.0, z).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x, 256.0, z).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x + 16.0, 0.0, z).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x + 16.0, 256.0, z).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x, 0.0, z + 16.0).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x, 256.0, z + 16.0).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x + 16.0, 0.0, z + 16.0).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                        worldrenderer.pos(x + 16.0, 256.0, z + 16.0).color(redColourR, redColourG, redColourB, redColourA).endVertex();
                    }
                }
            }
            z = 0.0;
            x = 0.0;
            if (this.chunkEdgeState == 2) {
                final float f2 = (float)(entity.getEyeHeight() + entity.posY);
                final int eyeHeightBlock = (int)Math.floor(f2);
                final int minY = Math.max(0, eyeHeightBlock - 16);
                final int maxY = Math.min(256, eyeHeightBlock + 16);
                boolean renderedSome = false;
                for (int y = 0; y < 257; ++y) {
                    greenColourA = 0.4f;
                    if (y < minY) {
                        greenColourA -= (float)(Math.pow(minY - y, 2.0) / 500.0);
                    }
                    if (y > maxY) {
                        greenColourA -= (float)(Math.pow(y - maxY, 2.0) / 500.0);
                    }
                    if (greenColourA < 0.01f) {
                        if (renderedSome) {
                            break;
                        }
                    }
                    else {
                        if (y < 256) {
                            for (int n = 1; n < 16; ++n) {
                                worldrenderer.pos((double)n, (double)y, z).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos((double)n, (double)(y + 1), z).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos(x, (double)y, (double)n).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos(x, (double)(y + 1), (double)n).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos((double)n, (double)y, z + 16.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos((double)n, (double)(y + 1), z + 16.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos(x + 16.0, (double)y, (double)n).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                                worldrenderer.pos(x + 16.0, (double)(y + 1), (double)n).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                            }
                        }
                        worldrenderer.pos(0.0, (double)y, 0.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(16.0, (double)y, 0.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(0.0, (double)y, 0.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(0.0, (double)y, 16.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(16.0, (double)y, 0.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(16.0, (double)y, 16.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(0.0, (double)y, 16.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        worldrenderer.pos(16.0, (double)y, 16.0).color(greenColourR, greenColourG, greenColourB, greenColourA).endVertex();
                        renderedSome = true;
                    }
                }
            }
            tessellator.draw();
            GL11.glPopMatrix();
            GL11.glEnable(3553);
            GL11.glDisable(3042);
        }
    }
}
