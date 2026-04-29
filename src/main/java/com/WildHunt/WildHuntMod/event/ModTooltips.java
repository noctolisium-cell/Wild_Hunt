package com.WildHunt.WildHuntMod.event;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.config.ModConfig;
import com.WildHunt.WildHuntMod.item.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = WildHuntMod.MOD_ID)
public class ModTooltips {

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        var config = ModConfig.get();

        if (stack.getItem() instanceof EredinSword) {
            cleanTooltip(event.getToolTip());
            event.getToolTip().add(1, Component.literal("§l§b⚔️ Ледяная погибель ⚔️§r")
                    .withStyle(ChatFormatting.DARK_AQUA));
            event.getToolTip().add(2, Component.literal(
                    " §7Каждый удар накапливает ледяную погибель (до " + config.maxFrostStacks + " стаков)."));
            event.getToolTip().add(3, Component.literal(
                    " §7Каждый стак снижает броню цели на " + config.armorReductionPerStack + " и наносит"));
            event.getToolTip().add(4, Component.literal(
                    " §7дополнительный урон от холода."));

            event.getToolTip().add(5, Component.literal("§7Когда в ведущей руке:").withStyle(ChatFormatting.GRAY));
            event.getToolTip().add(6, Component.literal(" §3Урон: §b" + config.eredinSwordDamage));
            event.getToolTip().add(7, Component.literal(" §3Снижение брони/стак: §b" + config.armorReductionPerStack));
            event.getToolTip().add(8, Component.literal(" §3+0.5 к дальности атаки"));
        }
        else if (stack.getItem() instanceof ImlerithAxe) {
            cleanTooltip(event.getToolTip());
            event.getToolTip().add(1, Component.literal("§l§6🌀 Сокрушительный удар 🌀§r")
                    .withStyle(ChatFormatting.GOLD));
            double stunSec = config.crushingStunDuration / 20.0;
            event.getToolTip().add(2, Component.literal(
                    " §7Критические удары оглушают цель на " + String.format("%.1f", stunSec) + " сек"));
            event.getToolTip().add(3, Component.literal(
                    " §7и создают ударную волну, наносящую " + config.crushingExplosionDamage + " урона по площади."));

            event.getToolTip().add(4, Component.literal("§7Когда в ведущей руке:").withStyle(ChatFormatting.GRAY));
            event.getToolTip().add(5, Component.literal(" §6Урон: §e" + config.imlerithAxeDamage));
            event.getToolTip().add(6, Component.literal(" §6Оглушение: §e" + String.format("%.1f", stunSec) + " сек"));
            event.getToolTip().add(7, Component.literal(" §6+15% к критическому урону"));
        }
        else if (stack.getItem() instanceof CaranthirStaff) {
            cleanTooltip(event.getToolTip());
            event.getToolTip().add(1, Component.literal("§l§d❄ Магический залп ❄§r")
                    .withStyle(ChatFormatting.LIGHT_PURPLE));
            event.getToolTip().add(2, Component.literal(
                    " §7Выстреливает " + config.frozenProjectilesCount + " магических снарядов."));
            event.getToolTip().add(3, Component.literal(
                    " §7Каждый наносит " + config.frozenProjectileDamage + " урона и замедляет цель."));

            event.getToolTip().add(4, Component.literal("§7Когда в ведущей руке:").withStyle(ChatFormatting.GRAY));
            event.getToolTip().add(5, Component.literal(" §dУрон снаряда: §5" + config.frozenProjectileDamage));
            event.getToolTip().add(6, Component.literal(" §dСнарядов: §5" + config.frozenProjectilesCount));
            event.getToolTip().add(7, Component.literal(" §dПерезарядка: §5" + (config.staffCooldownTicks / 20.0) + " сек"));
        } else {
            return;
        }

        // Очистка от лишних строк
        event.getToolTip().removeIf(line -> {
            String str = line.getString();
            return str.contains("Урон при атаке") || str.contains("Скорость атаки") ||
                    (str.trim().equals("§7Когда в ведущей руке:") && event.getToolTip().indexOf(line) > 5);
        });
    }

    private static void cleanTooltip(List<Component> tooltip) {
        tooltip.removeIf(line -> {
            String str = line.getString();
            return str.contains("Ледяная погибель") || str.contains("Сокрушительный удар") || str.contains("Магический залп")
                    || str.contains("Когда в ведущей руке:") || str.contains("Урон:") || str.contains("Снижение брони/стак:")
                    || str.contains("Оглушение:") || str.contains("Снарядов:") || str.contains("Перезарядка:");
        });
    }
}