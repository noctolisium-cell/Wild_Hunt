package com.WildHunt.WildHuntMod.entity;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.client.gui.CustomBossBar;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class AbstractWildHuntBoss extends PathfinderMob implements GeoEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final CustomBossBar customBossBar;

    protected AbstractWildHuntBoss(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);

        ResourceLocation barTexture = new ResourceLocation(WildHuntMod.MOD_ID, "textures/gui/custom_boss_bar.png");
        this.customBossBar = new CustomBossBar()
                .setTexture(barTexture, 256, 32, 0, 0)
                .setSize(182, 5)
                .setName(this.getDisplayName().getString())
                .setVisible(!level.isClientSide);
    }

    protected int attackCooldown = 0;
    protected int phase = 0;

    @Override
    public void aiStep() {
        super.aiStep();
        if (attackCooldown > 0) attackCooldown--;

        float healthPercent = this.getHealth() / this.getMaxHealth();
        if (healthPercent <= 0.3f) phase = 2;
        else if (healthPercent <= 0.6f) phase = 1;
        else phase = 0;

        customBossBar.setHealthPercent(healthPercent);
        customBossBar.setName(this.getDisplayName().getString());
    }

    @Override
    public void die(DamageSource source) {
        super.die(source);
        customBossBar.setVisible(false);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "move_controller", 5, event -> {
            if (attackCooldown > 0) {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.boss.idle"));
            } else if (event.isMoving()) {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.boss.walk"));
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.boss.idle"));
            }
            return null;
        }));

        controllers.add(new AnimationController<>(this, "attack_controller", 0, event -> {
            if (attackCooldown > 0) {
                event.getController().setAnimation(RawAnimation.begin().thenPlay("animation.boss.attack"));
            } else {
                event.getController().setAnimation(RawAnimation.begin().thenLoop("animation.boss.idle"));
            }
            return null;
        }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public int getPhase() { return phase; }
    public boolean isAttacking() { return attackCooldown > 0; }
    public void setAttacking(boolean attacking) { attackCooldown = attacking ? 20 : 0; }

    public abstract void performSpecialAttack(LivingEntity target);

    public CustomBossBar getCustomBossBar() {
        return customBossBar;
    }
}