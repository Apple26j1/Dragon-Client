// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import Benz.module.Category;
import Benz.module.Module;

public class Fullbright extends Module
{
    public Fullbright() {
        super("Fullbright", "Fullbright", Category.PVP);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        Fullbright.mc.field_71474_y.field_74333_Y = 10.0f;
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        Fullbright.mc.field_71474_y.field_74333_Y = 0.0f;
    }
}
