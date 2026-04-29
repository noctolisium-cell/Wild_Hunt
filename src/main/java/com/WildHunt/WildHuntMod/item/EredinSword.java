package com.WildHunt.WildHuntMod.item;

import com.WildHunt.WildHuntMod.config.ModConfig;
import com.WildHunt.WildHuntMod.init.ModRarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.UUID;

public class EredinSword extends SwordItem {
    private static final UUID FROST_ARMOR_MODIFIER = UUID.fromString("a1b2c3d4-e5f6-7890-1234-567890abcdef");

    public EredinSword() {
        super(createTier(), 3, -2.4F, new Properties()
                .fireResistant()
                .rarity(ModRarity.MYTHIC));
    }

    private static Tier createTier() {
        return new Tier() {
            public int getUses() { return 2000; }
            public float getSpeed() { return 12.0F; }
            public float getAttackDamageBonus() { return (float) (ModConfig.get().eredinSwordDamage - 3); }
            public int getLevel() { return 4; }
            public int getEnchantmentValue() { return 22; }
            public Ingredient getRepairIngredient() { return Ingredient.of(net.minecraft.world.item.Items.NETHERITE_INGOT); }
        };
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean hurt = super.hurtEnemy(stack, target, attacker);
        if (!attacker.level().isClientSide && hurt) {
            applyFrostStacks(target);
        }
        return hurt;
    }

    private void applyFrostStacks(LivingEntity target) {
        var data = target.getPersistentData();
        String key = "eredin_frost_stacks";
        int stacks = data.getInt(key);
        long lastHit = data.getLong("eredin_last_hit_time");

        long now = target.level().getGameTime();
        int resetDelay = ModConfig.get().stackResetDelayTicks;
        if (now - lastHit > resetDelay) stacks = 0;

        stacks = Math.min(stacks + 1, ModConfig.get().maxFrostStacks);
        data.putInt(key, stacks);
        data.putLong("eredin_last_hit_time", now);

        updateArmorModifier(target, stacks);
        applyFreezeEffect(target, stacks);
    }

    private void updateArmorModifier(LivingEntity target, int stacks) {
        AttributeInstance armor = target.getAttribute(Attributes.ARMOR);
        if (armor == null) return;
        armor.removeModifier(FROST_ARMOR_MODIFIER);
        if (stacks > 0) {
            double reduction = -stacks * ModConfig.get().armorReductionPerStack;
            AttributeModifier modifier = new AttributeModifier(FROST_ARMOR_MODIFIER,
                    "Frostbite armor reduction", reduction, AttributeModifier.Operation.ADDITION);
            armor.addTransientModifier(modifier);
        }
    }

    private void applyFreezeEffect(LivingEntity target, int stacks) {
        int duration = ModConfig.get().freezeDurationTicksBase + stacks * ModConfig.get().freezeDurationTicksPerStack;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration, 1, false, false));

        float coldDamage = (float) (ModConfig.get().coldDamageBase + stacks * ModConfig.get().coldDamagePerStack);
        target.hurt(target.damageSources().freeze(), coldDamage);
    }
}