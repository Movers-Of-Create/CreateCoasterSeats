package net.villagerzock.createcoasterseats.mixin;

import net.minecraft.resources.ResourceLocation;
import net.villagerzock.createcoasterseats.track.CoasterMaterialCompatibility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = {
    "dev.silvergold.simulatedcoasters.track.BezierBankBlend",
    "dev.silvergold.simulatedcoasters.track.CoasterBezierHandleEdit",
    "dev.silvergold.simulatedcoasters.track.CoasterBezierRailFrames",
    "dev.silvergold.simulatedcoasters.track.CoasterCurveDyeInteraction",
    "dev.silvergold.simulatedcoasters.track.CoasterOpenEndExtension",
    "dev.silvergold.simulatedcoasters.track.CoasterPeerCurveHandleLengths",
    "dev.silvergold.simulatedcoasters.track.CoasterTrackCurveDrops",
    "dev.silvergold.simulatedcoasters.track.CoasterTrackPlacement",
    "dev.silvergold.simulatedcoasters.track.anchor.AnchorPeerFakeTracks",
    "dev.silvergold.simulatedcoasters.track.anchor.CoasterAnchorpointBlockEntity",
    "dev.silvergold.simulatedcoasters.track.cart.CoasterCartMidTrackPlacement",
    "dev.silvergold.simulatedcoasters.track.graph.CoasterPathGraphManager",
    "dev.silvergold.simulatedcoasters.track.graph.CoasterPathTrackFrame"
}, remap = false)
public abstract class CoasterMaterialChecksMixin {
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
