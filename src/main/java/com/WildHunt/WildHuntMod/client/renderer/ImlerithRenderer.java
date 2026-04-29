package com.WildHunt.WildHuntMod.client.renderer;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.ImlerithEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ImlerithRenderer extends GeoEntityRenderer<ImlerithEntity> {
    public ImlerithRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DefaultedEntityGeoModel<>(new ResourceLocation(WildHuntMod.MOD_ID, "imlerith")));
    }

    @Override
    public ResourceLocation getTextureLocation(ImlerithEntity animatable) {
        return new ResourceLocation(WildHuntMod.MOD_ID, "textures/entity/imlerith.png");
    }
}