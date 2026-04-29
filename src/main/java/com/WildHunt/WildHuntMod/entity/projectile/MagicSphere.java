package com.WildHunt.WildHuntMod.entity.projectile;

import com.WildHunt.WildHuntMod.init.ModEntities;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class MagicSphere extends ThrowableProjectile {
    private LivingEntity owner;
    private LivingEntity target;
    private float damage = 12.0F;

    public MagicSphere(Level level, LivingEntity owner, LivingEntity target) {
        super(ModEntities.MAGIC_SPHERE.get(), level);
        this.owner = owner;
        this.target = target;
        this.setPos(owner.getEyePosition().x, owner.getEyePosition().y, owner.getEyePosition().z);
        double dx = target.getX() - this.getX();
        double dy = target.getY() + target.getEyeHeight() * 0.5 - this.getY();
        double dz = target.getZ() - this.getZ();
        this.shoot(dx, dy, dz, 1.5f, 0);
    }

    public MagicSphere(Level level, LivingEntity owner, LivingEntity target, float damage) {
        this(level, owner, target);
        this.damage = damage;
    }

    public MagicSphere(EntityType<? extends MagicSphere> type, Level level) {
        super(type, level);
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!this.level().isClientSide && result.getEntity() instanceof LivingEntity hit && hit != owner) {
            DamageSource damageSource = this.damageSources().indirectMagic(this, owner);
            hit.hurt(damageSource, damage);
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        this.discard();
    }

    @Override
    protected void defineSynchedData() {}
}