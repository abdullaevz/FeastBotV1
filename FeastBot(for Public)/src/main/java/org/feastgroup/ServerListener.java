package org.feastgroup;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;


/**
 * Listens to all events that happened in server.Contains event methods
 */
public class ServerListener extends ListenerAdapter {
    CheckEvent check = new CheckEvent();


    /**
     * @param event is a message received event
     * @see CheckEvent
     * @see BotInServer
     * This method checks all messages using check methods.
     * Cals functional methods if a statement is true
     */
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        if (check.checkCommand(message, event)) {
            check.checkCommandMessage(message, event);
        } else if (check.checkContent(message, event)) {
            BotInServer bts = new BotInServer();
            bts.sendWarnInfo(event.getGuild(), event, message);
        } else if (check.checkFunMesage(message)) {
            System.out.println(true);
        } else if (check.checkAdviceMessage(event.getGuild(), event.getChannel())) {
            BotInServer bt = new BotInServer();
            bt.reactAdviceMessage(event);
        }


    }

    /**
     * @param event is slash command event
     * @see BotInServer
     * Calls method for reply to command
     */
    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        BotInServer.sendSlashReply(event);
    }

    /**
     * @param event member join event
     * @see BotInServer
     * Calls a method for sending join info
     */
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        BotInServer bts = new BotInServer();
        bts.sendJoinInfo(event, event.getGuild());

    }

    /**
     * @param event is bot ready event
     * @see BotInServer
     * Calls a method for sending ready info
     */
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        BotInServer.sendBotReadyInfo(event.getGuild());
        BotInServer.showMemberCount(event.getGuild());
    }


}








