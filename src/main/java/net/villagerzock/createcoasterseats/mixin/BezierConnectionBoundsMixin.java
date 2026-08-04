package net.villagerzock.createcoasterseats.mixin;

import com.simibubi.create.content.trains.track.BezierConnection;
import dev.silvergold.simulatedcoasters.track.CoasterBezierRailFrames;
import dev.silvergold.simulatedcoasters.track.CoasterTrackGauge;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.villagerzock.createcoasterseats.registry.CustomCoasterTrackMaterials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BezierConnection.class)
public abstract class BezierConnectionBoundsMixin {
    @Inject(method = "getBounds", at = @At("RETURN"), cancellable = true)
    private void createcoasterseats$moveCustomCoasterBounds(CallbackInfoReturnable<AABB> cir) {
        BezierConnection connection = (BezierConnection) (Object) this;
        if (!CustomCoasterTrackMaterials.isCustomCoasterMaterial(connection.getMaterial()))
            return;

        Vec3 up = CoasterBezierRailFrames.faceUpAt(connection, 0.5F, null);
        if (up.lengthSqr() < 1.0E-10D)
            up = new Vec3(0, 1, 0);
        Vec3 offset = up.normalize().scale(CoasterTrackGauge.curveInteractionLift()).add(0, -0.125D, 0);
        cir.setReturnValue(cir.getReturnValue().move(offset));
    }
}
