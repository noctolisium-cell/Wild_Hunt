package com.WildHunt.WildHuntMod.client.renderer;

import com.WildHunt.WildHuntMod.entity.projectile.MagicSphere;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MagicSphereRenderer extends EntityRenderer<MagicSphere> {
    public MagicSphereRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(MagicSphere entity) {
        return new ResourceLocation("minecraft", "textures/item/snowball.png");
    }
}