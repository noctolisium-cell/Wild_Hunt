package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.block.BlizzardCoreBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, WildHuntMod.MOD_ID);

    public static final RegistryObject<Block> BLIZZARD_CORE = BLOCKS.register("blizzard_core",
            BlizzardCoreBlock::new);

    public static final RegistryObject<Item> BLIZZARD_CORE_ITEM = ModItems.ITEMS.register("blizzard_core",
            () -> new BlockItem(BLIZZARD_CORE.get(), new Item.Properties()));
}