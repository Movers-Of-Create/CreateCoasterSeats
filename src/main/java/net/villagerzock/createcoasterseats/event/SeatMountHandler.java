package net.villagerzock.createcoasterseats.event;

import com.simibubi.create.content.contraptions.actors.seat.SeatEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.EntityMountEvent;
import net.villagerzock.createcoasterseats.block.ISecurableSeat;

import java.util.Map;
import java.util.WeakHashMap;

public final class SeatMountHandler {
    private static final Map<Player, Long> LAST_LOCKED_MESSAGE = new WeakHashMap<>();
    private static final long MESSAGE_COOLDOWN_TICKS = 20;

    private SeatMountHandler() {
    }

    public static void onEntityMount(EntityMountEvent event) {
        if (!(event.getEntityMounting() instanceof Player player))
            return;

        if (event.isDismounting()) {
            if (isSecuredSeat(event.getEntityBeingMounted(), event.getLevel())){
                event.setCanceled(true);
                preventDismountForPlayer(player);
            }

            return;
        }

        if (isSecuredSeat(event.getEntityBeingMounted(), event.getLevel())
                || isSecuredSeat(player.getVehicle(), event.getLevel())) {
            event.setCanceled(true);
            preventMountForPlayer(player);
        }
    }

    private static boolean isSecuredSeat(Entity vehicle, Level level) {
        if (!(vehicle instanceof SeatEntity seat))
            return false;

        BlockPos pos = seat.blockPosition();
        BlockState state = level.getBlockState(pos);
        return state.getBlock() instanceof ISecurableSeat securableSeat
            && securableSeat.isSecured(state, pos, level);
    }

    private static void preventDismountForPlayer(Player player) {
        sendLockedMessage(player, "message.createcoasterseats.seat_locked_exit");
    }

    private static void preventMountForPlayer(Player player) {
        sendLockedMessage(player, "message.createcoasterseats.seat_locked_enter");
    }

    private static void sendLockedMessage(Player player, String translationKey) {
        if (player.level().isClientSide)
            return;

        long gameTime = player.level().getGameTime();
        Long lastMessage = LAST_LOCKED_MESSAGE.get(player);
        if (lastMessage != null && gameTime - lastMessage < MESSAGE_COOLDOWN_TICKS)
            return;

        LAST_LOCKED_MESSAGE.put(player, gameTime);
        player.displayClientMessage(
            Component.translatable(translationKey),
            true
        );
    }
}
