package band.kessoku.blueteath;

import band.kessoku.blueteath.common.BTConfig;
import band.kessoku.blueteath.common.attachments.BTAttachments;
import band.kessoku.blueteath.common.items.BTItems;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Blueteath.MOD_ID)
public class Blueteath {

    public static final String MOD_ID = "blueteath";
    public static final String MOD_NAME = "Blueteath";
    public static final String MOD_VERSION = FMLLoader.getCurrent().getLoadingModList().getModFileById(MOD_ID).versionString();
    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public Blueteath(IEventBus bus, ModContainer container, Dist dist) {
        LOGGER.info("Blueteath v{} is connecting to your {}...", MOD_VERSION, dist.isClient() ? "client" : "server");

        container.registerConfig(ModConfig.Type.SERVER, BTConfig.SERVER_CONFIG_SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, BTConfig.CLIENT_CONFIG_SPEC);
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        BTAttachments.register(bus);
        BTItems.register(bus);
    }

    public static Identifier asId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    @SuppressWarnings("ConstantValue")
    public static boolean isTeaCon() {
        return MOD_VERSION.endsWith("-teacon");
    }

}
