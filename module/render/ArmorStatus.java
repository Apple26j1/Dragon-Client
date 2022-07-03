// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.render;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.item.ItemStack;
import Benz.mod.ArmorStatusMod;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import Benz.module.Category;
import Benz.module.Module;

public class ArmorStatus extends Module
{
    public ArmorStatus() {
        super("ArmorStatus", "RenderArmorStatus", Category.PVP);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        for (int i21 = 0; i21 < Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70460_b.length; ++i21) {
            final ItemStack is = Minecraft.func_71410_x().field_71439_g.field_71071_by.field_70460_b[i21];
            ArmorStatusMod.renderArmorStatus(ArmorStatus.sr, i21, is);
        }
    }
}
