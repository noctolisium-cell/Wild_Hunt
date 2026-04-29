package com.WildHunt.WildHuntMod.client;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.client.renderer.*;
import com.WildHunt.WildHuntMod.entity.AbstractWildHuntBoss;
import com.WildHunt.WildHuntMod.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WildHuntMod.MOD_ID, value = Dist.CLIENT)
public class ClientEventSubscriber {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.EREDIN.get(), EredinRenderer::new);
        event.registerEntityRenderer(ModEntities.IMLERITH.get(), ImlerithRenderer::new);
        event.registerEntityRenderer(ModEntities.CARANTHIR.get(), CaranthirRenderer::new);
        event.registerEntityRenderer(ModEntities.MAGIC_SPHERE.get(), MagicSphereRenderer::new);
        event.registerEntityRenderer(ModEntities.FROZEN_PROJECTILE.get(), FrozenProjectileRenderer::new);
    }

    @SubscribeEvent
    public static void onRenderGui(RenderGuiEvent.Post event) {
        if (Minecraft.getInstance().level == null) return;
        for (var entity : Minecraft.getInstance().level.entitiesForRendering()) {
            if (entity instanceof AbstractWildHuntBoss boss && boss.getCustomBossBar() != null) {
                boss.getCustomBossBar().render(event.getGuiGraphics());
            }
        }
    }
}