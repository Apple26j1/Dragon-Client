// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.misc;

import Benz.module.Category;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.IChatComponent;
import Benz.module.Module;

public class MemoryFix extends Module
{
    private int messageDelay;
    private IChatComponent updateMessage;
    
    @SubscribeEvent
    public void MessageDelay(final TickEvent.ClientTickEvent clienttickevent) {
        if (this.updateMessage != null && Minecraft.func_71410_x().field_71439_g != null && ++this.messageDelay == 80) {
            Minecraft.func_71410_x().field_71439_g.func_145747_a(this.updateMessage);
            this.updateMessage = null;
        }
    }
    
    public MemoryFix() {
        super("MemoryFix", "MemoryFix", Category.FPSBOOST);
        this.messageDelay = 0;
    }
}
