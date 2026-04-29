package com.WildHunt.WildHuntMod.entity;

import com.WildHunt.WildHuntMod.entity.projectile.MagicSphere;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CaranthirEntity extends AbstractWildHuntBoss implements RangedAttackMob {
    public CaranthirEntity(EntityType<? extends AbstractWildHuntBoss> type, Level level) {
        super(type, level);
        this.xpReward = 400;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return createMobAttributes()
                .add(Attributes.MAX_HEALTH, 2800.0D)
                .add(Attributes.ATTACK_DAMAGE, 75.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ARMOR, 14.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0, 40, 15.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {
        if (!this.level().isClientSide && target != null && this.getPhase() >= 1) {
            // Урон для снаряда босса – 12 (можно настроить)
            MagicSphere sphere = new MagicSphere(this.level(), this, target, 12.0F);
            sphere.setPos(this.getX(), this.getY() + 1.5, this.getZ());
            this.level().addFreshEntity(sphere);
        }
    }

    @Override
    public void performSpecialAttack(LivingEntity target) {
        // не используется
    }
}