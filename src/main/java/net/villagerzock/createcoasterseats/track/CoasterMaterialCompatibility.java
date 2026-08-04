package net.villagerzock.createcoasterseats.track;

import dev.silvergold.simulatedcoasters.CoasterTrackMaterials;
import net.minecraft.resources.ResourceLocation;
import net.villagerzock.createcoasterseats.registry.CustomCoasterTrackMaterials;

public final class CoasterMaterialCompatibility {
    private CoasterMaterialCompatibility() {
    }

    public static boolean compatible(ResourceLocation id, Object comparedWith) {
        if (id.equals(comparedWith))
            return true;
        if (!(comparedWith instanceof ResourceLocation other))
            return false;

        ResourceLocation coaster = CoasterTrackMaterials.COASTER.id;
        ResourceLocation wooden = CustomCoasterTrackMaterials.WOODEN.id;
        return id.equals(coaster) && other.equals(wooden)
            || id.equals(wooden) && other.equals(coaster);
    }
}
