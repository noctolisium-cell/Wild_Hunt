package com.WildHunt.WildHuntMod.client.model;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.ImlerithEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class ImlerithModel extends GeoModel<ImlerithEntity> {
    @Override
    public ResourceLocation getModelResource(ImlerithEntity object) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "geo/imlerith.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ImlerithEntity object) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "textures/entity/imlerith.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ImlerithEntity animatable) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "animations/imlerith.animation.json");
    }
}