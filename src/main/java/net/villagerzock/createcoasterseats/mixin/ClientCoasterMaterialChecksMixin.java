package net.villagerzock.createcoasterseats.mixin;

import net.minecraft.resources.ResourceLocation;
import net.villagerzock.createcoasterseats.track.CoasterMaterialCompatibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = {
    "dev.silvergold.simulatedcoasters.client.cart.CoasterCartMidTrackPlacementClient",
    "dev.silvergold.simulatedcoasters.client.track.AnchorPeerCurveOutlineSupport",
    "dev.silvergold.simulatedcoasters.client.track.AnchorPeerCurvePick",
    "dev.silvergold.simulatedcoasters.client.track.AnchorPeerTrackCurveBerRender",
    "dev.silvergold.simulatedcoasters.client.track.AnchorPeerTrackCurveVisual$BezierTrackVisual",
    "dev.silvergold.simulatedcoasters.client.track.BezierHandleDragManager",
    "dev.silvergold.simulatedcoasters.client.track.BezierHandleOverlay",
    "dev.silvergold.simulatedcoasters.client.track.CoasterBezierPlacementSpineDebugRenderer",
    "dev.silvergold.simulatedcoasters.client.track.CoasterCurveRailInstancing",
    "dev.silvergold.simulatedcoasters.track.CoasterTrackCurvePickContext",
    "dev.silvergold.simulatedcoasters.track.anchor.AnchorPeerCurveClientIndex"
}, remap = false)
public abstract class ClientCoasterMaterialChecksMixin {
    @Redirect(
        method = "*",
        at = @At(value = "INVOKE", target =
            "Lnet/minecraft/resources/ResourceLocation;equals(Ljava/lang/Object;)Z"),
        require = 0
    )
    private static boolean createcoasterseats$coasterCompatibleId(
        ResourceLocation id, Object comparedWith
    ) {
        return CoasterMaterialCompatibility.compatible(id, comparedWith);
    }
}
