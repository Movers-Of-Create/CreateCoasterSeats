package net.villagerzock.createcoasterseats.mixin;

import com.simibubi.create.content.trains.track.TrackMaterial;
import dev.silvergold.simulatedcoasters.CoasterTrackMaterials;
import dev.silvergold.simulatedcoasters.track.CoasterTrackPlacement;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.villagerzock.createcoasterseats.item.CustomCoasterTrackItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CoasterTrackPlacement.class, remap = false)
public abstract class CoasterTrackPlacementMixin {
    private static final String COASTER_MATERIAL =
        "Ldev/silvergold/simulatedcoasters/CoasterTrackMaterials;COASTER:" +
            "Lcom/simibubi/create/content/trains/track/TrackMaterial;";

    @Redirect(
        method = "tryConnect",
        at = @At(value = "FIELD", target = COASTER_MATERIAL, opcode = 178, ordinal = 0)
    )
    private static TrackMaterial createcoasterseats$previewMaterial(
        Level level, Player player, BlockPos pos, BlockState state, ItemStack stack,
        boolean maximiseTurn, boolean front, boolean girder
    ) {
        return createcoasterseats$materialFrom(stack);
    }

    @Redirect(
        method = "tryConnectAnchorToAnchorAttempt",
        at = @At(value = "FIELD", target = COASTER_MATERIAL, opcode = 178)
    )
    private static TrackMaterial createcoasterseats$placedMaterial(
        Level level, Player player, BlockPos first, BlockPos second, ItemStack stack,
        boolean simulate,
        CoasterTrackPlacement.PlacementInfo placement
    ) {
        return createcoasterseats$materialFrom(stack);
    }

    private static TrackMaterial createcoasterseats$materialFrom(ItemStack stack) {
        if (stack.getItem() instanceof CustomCoasterTrackItem customItem)
            return customItem.getTrackMaterial();
        return CoasterTrackMaterials.COASTER;
    }
}
