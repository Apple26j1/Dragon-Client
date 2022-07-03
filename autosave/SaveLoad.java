// 
// Decompiled by Procyon v0.5.36
// 

package Benz.autosave;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import Benz.settings.Setting;
import Benz.module.Module;
import java.util.ArrayList;
import Benz.Client;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import java.io.File;

public class SaveLoad
{
    private File dir;
    private File dataFile;
    
    public SaveLoad() {
        this.dir = new File(Minecraft.func_71410_x().field_71412_D, "Client");
        if (!this.dir.exists()) {
            this.dir.mkdir();
        }
        this.dataFile = new File(this.dir, "Config.txt");
        if (!this.dataFile.exists()) {
            try {
                this.dataFile.createNewFile();
            }
            catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
        this.load();
    }
    
    public void save() {
        if (!Client.instance.destructed) {
            final ArrayList toSave = new ArrayList();
            for (final Module set : Client.instance.moduleManager.modules) {
                toSave.add("MOD:" + set.getName() + ":" + set.isToggled() + ":" + set.getKey());
            }
            for (final Setting set2 : Client.instance.settingsManager.getSettings()) {
                if (set2.isCheck()) {
                    toSave.add("SET:" + set2.getName() + ":" + set2.getParentMod().getName() + ":" + set2.getValDouble());
                }
                if (set2.isCombo()) {
                    toSave.add("SET:" + set2.getName() + ":" + set2.getParentMod().getName() + ":" + set2.getValString());
                }
                if (set2.isSlider()) {
                    toSave.add("SET:" + set2.getName() + ":" + set2.getParentMod().getName() + ":" + set2.getValDouble());
                }
            }
            try {
                final PrintWriter e2 = new PrintWriter(this.dataFile);
                for (final String str : toSave) {
                    e2.println(str);
                }
                e2.close();
            }
            catch (FileNotFoundException filenotfoundexception) {
                filenotfoundexception.printStackTrace();
            }
        }
    }
    
    public void load() {
        if (!Client.instance.destructed) {
            final ArrayList lines = new ArrayList();
            try {
                final BufferedReader e = new BufferedReader(new FileReader(this.dataFile));
                for (String s = e.readLine(); s != null; s = e.readLine()) {
                    lines.add(s);
                }
                e.close();
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            for (final String s : lines) {
                final String[] args = s.split(":");
                if (s.toLowerCase().startsWith("mod:")) {
                    final Module m = Client.instance.moduleManager.getModule(args[1]);
                    if (m == null) {
                        continue;
                    }
                    m.setToggled(Boolean.parseBoolean(args[2]));
                    m.setKey(Integer.parseInt(args[3]));
                }
                else {
                    if (!s.toLowerCase().startsWith("set:")) {
                        continue;
                    }
                    final Module m = Client.instance.moduleManager.getModule(args[2]);
                    if (m == null) {
                        continue;
                    }
                    final Setting set = Client.instance.settingsManager.getSettingByNameAutoSave(m, args[1]);
                    if (set == null) {
                        continue;
                    }
                    if (set.isCheck()) {
                        set.setValBoolean(Boolean.parseBoolean(args[3]));
                    }
                    if (set.isCombo()) {
                        set.setValString(args[3]);
                    }
                    if (!set.isSlider()) {
                        continue;
                    }
                    set.setValDouble(Double.parseDouble(args[3]));
                }
            }
        }
    }
}
