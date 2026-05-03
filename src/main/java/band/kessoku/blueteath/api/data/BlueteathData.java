package band.kessoku.blueteath.api.data;

import band.kessoku.blueteath.Blueteath;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BlueteathData {
    private static final UUID EMPTY = UUID.fromString("00000000-0000-0000-0000-000000000000");

    public static final Codec<BlueteathData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Identifier.CODEC.fieldOf("id").orElse(Blueteath.asId("empty")).forGetter(BlueteathData::getId),
                    Codec.BOOL.fieldOf("isActive").orElse(false).forGetter(BlueteathData::isActive),
                    UUIDUtil.CODEC.fieldOf("connectingObject").orElse(EMPTY).forGetter(BlueteathData::getConnectingObject),
                    BlueteathModuleData.CODEC.listOf().fieldOf("modules").orElse(new ArrayList<>()).forGetter(BlueteathData::getModules),
                    Codec.INT.fieldOf("maxModuleAmount").orElse(4).forGetter(BlueteathData::getMaxModuleAmount)
            ).apply(instance, BlueteathData::new)
    );

    public static final StreamCodec<ByteBuf, BlueteathData> STREAM_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, BlueteathData::getId,
            ByteBufCodecs.BOOL, BlueteathData::isActive,
            UUIDUtil.STREAM_CODEC, BlueteathData::getConnectingObject,
            ByteBufCodecs.fromCodec(BlueteathModuleData.CODEC.listOf()), BlueteathData::getModules,
            ByteBufCodecs.INT, BlueteathData::getMaxModuleAmount,
            BlueteathData::new
    );

    private Identifier id;
    private boolean isActive;
    private UUID connectingObject;
    private List<BlueteathModuleData> modules;
    private int maxModuleAmount;

    public BlueteathData(Identifier id, boolean isActive, UUID connectingObject, List<BlueteathModuleData> modules, int maxModuleAmount) {
        this.id = id;
        this.isActive = isActive;
        this.connectingObject = connectingObject;
        this.modules = modules;
        this.maxModuleAmount = maxModuleAmount;
    }

    public BlueteathData(Identifier id, int maxModuleAmount) {
        this(id, false, EMPTY, new ArrayList<>(), maxModuleAmount);
    }

    public Identifier getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isConnected() {
        return !this.connectingObject.equals(EMPTY);
    }

    public UUID getConnectingObject() {
        return this.connectingObject;
    }

    public void connectTo(Level level, UUID connectingObject) {
        this.connectingObject = connectingObject;
    }

    public List<BlueteathModuleData> getModules() {
        return modules;
    }

    public int getMaxModuleAmount() {
        return maxModuleAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BlueteathData that = (BlueteathData) o;
        return isActive() == that.isActive() && Objects.equals(getId(), that.getId()) && Objects.equals(getConnectingObject(), that.getConnectingObject()) && Objects.equals(getModules(), that.getModules());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isActive(), getConnectingObject(), getModules());
    }

    @Override
    public String toString() {
        return "BlueteathData{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", connectingObject=" + connectingObject +
                ", modules=" + modules +
                ", maxModuleAmount=" + maxModuleAmount +
                '}';
    }
}
