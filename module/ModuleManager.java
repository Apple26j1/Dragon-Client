// 
// Decompiled by Procyon v0.5.36
// 

package Benz.module;

import java.util.Iterator;
import Benz.module.misc.VoidFix;
import Benz.module.misc.ResourceExploitFix;
import Benz.module.misc.NoBreakParticle;
import Benz.module.render.Fullbright;
import Benz.module.render.PotionStatus;
import Benz.module.render.ClickGUI;
import Benz.module.render.Coords;
import Benz.module.render.BlockInfo;
import Benz.module.render.ArmorStatus;
import Benz.module.render.FPS;
import Benz.module.misc.NoParticles;
import Benz.module.misc.MemoryFix;
import java.util.ArrayList;

public class ModuleManager
{
    public ArrayList modules;
    
    public ModuleManager() {
        (this.modules = new ArrayList()).clear();
        this.modules.add(new MemoryFix());
        this.modules.add(new NoParticles());
        this.modules.add(new FPS());
        this.modules.add(new ArmorStatus());
        this.modules.add(new BlockInfo());
        this.modules.add(new Coords());
        this.modules.add(new ClickGUI());
        this.modules.add(new PotionStatus());
        this.modules.add(new Fullbright());
        this.modules.add(new NoBreakParticle());
        this.modules.add(new ResourceExploitFix());
        this.modules.add(new VoidFix());
    }
    
    public Module getModule(final String name) {
        for (final Module m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public ArrayList getModuleList() {
        return this.modules;
    }
    
    public ArrayList getModulesInCategory(final Category c) {
        final ArrayList mods = new ArrayList();
        for (final Module m : this.modules) {
            if (m.getCategory() == c) {
                mods.add(m);
            }
        }
        return mods;
    }
}
