package com.WildHunt.WildHuntMod.client;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.client.particle.IceSparkParticle;
import com.WildHunt.WildHuntMod.init.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WildHuntMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegistrarParticles {

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        Minecraft.getInstance().particleEngine.register(ModParticles.ICE_SPARK.get(), IceSparkParticle.Provider::new);
    }
}