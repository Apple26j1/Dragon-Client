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

public class NoBreakParticle extends Module
{
    private int messageDelay;
    private IChatComponent updateMessage;
    
    @SubscribeEvent
    public void MessageDelay(final TickEvent.ClientTickEvent clienttickevent) {
        if (this.updateMessage != null && Minecraft.getMinecraft().thePlayer != null && ++this.messageDelay == 80) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(this.updateMessage);
            this.updateMessage = null;
        }
    }
    
    public NoBreakParticle() {
        super("NoBreakParticle", "NoBreakParticle", Category.FPSBOOST);
        this.messageDelay = 0;
    }
}
