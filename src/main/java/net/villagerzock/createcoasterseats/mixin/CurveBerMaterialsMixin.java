package net.villagerzock.createcoasterseats.mixin;

import com.simibubi.create.content.trains.track.BezierConnection;
import com.simibubi.create.content.trains.track.TrackMaterial;
import dev.silvergold.simulatedcoasters.CoasterTrackMaterials;
import dev.silvergold.simulatedcoasters.track.anchor.CoasterAnchorpointBlockEntity;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets =
    "dev.silvergold.simulatedcoasters.client.track.AnchorPeerTrackCurveBerRender$CurveBerMaterials",
    remap = false)
public abstract class CurveBerMaterialsMixin {
    private static final String COASTER_MATERIAL =
        "Ldev/silvergold/simulatedcoasters/CoasterTrackMaterials;COASTER:" +
            "Lcom/simibubi/create/content/trains/track/TrackMaterial;";

    @Redirect(
        method = "from",
        at = @At(value = "FIELD", target = COASTER_MATERIAL, opcode = 178)
    )
    private static TrackMaterial createcoasterseats$useCurveMaterial(
        CoasterAnchorpointBlockEntity anchor, BlockPos peer
    ) {
        BezierConnection connection = anchor.getAnchorPeerCurvesView().get(peer);
        return connection == null ? CoasterTrackMaterials.COASTER : connection.getMaterial();
    }
}
