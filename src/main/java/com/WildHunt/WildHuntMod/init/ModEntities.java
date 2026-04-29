package com.WildHunt.WildHuntMod.init;

import com.WildHunt.WildHuntMod.WildHuntMod;
import com.WildHunt.WildHuntMod.entity.*;
import com.WildHunt.WildHuntMod.entity.projectile.MagicSphere;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.WildHunt.WildHuntMod.entity.projectile.FrozenProjectile;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WildHuntMod.MOD_ID);

    public static final RegistryObject<EntityType<EredinEntity>> EREDIN = ENTITY_TYPES.register("eredin",
            () -> EntityType.Builder.of(EredinEntity::new, MobCategory.MONSTER)
                    .sized(1.2f, 2.8f)
                    .fireImmune()
                    .build("eredin"));

    public static final RegistryObject<EntityType<ImlerithEntity>> IMLERITH = ENTITY_TYPES.register("imlerith",
            () -> EntityType.Builder.of(ImlerithEntity::new, MobCategory.MONSTER)
                    .sized(1.5f, 3.2f)
                    .fireImmune()
                    .build("imlerith"));

    public static final RegistryObject<EntityType<CaranthirEntity>> CARANTHIR = ENTITY_TYPES.register("caranthir",
            () -> EntityType.Builder.of(CaranthirEntity::new, MobCategory.MONSTER)
                    .sized(1.0f, 2.4f)
                    .build("caranthir"));

    public static final RegistryObject<EntityType<MagicSphere>> MAGIC_SPHERE = ENTITY_TYPES.register("magic_sphere",
            () -> EntityType.Builder.<MagicSphere>of(MagicSphere::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("magic_sphere"));
    public static final RegistryObject<EntityType<FrozenProjectile>> FROZEN_PROJECTILE = ENTITY_TYPES.register("frozen_projectile",
            () -> EntityType.Builder.<FrozenProjectile>of(FrozenProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .build("frozen_projectile"));

}