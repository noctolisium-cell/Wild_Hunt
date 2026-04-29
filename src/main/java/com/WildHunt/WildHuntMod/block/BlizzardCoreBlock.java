package com.WildHunt.WildHuntMod.block;

import com.WildHunt.WildHuntMod.block.entity.BlizzardCoreEntity;
import com.WildHunt.WildHuntMod.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

public class BlizzardCoreBlock extends Block implements EntityBlock {
    public BlizzardCoreBlock() {
        super(Properties.of()
                .mapColor(MapColor.COLOR_CYAN)
                .strength(5.0f, 1200.0f)
                .requiresCorrectToolForDrops()
                .noOcclusion()
                .isViewBlocking((state, reader, pos) -> false)
                .pushReaction(PushReaction.NORMAL)   // исправлено
        );
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BlizzardCoreEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide ? null : (lvl, pos, st, t) -> {
            if (t instanceof BlizzardCoreEntity core) core.serverTick();
        };
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 0;
    }
}