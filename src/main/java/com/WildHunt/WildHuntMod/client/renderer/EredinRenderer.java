package com.WildHunt.WildHuntMod.client.renderer;

import com.WildHunt.WildHuntMod.client.model.EredinModel;
import com.WildHunt.WildHuntMod.entity.EredinEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EredinRenderer extends GeoEntityRenderer<EredinEntity> {
    public EredinRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EredinModel());
    }

    @Override
    public ResourceLocation getTextureLocation(EredinEntity animatable) {
        return new ResourceLocation("wildhunt", "textures/entity/eredin.png");
    }
}