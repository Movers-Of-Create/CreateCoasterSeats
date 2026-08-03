package net.villagerzock.createcoasterseats;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.villagerzock.createcoasterseats.event.SeatMountHandler;
import net.villagerzock.createcoasterseats.registry.ModBlockEntities;
import net.villagerzock.createcoasterseats.registry.ModBlocks;
import net.villagerzock.createcoasterseats.registry.ModItems;
import net.villagerzock.createcoasterseats.registry.ModCreativeTabs;

@Mod(Createcoasterseats.MOD_ID)
public final class Createcoasterseats {
    public static final String MOD_ID = "createcoasterseats";

    public Createcoasterseats(IEventBus modEventBus) {
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModCreativeTabs.CREATIVE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(SeatMountHandler::onEntityMount);
    }
}
