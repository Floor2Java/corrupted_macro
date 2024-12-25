package com.github.floor2java.corrupted_macro.clickgui;

import com.github.floor2java.corrupted_macro.clickgui.elem.CollisionBox;
import com.github.floor2java.corrupted_macro.utils.gui.ImageProcessor;
import com.github.floor2java.corrupted_macro.utils.gui.glyph.GlyphPageFontRenderer;
import com.github.floor2java.corrupted_macro.utils.gui.shaders.misc.DrawHelper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ClickGui extends GuiScreen {

    private static final GlyphPageFontRenderer font = GlyphPageFontRenderer.create("Arial", 24, true, true, true);
    private static final GlyphPageFontRenderer fontSignature = GlyphPageFontRenderer.create("Arial", 12, true, true, true);

    private static ResourceLocation PLANCHERAV = ImageProcessor.resizeAndLoadTexture(new ResourceLocation("textures/plancher_av.png"), 512, 512);
    private static ResourceLocation DUNGEONS = ImageProcessor.resizeAndLoadTexture(new ResourceLocation("textures/dungeon.png"), 512, 512);
    private static ResourceLocation MACRO = ImageProcessor.resizeAndLoadTexture(new ResourceLocation("textures/macro.png"), 512, 512);
    private static ResourceLocation RENDER = ImageProcessor.resizeAndLoadTexture(new ResourceLocation("textures/render.png"), 512, 512);
    private static ResourceLocation PLAYER = ImageProcessor.resizeAndLoadTexture(new ResourceLocation("textures/player.png"), 512, 512);
    private static ResourceLocation MISC = ImageProcessor.resizeAndLoadTexture(new ResourceLocation("textures/misc.png"), 512, 512);

    @Override
    public void initGui() {

        int base = 110;
        int spacing = 91;
        CollisionBox dungeonBox = new CollisionBox(1, base, 60, 50, 50, "Dungeons", DUNGEONS, false);
        CollisionBox macroBox = new CollisionBox(1, base + 1 * spacing, 60, 50, 50, "Macro", MACRO, false);
        CollisionBox renderBox = new CollisionBox(1, base + 2 * spacing, 60, 50, 50, "Render", RENDER, false);
        CollisionBox playerBox = new CollisionBox(1, base + 3 * spacing, 60, 50, 50, "Player", PLAYER, false);
        CollisionBox miscBox = new CollisionBox(1, base + 4 * spacing, 60, 50, 50, "Misc", MISC, false);


        buttonList.add(dungeonBox);
        buttonList.add(macroBox);
        buttonList.add(renderBox);
        buttonList.add(playerBox);
        buttonList.add(miscBox);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        // basic values
        ScaledResolution sr = new ScaledResolution(mc);
        int w = sr.getScaledWidth();
        int h = sr.getScaledHeight();

        GlStateManager.pushMatrix();

        // BackGround
        int bgWidth = 450;
        int bgHeight = 300;

        DrawHelper.drawRoundedRect(w / 2 - bgWidth / 2, h / 2 + bgHeight / 2, bgWidth, bgHeight, 10, new Color(45, 45, 45, 255));
        DrawHelper.drawRoundedRect(w / 2 - bgWidth / 2, h / 2 + bgHeight / 2 - 210, bgWidth, 10, 15, new Color(33, 33, 33));
        font.drawCenteredString("PlancherClient", (int) (w / 2 * 0.45), (int) (h / 2 * 1.66), 0xFFFFFFFF, true);
        DrawHelper.drawIcon(PLANCHERAV, (int) (w / 2 * 0.45) + 100, (int) (h / 2 * 1.66), 15, 15);
        fontSignature.drawCenteredString("by @Plancherrr", (int) (w / 2 * 0.45) + 72, (int) (h / 2 * 1.66) + 5, 0xFFFFFFFF, true);


        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
