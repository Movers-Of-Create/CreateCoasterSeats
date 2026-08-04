package net.villagerzock.createcoasterseats.item;

import com.simibubi.create.content.trains.track.TrackMaterial;
import dev.silvergold.simulatedcoasters.track.CoasterTrackItem;

import java.util.Objects;
import java.util.function.Supplier;

public class CustomCoasterTrackItem extends CoasterTrackItem {
    private final Supplier<TrackMaterial> material;

    public CustomCoasterTrackItem(Properties properties, TrackMaterial material) {
        this(properties, () -> material);
    }

    public CustomCoasterTrackItem(Properties properties, Supplier<TrackMaterial> material) {
        super(properties);
        this.material = Objects.requireNonNull(material, "material");
    }

    /**
     * Returns the material used by tracks placed with this item.
     *
     * <p>The supplier is resolved lazily so the item can safely refer to a
     * material whose backing block is registered later in mod initialization.</p>
     */
    public TrackMaterial getTrackMaterial() {
        return Objects.requireNonNull(material.get(), "The coaster track material supplier returned null");
    }
}
