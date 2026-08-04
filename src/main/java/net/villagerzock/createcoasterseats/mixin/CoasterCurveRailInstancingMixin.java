package net.villagerzock.createcoasterseats.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.trains.track.BezierConnection;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.silvergold.simulatedcoasters.client.track.CoasterCurveRailInstancing;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.villagerzock.createcoasterseats.registry.CustomCoasterTrackMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(value = CoasterCurveRailInstancing.class, remap = false)
public abstract class CoasterCurveRailInstancingMixin {
    @Inject(method = "curveTieGapCount(Lcom/simibubi/create/content/trains/track/BezierConnection;)I",
        at = @At("RETURN"), cancellable = true)
    private static void createcoasterseats$doubleWoodenTieAllocation(
        BezierConnection connection, CallbackInfoReturnable<Integer> cir
    ) {
        if (CustomCoasterTrackMaterials.isCustomCoasterMaterial(connection.getMaterial()))
            cir.setReturnValue(cir.getReturnValue() * 2);
    }

    @Redirect(
        method = "applyCurveTies",
        at = @At(value = "INVOKE", target =
            "Ldev/silvergold/simulatedcoasters/client/track/CoasterCurveRailInstancing;curveTieGapCount(D)I")
    )
    private static int createcoasterseats$doubleWoodenTiePlacement(
        double length, TransformedInstance[] ties, PoseStack poseStack,
        BezierConnection connection, Level level, int light, List<?> samples,
        Vec3 firstPos, Vec3 firstTangent, Vec3 firstLateral,
        Vec3 lastPos, Vec3 lastTangent, Vec3 lastLateral
    ) {
        int gaps = Math.max(1, (int) Math.round(length));
        return CustomCoasterTrackMaterials.isCustomCoasterMaterial(connection.getMaterial())
            ? gaps * 2
            : gaps;
    }
}
