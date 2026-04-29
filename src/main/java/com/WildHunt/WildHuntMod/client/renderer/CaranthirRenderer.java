package com.WildHunt.WildHuntMod.client.renderer;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.CaranthirEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CaranthirRenderer extends GeoEntityRenderer<CaranthirEntity> {
    public CaranthirRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(WildHuntMod.MOD_ID, "caranthir")));
    }

    @Override
    public ResourceLocation getTextureLocation(CaranthirEntity animatable) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "textures/entity/caranthir.png");
    }
}