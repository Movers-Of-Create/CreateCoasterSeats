package net.villagerzock.createcoasterseats.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ISecurableSeat {
    boolean isSecured(BlockState state, BlockPos pos, Level level);
}
