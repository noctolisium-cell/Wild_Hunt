package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, WildHuntMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> WILD_HUNT_TAB = TABS.register("wildhunt_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.wildhunt"))
                    .icon(() -> new ItemStack(ModItems.EREDIN_SPAWN_EGG.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.EREDIN_SPAWN_EGG.get());
                        output.accept(ModItems.IMLERITH_SPAWN_EGG.get());
                        output.accept(ModItems.CARANTHIR_SPAWN_EGG.get());
                        output.accept(ModItems.EREDIN_SWORD.get());
                        output.accept(ModItems.IMLERITH_AXE.get());
                        output.accept(ModItems.CARANTHIR_STAFF.get());
                    })
                    .build());
}