package com.WildHunt.WildHuntMod.event;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.CaranthirEntity;
import com.WildHunt.WildHuntMod.entity.EredinEntity;
import com.WildHunt.WildHuntMod.entity.ImlerithEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(modid = WildHuntMod.MOD_ID)
public class ModDeathMessages {
    private static final Random RANDOM = new Random();

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (source.getEntity() instanceof LivingEntity killer) {
            LivingEntity victim = event.getEntity();
            if (killer instanceof EredinEntity) {
                String[] messages = {
                        "death.attack.eredin_1",
                        "death.attack.eredin_2",
                        "death.attack.eredin_3",
                        "death.attack.eredin_4"
                };
                String key = messages[RANDOM.nextInt(messages.length)];
                broadcastDeathMessage(victim, killer, key);
                event.setCanceled(true); // отменяем стандартное сообщение
            } else if (killer instanceof ImlerithEntity) {
                String key = "death.attack.imlerith_kneel";
                broadcastDeathMessage(victim, killer, key);
                event.setCanceled(true);
            } else if (killer instanceof CaranthirEntity) {
                String[] messages = {
                        "death.attack.caranthir_1",
                        "death.attack.caranthir_2",
                        "death.attack.caranthir_3"
                };
                String key = messages[RANDOM.nextInt(messages.length)];
                broadcastDeathMessage(victim, killer, key);
                event.setCanceled(true);
            }
        }
    }

    private static void broadcastDeathMessage(LivingEntity victim, LivingEntity killer, String key) {
        if (victim.getCommandSenderWorld().getServer() != null) {
            victim.getCommandSenderWorld().getServer().getPlayerList().broadcastSystemMessage(
                    Component.translatable(key, victim.getDisplayName(), killer.getDisplayName()), false
            );
        }
    }
}