package versus;

import discord4j.core.DiscordClient;
import discord4j.core.DiscordClientBuilder;
import versus.controller.D4JController;

public class VersusMain {
    public static void main(String[] args) {
        DiscordClient client = DiscordClientBuilder.create(args[0]).build(); // command line token
        D4JController controller = new D4JController(client);

        controller.run();
    }
}
