package net.villagerzock.createcoasterseats.registry;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.villagerzock.createcoasterseats.Createcoasterseats;
import net.villagerzock.createcoasterseats.block.SecurableSeatBlock;

public final class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Createcoasterseats.MOD_ID);

    public static final DeferredBlock<SecurableSeatBlock> SECURABLE_SEAT = BLOCKS.register(
        "securable_seat",
        () -> new SecurableSeatBlock(BlockBehaviour.Properties.of().strength(1.0F), DyeColor.WHITE)
    );

    private ModBlocks() {
    }
}
