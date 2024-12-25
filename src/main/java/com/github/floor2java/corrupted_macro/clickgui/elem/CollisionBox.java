package com.github.floor2java.corrupted_macro.clickgui.elem;

import com.github.floor2java.corrupted_macro.utils.ChatUtils;
import com.github.floor2java.corrupted_macro.utils.gui.glyph.GlyphPageFontRenderer;
import com.github.floor2java.corrupted_macro.utils.gui.shaders.misc.DrawHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class CollisionBox extends GuiButton {

    private int width;
    private int height;
    private ResourceLocation image;
    private boolean debug;

    private int x;
    private int y;
    private String buttonText;

    public CollisionBox(int buttonId, int x, int y, int width, int height, String buttonText, ResourceLocation image, boolean debug) {

        super(buttonId, x, y, buttonText);

        this.width = width;
        this.height = height;
        this.image = image;
        this.debug = debug;

        this.x = x;
        this.y = y;
        this.buttonText = buttonText;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (!this.visible) return;
        hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width
                && mouseY < this.yPosition + this.height;

        if (this.image != null) {
            DrawHelper.drawIcon(this.image, this.x, this.y, this.width, this.height);
        }
        GlyphPageFontRenderer font = GlyphPageFontRenderer.create("Arial", 24, true, true, true);
        if (!this.buttonText.equals("")) {
            if (this.hovered) {
                font.drawCenteredString(buttonText, (int) (this.x + 0.5 * width), this.y + 52, 0xFFC5B936, false);
            } else {
                font.drawCenteredString(buttonText, (int) (this.x + 0.5 * width), this.y + 52, 0xFFFFFFFF, false);
            }
        }
        if (this.debug) {
            if (hovered)
                DrawHelper.drawRect(this.x, this.y + height, this.width, this.height, new Color(61, 255, 0));
            else
                DrawHelper.drawRect(this.x, this.y + height, this.width, this.height, new Color(255, 0, 0));
        }

    }

}