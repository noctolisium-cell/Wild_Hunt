package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, WildHuntMod.MOD_ID);

    // Заглушка – звуки можно добавить позже
}