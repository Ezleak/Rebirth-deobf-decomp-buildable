package me.rebirthclient;

import java.io.InputStream;
import java.nio.ByteBuffer;
import me.rebirthclient.api.managers.Managers;
import me.rebirthclient.api.util.render.RenderUtil;
import me.rebirthclient.mod.gui.screen.Gui;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Util;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid="rebirth", name="LoserGod.cc", version="dev")
public class Rebirth {
    public static final Logger LOGGER = LogManager.getLogger("Rebirth");
    @Mod.Instance
    public static Rebirth INSTANCE;

    public static void load() {
        LOGGER.info("Loading LoserGod.cc Dev...");
        Managers.load();
        if (Gui.INSTANCE == null) {
            Gui.INSTANCE = new Gui();
        }
        LOGGER.info("LoserGod.cc Dev successfully loaded!\n");
    }

    public static void unload(boolean force) {
        LOGGER.info("Unloading LoserGod.cc Dev...");
        Managers.unload(force);
        LOGGER.info("LoserGod.cc Dev successfully unloaded!\n");
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Display.setTitle("LoserGod.cc Dev: Loading...");
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try (InputStream inputStream16x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/rebirth/constant/icon16x.png");
                 InputStream inputStream32x = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/rebirth/constant/icon32x.png")){
                ByteBuffer[] icons = new ByteBuffer[]{RenderUtil.readImageToBuffer(inputStream16x), RenderUtil.readImageToBuffer(inputStream32x)};
                Display.setIcon(icons);
            }
            catch (Exception e) {
                LOGGER.error("LoserGod.cc Dev couldn't set the window icon!", e);
            }
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        Rebirth.load();
    }
}

