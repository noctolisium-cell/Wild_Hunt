package com.WildHunt.WildHuntMod.client.renderer.overlay;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = "wildhunt")
public class BlizzardOverlay {
    public static void setBlizzardActive(boolean active, float intensity) {
        // Оверлей пока отключён
        // При желании можно добавить визуал из прошлого коммита
    }
}