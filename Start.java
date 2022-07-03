// 
// Decompiled by Procyon v0.5.36
// 

package Benz;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "Speeder Mod", version = "1.0", acceptedMinecraftVersions = "[1.8.9]")
public class Start
{
    public static final String MODID = "Speeder Mod";
    public static final String VERSION = "1.0";
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        (Client.instance = new Client()).init();
    }
}
