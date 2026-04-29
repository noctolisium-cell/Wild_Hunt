package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, WildHuntMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> ICE_SPARK = PARTICLES.register("ice_spark", () -> new SimpleParticleType(true));
}