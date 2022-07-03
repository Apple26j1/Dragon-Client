// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import Benz.settings.Setting;
import Benz.Client;
import Benz.module.Category;
import Benz.module.Module;

public class FPS extends Module
{
    int posXFPS;
    int posYFPS;
    
    public FPS() {
        super("FPS", "RenderFramerate", Category.PVP);
        this.posXFPS = 0;
        this.posYFPS = 0;
        Client.instance.settingsManager.rSetting(new Setting("FPS: X", this, 0.0, 0.0, 900.0, false));
        Client.instance.settingsManager.rSetting(new Setting("FPS: Y", this, 0.0, 0.0, 550.0, false));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        this.posXFPS = (int)Client.instance.settingsManager.getSettingByName("FPS: X").getValDouble() + 2;
        this.posYFPS = (int)Client.instance.settingsManager.getSettingByName("FPS: Y").getValDouble() + 2;
        FPS.fr.func_175063_a("FPS[Boost]: " + Minecraft.func_175610_ah(), (float)this.posXFPS, (float)this.posYFPS, -1);
    }
}
