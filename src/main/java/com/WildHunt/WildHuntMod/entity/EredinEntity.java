package com.WildHunt.WildHuntMod.entity;

import com.WildHunt.WildHuntMod.init.ModEntities;
import com.WildHunt.WildHuntMod.init.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class EredinEntity extends AbstractWildHuntBoss {
    public EredinEntity(EntityType<? extends AbstractWildHuntBoss> type, Level level) {
        super(type, level);
        this.xpReward = 500;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3000.0D)
                .add(Attributes.ATTACK_DAMAGE, 50.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.9D)
                .add(Attributes.ARMOR, 16.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 5.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
        // Атакуем всех живых существ, кроме других боссов Дикой Охоты
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, LivingEntity.class, true, (target) -> {
            return !(target instanceof AbstractWildHuntBoss);
        }));
    }

    @Override
    public void performSpecialAttack(LivingEntity target) {
        if (!this.level().isClientSide && target != null) {
            if (this.getPhase() >= 1) {
                this.teleportTo(target.getX(), target.getY(), target.getZ());
            }
            if (this.getPhase() >= 2 && this.random.nextFloat() < 0.3f) {
                ImlerithEntity minion = new ImlerithEntity(ModEntities.IMLERITH.get(), this.level());
                minion.setPos(this.getX(), this.getY(), this.getZ());
                this.level().addFreshEntity(minion);
            }
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
            if (this.random.nextFloat() < 0.12F) {  // 12% шанс
                this.spawnAtLocation(ModItems.EREDIN_SWORD.get());
            }
        }
    }
}