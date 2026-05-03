package band.kessoku.blueteath.api.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;

import java.util.Objects;

public class BlueteathModuleData {
    public static final Codec<BlueteathModuleData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Identifier.CODEC.fieldOf("id").forGetter(BlueteathModuleData::getId)
            ).apply(instance, BlueteathModuleData::new)
    );

    public static final StreamCodec<ByteBuf, BlueteathModuleData> STREAM_CODEC = StreamCodec.composite(
            Identifier.STREAM_CODEC, BlueteathModuleData::getId,
            BlueteathModuleData::new
    );

    private Identifier id;

    public BlueteathModuleData(Identifier id) {
        this.id = id;
    }

    public Identifier getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BlueteathModuleData that = (BlueteathModuleData) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BlueteathModule{" +
                "id=" + id +
                '}';
    }
}
