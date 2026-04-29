package com.WildHunt.WildHuntMod.client.model;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.CaranthirEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class CaranthirModel extends GeoModel<CaranthirEntity> {
    @Override
    public ResourceLocation getModelResource(CaranthirEntity object) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "geo/caranthir.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CaranthirEntity object) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "textures/entity/caranthir.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CaranthirEntity animatable) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "animations/caranthir.animation.json");
    }
}