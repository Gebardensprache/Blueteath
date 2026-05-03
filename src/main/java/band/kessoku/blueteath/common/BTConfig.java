package band.kessoku.blueteath.common;

import band.kessoku.blueteath.Blueteath;
import band.kessoku.blueteath.common.items.transceiver.TransceiverTier;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class BTConfig {
    public static final Server SERVER_CONFIG;
    public static final ModConfigSpec SERVER_CONFIG_SPEC;

    public static final Client CLIENT_CONFIG;
    public static final ModConfigSpec CLIENT_CONFIG_SPEC;

    static {
        Pair<Server, ModConfigSpec> serverSpec =
                new ModConfigSpec.Builder().configure(Server::new);

        SERVER_CONFIG = serverSpec.getLeft();
        SERVER_CONFIG_SPEC = serverSpec.getRight();

        Pair<Client, ModConfigSpec> clientSpec =
                new ModConfigSpec.Builder().configure(Client::new);

        CLIENT_CONFIG = clientSpec.getLeft();
        CLIENT_CONFIG_SPEC = clientSpec.getRight();
    }

    @SuppressWarnings("ClassCanBeRecord")
    public static class Client {
        public final ModConfigSpec.BooleanValue displayConnectingPlayer;

        public Client(ModConfigSpec.Builder builder) {
            displayConnectingPlayer = builder.comment("Render name and player head icon into main HUD.")
                    .define("display_connecting_player", true);
        }
    }

    public static class Server {
        public final ModConfigSpec.IntValue defaultDistance;
        public final ModConfigSpec.IntValue copperTransceiverDistanceMultiple;
        public final ModConfigSpec.IntValue ironTransceiverDistanceMultiple;
        public final ModConfigSpec.IntValue goldenTransceiverDistanceMultiple;
        public final ModConfigSpec.IntValue diamondTransceiverDistanceMultiple;
        public final ModConfigSpec.IntValue netheriteTransceiverDistanceMultiple;

        public Server(ModConfigSpec.Builder builder) {
            defaultDistance = builder.comment("Default distance of Blueteath connection.", "Exceeding the distance will disconnection.", "Set to 0 to disable distance limit.")
                    .defineInRange("default_distance", Blueteath.isTeaCon() ? 0 : 100, 0, Integer.MAX_VALUE);

            builder.comment("Distance multiple of each tier transceiver.");
            copperTransceiverDistanceMultiple = builder.defineInRange("copper_transceiver_distance_multiple", 2, 1, 256);
            ironTransceiverDistanceMultiple = builder.defineInRange("iron_transceiver_distance_multiple", 4, 1, 256);
            goldenTransceiverDistanceMultiple = builder.defineInRange("golden_transceiver_distance_multiple", 8, 1, 256);
            diamondTransceiverDistanceMultiple = builder.defineInRange("diamond_transceiver_distance_multiple", 16, 1, 256);
            netheriteTransceiverDistanceMultiple = builder.defineInRange("netherite_transceiver_distance_multiple", 32, 1, 256);
        }

        public int getDistance(TransceiverTier tier) {
            return defaultDistance.get() * switch (tier) {
                case NONE -> 1;
                case COPPER -> copperTransceiverDistanceMultiple.get();
                case IRON -> ironTransceiverDistanceMultiple.get();
                case GOLDEN -> goldenTransceiverDistanceMultiple.get();
                case DIAMOND -> diamondTransceiverDistanceMultiple.get();
                case NETHERITE -> netheriteTransceiverDistanceMultiple.get();
            };
        }
    }

}
