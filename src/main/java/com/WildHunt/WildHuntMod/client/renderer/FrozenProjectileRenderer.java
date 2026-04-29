package com.WildHunt.WildHuntMod.client.renderer;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.projectile.FrozenProjectile;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FrozenProjectileRenderer extends GeoEntityRenderer<FrozenProjectile> {
    public FrozenProjectileRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(WildHuntMod.MOD_ID, "ice_projectile")));
    }

    @Override
    public ResourceLocation getTextureLocation(FrozenProjectile animatable) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "textures/entity/ice_projectile.png");
    }
}