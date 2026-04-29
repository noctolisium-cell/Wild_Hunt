package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.block.entity.BlizzardCoreEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, WildHuntMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<BlizzardCoreEntity>> BLIZZARD_CORE =
            BLOCK_ENTITIES.register("blizzard_core",
                    () -> BlockEntityType.Builder.of(BlizzardCoreEntity::new, ModBlocks.BLIZZARD_CORE.get())
                            .build(null));
}