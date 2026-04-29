package com.WildHunt.WildHuntMod;

import com.WildHunt.WildHuntMod.config.ModConfig;
import com.WildHunt.WildHuntMod.entity.CaranthirEntity;
import com.WildHunt.WildHuntMod.entity.EredinEntity;
import com.WildHunt.WildHuntMod.entity.ImlerithEntity;
import com.WildHunt.WildHuntMod.init.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(WildHuntMod.MOD_ID)
public class WildHuntMod {
    public static final String MOD_ID = "wildhunt";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public WildHuntMod() {
        ModConfig.init();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITY_TYPES.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModCreativeTabs.TABS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModParticles.PARTICLES.register(bus);
        bus.register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onAttributeCreation(EntityAttributeCreationEvent event) {
        event.put(ModEntities.EREDIN.get(), EredinEntity.createAttributes().build());
        event.put(ModEntities.IMLERITH.get(), ImlerithEntity.createAttributes().build());
        event.put(ModEntities.CARANTHIR.get(), CaranthirEntity.createAttributes().build());
    }
}