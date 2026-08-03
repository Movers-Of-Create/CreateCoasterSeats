package net.villagerzock.createcoasterseats.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.villagerzock.createcoasterseats.registry.ModBlockEntities;

public final class SecurableSeatBlockEntity extends BlockEntity {
    public SecurableSeatBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SECURABLE_SEAT.get(), pos, state);
    }
}
