package com.WildHunt.WildHuntMod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CustomBossBar {
    private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("minecraft", "textures/gui/bars.png");
    private ResourceLocation texture = DEFAULT_TEXTURE;
    private int color = 0xFF5555; // красный по умолчанию
    private float healthPercent = 1.0f;
    private String name = "";
    private boolean visible = false;

    // Настройки отображения
    private int barWidth = 182;
    private int barHeight = 5;
    private int textureWidth = 256;
    private int textureHeight = 256;
    private int uOffset = 0;
    private int vOffset = 0;

    public CustomBossBar() {}

    public CustomBossBar setTexture(ResourceLocation texture, int width, int height, int u, int v) {
        this.texture = texture;
        this.textureWidth = width;
        this.textureHeight = height;
        this.uOffset = u;
        this.vOffset = v;
        return this;
    }

    public CustomBossBar setColor(int color) {
        this.color = color;
        return this;
    }

    public CustomBossBar setHealthPercent(float percent) {
        this.healthPercent = Math.max(0, Math.min(1, percent));
        return this;
    }

    public CustomBossBar setName(String name) {
        this.name = name;
        return this;
    }

    public CustomBossBar setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public CustomBossBar setSize(int width, int height) {
        this.barWidth = width;
        this.barHeight = height;
        return this;
    }

    public void render(GuiGraphics guiGraphics) {
        if (!visible) return;

        Minecraft mc = Minecraft.getInstance();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        int barX = (screenWidth - barWidth) / 2;
        int barY = 12;

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShaderTexture(0, texture);

        // Рисуем фон
        guiGraphics.blit(texture, barX, barY, uOffset, vOffset, barWidth, barHeight, textureWidth, textureHeight);

        // Рисуем заполненную часть
        int filledWidth = (int) (barWidth * healthPercent);
        guiGraphics.blit(texture, barX, barY, uOffset, vOffset + barHeight, filledWidth, barHeight, textureWidth, textureHeight);

        // Рисуем имя босса (можно убрать, если не нужно)
        int nameWidth = mc.font.width(name);
        int nameX = (screenWidth - nameWidth) / 2;
        int nameY = barY - 12;
        guiGraphics.drawString(mc.font, name, nameX, nameY, color);

        RenderSystem.disableBlend();
    }
}