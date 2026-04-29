package com.WildHunt.WildHuntMod.item;

import com.WildHunt.WildHuntMod.config.ModConfig;
import com.WildHunt.WildHuntMod.entity.projectile.FrozenProjectile;
import com.WildHunt.WildHuntMod.init.ModRarity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class CaranthirStaff extends SwordItem {
    public CaranthirStaff() {
        super(new Tier() {
            public int getUses() { return 1500; }
            public float getSpeed() { return 5.0F; }
            public float getAttackDamageBonus() { return (float) ModConfig.get().caranthirStaffDamage; }
            public int getLevel() { return 4; }
            public int getEnchantmentValue() { return 25; }
            public Ingredient getRepairIngredient() { return Ingredient.of(net.minecraft.world.item.Items.EMERALD); }
        }, 3, -2.6F, new Properties().fireResistant().rarity(ModRarity.MYTHIC));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            if (player.getCooldowns().isOnCooldown(this)) {
                return InteractionResultHolder.fail(stack);
            }

            int count = ModConfig.get().frozenProjectilesCount;
            float damage = (float) ModConfig.get().frozenProjectileDamage;
            int slowDuration = ModConfig.get().frozenProjectileSlowDuration;

            AABB area = player.getBoundingBox().inflate(20);
            List<LivingEntity> targets = level.getEntitiesOfClass(LivingEntity.class, area,
                    e -> e != player && e.isAlive() && !(e instanceof Player));

            if (!targets.isEmpty()) {
                List<LivingEntity> selected = targets.stream()
                        .sorted((a, b) -> Double.compare(a.distanceToSqr(player), b.distanceToSqr(player)))
                        .limit(count)
                        .toList();
                for (LivingEntity target : selected) {
                    FrozenProjectile projectile = new FrozenProjectile(level, player, target, damage, slowDuration);
                    projectile.setPos(player.getEyePosition().x, player.getEyePosition().y, player.getEyePosition().z);
                    level.addFreshEntity(projectile);
                }
                stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                player.getCooldowns().addCooldown(this, ModConfig.get().staffCooldownTicks);
            }
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }
}