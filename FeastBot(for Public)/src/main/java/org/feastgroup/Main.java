package org.feastgroup;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 *This is the main class for a whole project.Need to keep simple this class.
 * It contains jda builder and custom bot status.
 */
public class Main {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault("TOKEN")
                .enableIntents(GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MODERATION,
                        GatewayIntent.GUILD_WEBHOOKS,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS)
                .build();
        OnlineStatus status = OnlineStatus.ONLINE;
        jda.getPresence().setStatus(status);
        jda.getPresence().setActivity(Activity.listening("Muğam"));
        jda.addEventListener(new ServerListener());
        SlashCommands.commands(jda);

    }
}