// 
// Decompiled by Procyon v0.5.36
// 

package Benz;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyHandler
{
    public static Minecraft mc;
    public static float defaultfov;
    public static final float amount = 0.1f;
    
    @SubscribeEvent
    public void onRenderTick(final TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.START && KeyHandler.mc.thePlayer != null && KeyHandler.mc.theWorld != null && KeyHandler.mc.inGameHasFocus) {
            GameSettings gamesettings = KeyHandler.mc.gameSettings;
            if (GameSettings.isKeyDown(Client.in)) {
                if (KeyHandler.mc.thePlayer.isSneaking()) {
                    --gamesettings.fovSetting;
                }
                else {
                    gamesettings.fovSetting -= 0.1f;
                }
            }
            if (GameSettings.isKeyDown(Client.out)) {
                if (KeyHandler.mc.thePlayer.isSneaking()) {
                    ++gamesettings.fovSetting;
                }
                else {
                    gameSettings.fovSetting += 0.1f;
                }
            }
            if (GameSettings.isKeyDown(Client.center)) {
                KeyHandler.mc.gameSettings.fovSetting = KeyHandler.defaultfov;
            }
        }
    }
    
    static {
        KeyHandler.mc = Minecraft.getMinecraft();
        KeyHandler.defaultfov = KeyHandler.mc.gameSettings.fovSetting;
    }
}
