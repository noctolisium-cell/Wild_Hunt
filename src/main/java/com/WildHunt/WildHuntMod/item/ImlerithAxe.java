package com.WildHunt.WildHuntMod.item;

import com.WildHunt.WildHuntMod.config.ModConfig;
import com.WildHunt.WildHuntMod.init.ModRarity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.UUID;

public class ImlerithAxe extends AxeItem {
    private static final UUID CRUSH_ARMOR_MODIFIER = UUID.fromString("b2c3d4e5-f6a7-8901-2345-67890abcdef1");

    public ImlerithAxe() {
        super(createTier(), 6.0F, -3.0F, new Properties()
                .fireResistant()
                .rarity(ModRarity.MYTHIC));
    }

    private static Tier createTier() {
        return new Tier() {
            public int getUses() { return 1800; }
            public float getSpeed() { return 9.0F; }
            public float getAttackDamageBonus() { return (float) (ModConfig.get().imlerithAxeDamage - 7); }
            public int getLevel() { return 4; }
            public int getEnchantmentValue() { return 20; }
            public Ingredient getRepairIngredient() { return Ingredient.of(net.minecraft.world.item.Items.NETHERITE_INGOT); }
        };
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean hurt = super.hurtEnemy(stack, target, attacker);
        if (!attacker.level().isClientSide && hurt) {
            applyCrushingBlow(target, attacker);
        }
        return hurt;
    }

    private void applyCrushingBlow(LivingEntity target, LivingEntity attacker) {
        AttributeInstance armor = target.getAttribute(Attributes.ARMOR);
        if (armor != null) {
            armor.removeModifier(CRUSH_ARMOR_MODIFIER);
            double reduction = ModConfig.get().crushingArmorReduction;
            AttributeModifier modifier = new AttributeModifier(CRUSH_ARMOR_MODIFIER,
                    "Crushing blow", -reduction, AttributeModifier.Operation.ADDITION);
            armor.addTransientModifier(modifier);
        }

        int stunDuration = ModConfig.get().crushingStunDuration;
        target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, stunDuration, 3, false, false));
        target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, stunDuration, 1, false, false));

        float explosionDamage = (float) ModConfig.get().crushingExplosionDamage;
        attacker.level().getEntitiesOfClass(LivingEntity.class, target.getBoundingBox().inflate(3.0))
                .forEach(e -> e.hurt(attacker.damageSources().mobAttack(attacker), explosionDamage));
    }
}