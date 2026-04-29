package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, WildHuntMod.MOD_ID);

    // Спаун-яйца
    public static final RegistryObject<Item> EREDIN_SPAWN_EGG = ITEMS.register("eredin_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.EREDIN, 0x1A1A2E, 0x8B0000, new Item.Properties()));
    public static final RegistryObject<Item> IMLERITH_SPAWN_EGG = ITEMS.register("imlerith_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.IMLERITH, 0x3A2A2A, 0x2F4F4F, new Item.Properties()));
    public static final RegistryObject<Item> CARANTHIR_SPAWN_EGG = ITEMS.register("caranthir_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CARANTHIR, 0x4B0082, 0x1E90FF, new Item.Properties()));

    // Оружие
    public static final RegistryObject<Item> EREDIN_SWORD = ITEMS.register("eredin_sword", EredinSword::new);
    public static final RegistryObject<Item> IMLERITH_AXE = ITEMS.register("imlerith_axe", ImlerithAxe::new);
    public static final RegistryObject<Item> CARANTHIR_STAFF = ITEMS.register("caranthir_staff", CaranthirStaff::new);
}