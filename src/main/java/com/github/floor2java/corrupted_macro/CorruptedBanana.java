package com.github.floor2java.corrupted_macro;

import com.github.floor2java.corrupted_macro.clickgui.ClickGui;
import com.github.floor2java.corrupted_macro.render.Render;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = "corruptedbanana", useMetadata = true)
public class CorruptedBanana {

    KeyBinding visceraKey = new KeyBinding("Viscera Macro", Keyboard.KEY_NONE, "! Viscera");

    private KeyBinding[] kList = {visceraKey};

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new GhostPickaxe());
        MinecraftForge.EVENT_BUS.register(new Render());

        for (KeyBinding k : kList) {
            ClientRegistry.registerKeyBinding(k);
        }
    }

    @SubscribeEvent
    public void onKey(InputEvent.KeyInputEvent e) {
        if (visceraKey.isPressed()) {
            Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
        }
    }
}
