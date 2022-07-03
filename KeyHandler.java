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
        if (event.phase == TickEvent.Phase.START && KeyHandler.mc.field_71439_g != null && KeyHandler.mc.field_71441_e != null && KeyHandler.mc.field_71415_G) {
            GameSettings gamesettings = KeyHandler.mc.field_71474_y;
            if (GameSettings.func_100015_a(Client.in)) {
                if (KeyHandler.mc.field_71439_g.func_70093_af()) {
                    final GameSettings field_71474_y = KeyHandler.mc.field_71474_y;
                    --field_71474_y.field_74334_X;
                }
                else {
                    final GameSettings field_71474_y2 = KeyHandler.mc.field_71474_y;
                    field_71474_y2.field_74334_X -= 0.1f;
                }
            }
            gamesettings = KeyHandler.mc.field_71474_y;
            if (GameSettings.func_100015_a(Client.out)) {
                if (KeyHandler.mc.field_71439_g.func_70093_af()) {
                    final GameSettings field_71474_y3 = KeyHandler.mc.field_71474_y;
                    ++field_71474_y3.field_74334_X;
                }
                else {
                    final GameSettings field_71474_y4 = KeyHandler.mc.field_71474_y;
                    field_71474_y4.field_74334_X += 0.1f;
                }
            }
            gamesettings = KeyHandler.mc.field_71474_y;
            if (GameSettings.func_100015_a(Client.center)) {
                KeyHandler.mc.field_71474_y.field_74334_X = KeyHandler.defaultfov;
            }
        }
    }
    
    static {
        KeyHandler.mc = Minecraft.func_71410_x();
        KeyHandler.defaultfov = KeyHandler.mc.field_71474_y.field_74334_X;
    }
}
