package band.kessoku.blueteath.common;

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
        public Client(ModConfigSpec.Builder builder) {
        }
    }

    @SuppressWarnings("ClassCanBeRecord")
    public static class Server {
        public final ModConfigSpec.IntValue defaultDistance;

        public Server(ModConfigSpec.Builder builder) {
            defaultDistance = builder.defineInRange("defaultDistance", 100, 1, Integer.MAX_VALUE);
        }
    }

}
