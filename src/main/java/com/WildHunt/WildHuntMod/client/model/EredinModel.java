package com.WildHunt.WildHuntMod.client.model;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.EredinEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EredinModel extends GeoModel<EredinEntity> {
    @Override
    public ResourceLocation getModelResource(EredinEntity object) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "geo/eredin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EredinEntity object) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "textures/entity/eredin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EredinEntity animatable) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "animations/eredin.animation.json");
    }
}