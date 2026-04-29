package com.WildHunt.WildHuntMod.entity.projectile;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.init.ModEntities;
import com.WildHunt.WildHuntMod.init.ModParticles;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.Random;

public class FrozenProjectile extends ThrowableProjectile implements GeoEntity {
    private static final Random RANDOM = new Random();
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private LivingEntity owner;
    private float damage;
    private int slowDuration;
    private int particleCooldown = 0;

    // Конструктор для стрельбы из посоха
    public FrozenProjectile(Level level, LivingEntity owner, LivingEntity target, float damage, int slowDuration) {
        super(ModEntities.FROZEN_PROJECTILE.get(), level);
        this.owner = owner;
        this.damage = damage;
        this.slowDuration = slowDuration;
        this.setPos(owner.getEyePosition().x, owner.getEyePosition().y, owner.getEyePosition().z);
        double dx = target.getX() - this.getX();
        double dy = target.getY() + target.getEyeHeight() * 0.5 - this.getY();
        double dz = target.getZ() - this.getZ();
        this.shoot(dx, dy, dz, 1.5f, 0);
    }

    // Конструктор для регистрации сущности
    public FrozenProjectile(EntityType<? extends FrozenProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (particleCooldown <= 0) {
                // Кастомная частица ICE_SPARK
                if (ModParticles.ICE_SPARK.isPresent()) {
                    this.level().addParticle(ModParticles.ICE_SPARK.get(), this.getX(), this.getY(), this.getZ(),
                            (RANDOM.nextDouble() - 0.5) * 0.1, (RANDOM.nextDouble() - 0.5) * 0.1, (RANDOM.nextDouble() - 0.5) * 0.1);
                }
                // Стандартные ледяные частицы
                for (int i = 0; i < 2; i++) {
                    this.level().addParticle(ParticleTypes.SNOWFLAKE,
                            this.getX() + (RANDOM.nextDouble() - 0.5) * 0.3,
                            this.getY() + (RANDOM.nextDouble() - 0.5) * 0.3,
                            this.getZ() + (RANDOM.nextDouble() - 0.5) * 0.5,
                            0, 0, 0);
                }
                particleCooldown = 4;
            } else {
                particleCooldown--;
            }
        }
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!this.level().isClientSide && result.getEntity() instanceof LivingEntity hit && hit != owner) {
            hit.hurt(hit.damageSources().indirectMagic(this, owner), damage);
            hit.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, slowDuration, 1, false, false));

            if (this.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY(), this.getZ(), 15, 0.5, 0.5, 0.5, 0.1);
            }
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.ITEM_SNOWBALL, this.getX(), this.getY(), this.getZ(), 10, 0.3, 0.3, 0.3, 0.05);
        }
        this.discard();
    }

    @Override
    protected void defineSynchedData() {}

    // === Методы GeckoLib ===
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        // Здесь позже можно будет добавить анимацию, например, вращения
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}