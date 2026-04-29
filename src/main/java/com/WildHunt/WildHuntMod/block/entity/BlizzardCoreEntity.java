package com.WildHunt.WildHuntMod.block.entity;

import com.WildHunt.WildHuntMod.config.ModConfig;
import com.WildHunt.WildHuntMod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlizzardCoreEntity extends BlockEntity {
    private long lastEventTime = 0;

    public BlizzardCoreEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLIZZARD_CORE.get(), pos, state);
    }

    public void serverTick() {
        if (level == null || level.isClientSide) return;
        long now = level.getGameTime();
        if (lastEventTime == 0) lastEventTime = now;
        int cooldown = ModConfig.get().blizzardCooldownTicks;
        if (now - lastEventTime < cooldown) return;
        lastEventTime = now;
        startBlizzard();
        setChanged();
    }

    private void startBlizzard() {
        if (!(level instanceof ServerLevel serverLevel)) return;
        int duration = ModConfig.get().blizzardDurationSeconds;
        serverLevel.getServer().execute(() -> {
            String command = "weather snow " + duration;
            serverLevel.getServer().getCommands().performPrefixedCommand(
                    serverLevel.getServer().createCommandSourceStack(),
                    command
            );
        });
    }
}