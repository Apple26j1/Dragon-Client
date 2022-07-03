// 
// Decompiled by Procyon v0.5.36
// 

package Benz.settings;

import Benz.Client;
import java.util.ArrayList;
import Benz.module.Module;

public class Setting
{
    private String name;
    private Module parent;
    private String mode;
    private String sval;
    private ArrayList options;
    private boolean bval;
    private double dval;
    private double min;
    private double max;
    private boolean onlyint;
    
    public Setting(final String name, final Module parent, final String sval, final ArrayList options) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.options = options;
        this.mode = "Combo";
    }
    
    public Setting(final String name, final Module parent, final boolean bval) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
    }
    
    public Setting(final String name, final Module parent, final double dval, final double min, final double max, final boolean onlyint) {
        this.onlyint = false;
        this.name = name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
    }
    
    public String getName() {
        return this.name;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public void setValString(final String in) {
        this.sval = in;
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
    }
    
    public ArrayList getOptions() {
        return this.options;
    }
    
    public boolean getValBoolean() {
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
        return this.bval;
    }
    
    public boolean getUnValBoolean() {
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
        return !this.bval;
    }
    
    public void setValBoolean(final boolean in) {
        this.bval = in;
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
    }
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public void setValDouble(final double in) {
        this.dval = in;
        if (Client.instance.saveLoad != null) {
            Client.instance.saveLoad.save();
        }
    }
    
    public double getMin() {
        return this.min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
}
