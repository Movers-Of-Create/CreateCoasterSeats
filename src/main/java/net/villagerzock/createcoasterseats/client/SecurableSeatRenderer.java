package net.villagerzock.createcoasterseats.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.content.redstone.link.LinkRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.villagerzock.createcoasterseats.block.entity.SecurableSeatBlockEntity;

public final class SecurableSeatRenderer implements BlockEntityRenderer<SecurableSeatBlockEntity> {
    public SecurableSeatRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(SecurableSeatBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight, int packedOverlay) {
        LinkRenderer.renderOnBlockEntity(
            blockEntity,
            partialTick,
            poseStack,
            buffer,
            packedLight,
            packedOverlay
        );
    }
}
