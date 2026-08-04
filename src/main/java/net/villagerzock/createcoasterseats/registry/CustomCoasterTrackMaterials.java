package net.villagerzock.createcoasterseats.registry;

import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.content.trains.track.TrackMaterialFactory;
import dev.silvergold.simulatedcoasters.SimulatedCoastersBlocks;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.villagerzock.createcoasterseats.Createcoasterseats;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public final class CustomCoasterTrackMaterials {
    private static final Set<TrackMaterial> MATERIALS =
        Collections.newSetFromMap(new IdentityHashMap<>());

    public static final TrackMaterial WOODEN = register(TrackMaterialFactory
        .make(ResourceLocation.fromNamespaceAndPath(Createcoasterseats.MOD_ID, "coaster_track"))
        .lang("Coaster Track")
        .block(NonNullSupplier.lazy(() ->
            () -> (TrackBlock) SimulatedCoastersBlocks.COASTER_TRACK_MATERIAL.get()))
        .particle(ResourceLocation.fromNamespaceAndPath("create", "block/industrial_iron_block"))
        // Reuse Create's built-in andesite track geometry instead of looking
        // for models below createcoasterseats:block/track/coaster_track/.
        .defaultModels()
        .noRecipeGen()
        .build());

    public static <T extends TrackMaterial> T register(T material) {
        MATERIALS.add(material);
        return material;
    }

    public static boolean isCustomCoasterMaterial(TrackMaterial material) {
        return MATERIALS.contains(material);
    }

    private CustomCoasterTrackMaterials() {
    }

}
