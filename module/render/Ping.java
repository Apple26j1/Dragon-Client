// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import Benz.settings.Setting;
import Benz.Client;
import Benz.module.Category;
import Benz.module.Module;

public class Ping extends Module
{
    int posXPing;
    int posYPing;
    
    public Ping() {
        super("Ping", "Pingdisplay", Category.PVP);
        this.posXPing = 0;
        this.posYPing = 0;
        Client.instance.settingsManager.rSetting(new Setting("Ping: X", this, 0.0, 0.0, 900.0, false));
        Client.instance.settingsManager.rSetting(new Setting("Ping: Y", this, 0.0, 0.0, 550.0, false));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final int ping = Ping.mc.getNetHandler().getPlayerInfo(Ping.mc.thePlayer.getUniqueID()).getResponseTime();
        this.posXPing = (int)Client.instance.settingsManager.getSettingByName("Ping: X").getValDouble() + 2;
        this.posYPing = (int)Client.instance.settingsManager.getSettingByName("Ping: Y").getValDouble() + 2;
        Ping.fr.drawStringWithShadow("Ping: " + ping, (float)this.posXPing, (float)this.posYPing, -1);
    }
}
