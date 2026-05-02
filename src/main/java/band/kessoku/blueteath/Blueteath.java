package band.kessoku.blueteath;

import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(Blueteath.MOD_ID)
public class Blueteath {

    public static final String MOD_ID = "blueteath";
    public static final String MOD_NAME = "Blueteath";
    public static final String MOD_VERSION = "1.0.0-teacon";

    public Blueteath(IEventBus bus, ModContainer modContainer, Dist dist) {

    }

    public static Identifier asId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

}
