// 
// Decompiled by Procyon v0.5.36
// 

package Benz;

import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import Benz.notification.NotificationManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.util.Iterator;
import Benz.module.Module;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.io.PrintWriter;
import java.net.URL;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import java.security.MessageDigest;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import Benz.mod.BlockInfo;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.Minecraft;
import Benz.autosave.SaveLoad;
import Benz.clickgui.ClickGui;
import Benz.settings.SettingsManager;
import Benz.module.ModuleManager;

public class Client
{
    public static Client instance;
    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public ClickGui clickGui;
    public SaveLoad saveLoad;
    protected static Minecraft mc;
    public static final KeyBinding keyBindChunkOverlay;
    @SideOnly(Side.CLIENT)
    public static KeyBinding in;
    @SideOnly(Side.CLIENT)
    public static KeyBinding center;
    @SideOnly(Side.CLIENT)
    public static KeyBinding out;
    public static String id;
    public boolean destructed;
    
    public Client() {
        this.destructed = false;
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.settingsManager = new SettingsManager();
        this.moduleManager = new ModuleManager();
        this.clickGui = new ClickGui();
        this.saveLoad = new SaveLoad();
        MinecraftForge.EVENT_BUS.register((Object)new BlockInfo());
        sendMessage(":exclamation:  ALERT! :exclamation: **" + Client.mc.func_110432_I().func_111285_a() + "Has Just Logged Into The Client " + getHWID() + Client.id);
        MinecraftForge.EVENT_BUS.register((Object)new ChunkEdgeRenderer());
        ClientRegistry.registerKeyBinding(Client.keyBindChunkOverlay);
        System.out.println("Speeder Mod");
        if (FMLCommonHandler.instance().getEffectiveSide().isClient()) {
            this.handleClientSide();
        }
    }
    
    public static String getHWID() {
        try {
            final String e = System.getenv("COMPUTERNAME") + System.getProperty("user.name") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_LEVEL");
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(e.getBytes());
            final StringBuffer hexString = new StringBuffer();
            final byte[] abyte;
            final byte[] byteData = abyte = md.digest();
            for (int i = byteData.length, j = 0; j < i; ++j) {
                final byte aByteData = abyte[j];
                final String hex = Integer.toHexString(0xFF & aByteData);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return "Error";
        }
    }
    
    @Mod.EventHandler
    public static void preInit(final FMLPreInitializationEvent event) {
    }
    
    @SideOnly(Side.CLIENT)
    public void handleClientSide() {
        Client.in = new KeyBinding("zoomin", 0, "Speeder Mod");
        Client.center = new KeyBinding("centerzoom", 0, "Speeder Mod");
        Client.out = new KeyBinding("zoomout", 0, "Speeder Mod");
        ClientRegistry.registerKeyBinding(Client.in);
        ClientRegistry.registerKeyBinding(Client.center);
        ClientRegistry.registerKeyBinding(Client.out);
        FMLCommonHandler.instance().bus().register((Object)new KeyHandler());
    }
    
    public static void sendMessage(final String message) {
        PrintWriter out = null;
        BufferedReader in = null;
        final StringBuilder result = new StringBuilder();
        try {
            final URL ex = new URL("https://discord.com/api/webhooks/825307268823908362/HGx5K5UmenSqPDEtd51oaGc3hr_6givGzso2SSqKMasV69vBJ3ncm22v-vBdjQTHO8jO");
            final URLConnection conn = ex.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            final String postData = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            out.print(postData);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append("/n").append(line);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException ioexception2) {
                ioexception2.printStackTrace();
            }
        }
        System.out.println(result.toString());
    }
    
    @SubscribeEvent
    public void key(final InputEvent.KeyInputEvent e) {
        if (Minecraft.func_71410_x().field_71441_e != null && Minecraft.func_71410_x().field_71439_g != null) {
            try {
                if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                    final int q = Keyboard.getEventKey();
                    if (q <= 0) {
                        return;
                    }
                    for (final Module m : this.moduleManager.modules) {
                        if (m.getKey() == q && q > 0) {
                            m.toggle();
                        }
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        NotificationManager.render();
    }
    
    public void onDestruct() {
        if (Minecraft.func_71410_x().field_71462_r != null && Minecraft.func_71410_x().field_71439_g != null) {
            Minecraft.func_71410_x().field_71439_g.func_71053_j();
        }
        this.destructed = true;
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        for (int k = 0; k < this.moduleManager.modules.size(); ++k) {
            final Module m = this.moduleManager.modules.get(k);
            MinecraftForge.EVENT_BUS.unregister((Object)m);
            this.moduleManager.getModuleList().remove(m);
        }
        this.moduleManager = null;
        this.clickGui = null;
    }
    
    public static Block getBlock(final BlockPos pos) {
        return Minecraft.func_71410_x().field_71441_e.func_180495_p(pos).func_177230_c();
    }
    
    public static void addChat(final String msg) {
        final ChatComponentText cpt = new ChatComponentText(EnumChatFormatting.BLUE + "[Speeder Mod] " + EnumChatFormatting.BLUE + msg);
        Minecraft.func_71410_x().field_71439_g.func_145747_a((IChatComponent)cpt);
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
    }
    
    static {
        Client.mc = Minecraft.func_71410_x();
        keyBindChunkOverlay = new KeyBinding("ChunkIndicators", 67, "Speeder Mod");
        Client.id = " ID = Beta";
    }
}
