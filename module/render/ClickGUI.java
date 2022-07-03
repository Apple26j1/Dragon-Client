// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraft.client.gui.GuiScreen;
import Benz.settings.Setting;
import Benz.Client;
import Benz.module.Category;
import Benz.module.Module;

public class ClickGUI extends Module
{
    public ClickGUI() {
        super("ClickGUI", "Allows you to enable and disable modules", Category.PVP);
        this.setKey(54);
        Client.instance.settingsManager.rSetting(new Setting("Red", this, 0.0, 0.0, 255.0, true));
        Client.instance.settingsManager.rSetting(new Setting("Green", this, 0.0, 0.0, 255.0, true));
        Client.instance.settingsManager.rSetting(new Setting("Blue", this, 0.0, 0.0, 255.0, true));
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        ClickGUI.mc.func_147108_a((GuiScreen)Client.instance.clickGui);
        this.setToggled(false);
    }
}
