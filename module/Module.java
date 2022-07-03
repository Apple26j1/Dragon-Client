// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module;

import net.minecraftforge.common.MinecraftForge;
import Benz.Client;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;

public class Module
{
    protected static Minecraft mc;
    protected static FontRenderer fr;
    protected static ScaledResolution sr;
    private String name;
    private String description;
    private int key;
    private Category category;
    private boolean toggled;
    public boolean visible;
    
    public Module(final String name, final String description, final Category category) {
        this.visible = true;
        this.name = name;
        this.description = description;
        this.key = 0;
        this.category = category;
        this.toggled = false;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
    }
    
    public boolean isToggled() {
        return this.toggled;
    }
    
    public boolean isDisabled() {
        return !this.toggled;
    }
    
    public void setToggled(final boolean toggled) {
        this.toggled = toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
        if (this.toggled) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    static {
        Module.mc = Minecraft.func_71410_x();
        Module.fr = Minecraft.func_71410_x().field_71466_p;
        Module.sr = new ScaledResolution(Module.mc);
    }
}
