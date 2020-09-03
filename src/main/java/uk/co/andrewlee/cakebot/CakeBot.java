package uk.co.andrewlee.cakebot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.andrewlee.cakebot.discord.BotSystem;

import java.nio.file.Path;
import java.nio.file.Paths;
import uk.co.andrewlee.cakebot.clients.aoe.AgeOfEmpiresBotClient;

public class CakeBot {

  private static final Logger logger = LoggerFactory.getLogger(CakeBot.class);

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      logger.info("Usage: CakeBot [DiscordToken] [SaveDirectory].");
      System.exit(1);
    }

    Path saveDirectory = Paths.get(args[1]);

    BotSystem botSystem = BotSystem.create(args[0]);

    AgeOfEmpiresBotClient ageOfEmpiresBotClient = AgeOfEmpiresBotClient
        .create(botSystem, 10, saveDirectory);
    botSystem.registerBotClient(ageOfEmpiresBotClient);

    botSystem.start();
  }
}
