package com.github.floor2java.corrupted_macro.render;

import com.github.floor2java.corrupted_macro.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import static com.github.floor2java.corrupted_macro.utils.ChatUtils.debug;

public class Render {

    @SubscribeEvent
    public void onRenderOverlay(RenderGameOverlayEvent e) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.thePlayer == null) return;
        if (e.type != RenderGameOverlayEvent.ElementType.ALL) return;
    }
}
