package com.WildHunt.WildHuntMod.entity;

import com.WildHunt.WildHuntMod.init.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ImlerithEntity extends AbstractWildHuntBoss {
    public ImlerithEntity(EntityType<? extends AbstractWildHuntBoss> type, Level level) {
        super(type, level);
        this.xpReward = 350;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2500.0D)
                .add(Attributes.ATTACK_DAMAGE, 100.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ARMOR, 12.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 2.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.1, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, (target) -> {
            return !(target instanceof AbstractWildHuntBoss);
        }));
    }

    @Override
    public void performSpecialAttack(LivingEntity target) {
        if (!this.level().isClientSide && target != null && this.getPhase() >= 1) {
            target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
            target.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
        }
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof LivingEntity livingTarget) {
            float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            livingTarget.hurt(livingTarget.damageSources().mobAttack(this), damage);
            performSpecialAttack(livingTarget);
            return true;
        }
        return super.doHurtTarget(target);
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        if (!this.level().isClientSide) {
            if (this.random.nextFloat() < 0.12F) {
                this.spawnAtLocation(ModItems.IMLERITH_AXE.get());
            }
        }
    }
}