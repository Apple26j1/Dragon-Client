// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module.misc;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import Benz.module.Category;
import Benz.module.Module;

public class VoidFix extends Module
{
    public VoidFix() {
        super("VoidFix", "Always holds down the sprint key", Category.FPSBOOST);
        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
    }
}
