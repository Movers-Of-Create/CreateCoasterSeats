package net.villagerzock.createcoasterseats.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.villagerzock.createcoasterseats.Createcoasterseats;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Createcoasterseats.MOD_ID);

    public static final DeferredItem<BlockItem> SECURABLE_SEAT = ITEMS.register(
        "securable_seat",
        () -> new BlockItem(ModBlocks.SECURABLE_SEAT.get(), new Item.Properties())
    );

    private ModItems() {
    }
}
