// 
// Decompiled by Procyon v0.5.36
// 

package Benz.settings;

import java.util.Iterator;
import Benz.module.Module;
import java.util.ArrayList;

public class SettingsManager
{
    private ArrayList settings;
    
    public SettingsManager() {
        this.settings = new ArrayList();
    }
    
    public void rSetting(final Setting in) {
        this.settings.add(in);
    }
    
    public ArrayList getSettings() {
        return this.settings;
    }
    
    public ArrayList getSettingsByMod(final Module mod) {
        final ArrayList out = new ArrayList();
        for (final Setting s : this.getSettings()) {
            if (s.getParentMod().equals(mod)) {
                out.add(s);
            }
        }
        if (out.isEmpty()) {
            return null;
        }
        return out;
    }
    
    public Setting getSettingByName(final String name) {
        for (final Setting set : this.getSettings()) {
            if (set.getName().equalsIgnoreCase(name)) {
                return set;
            }
        }
        System.err.println("[BaseUtils] Error Setting NOT found: '" + name + "'!");
        return null;
    }
    
    public Setting getSettingByNameAutoSave(final Module mod, final String name) {
        for (final Setting set : this.getSettings()) {
            if (set.getName().equalsIgnoreCase(name) && set.getParentMod() == mod) {
                return set;
            }
        }
        System.err.println("[BaseUtils] Error Setting NOT found: '" + name + "'!");
        return null;
    }
}
