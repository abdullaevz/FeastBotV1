package org.feastgroup;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


/**
 * Works in server.Contains useful methods for some functions.
 */
public class BotInServer {


    /**
     * @param guild is server that something happened on.
     *              Sends info to a selected channel when it is ready.
     *              If you want to send a tag, you can add a member or role id.
     * @see Channels
     */
    public static void sendBotReadyInfo(Guild guild) {
        MessageChannel c = CheckEvent.checkChannel(Channels.READY_INFO_CHANNEL, guild);
        if (c != null) try {
            c.sendMessage("Bot artıq aktivdir <@+" + "Any owner or role ID" + "+>").queue();
        } catch (Exception e) {
            System.out.println("catched");
        }
    }

    /**
     * @param guild is server that something happened on.
     * @param event is Message Received event.
     * @param smsg  is message that already sent by member.
     *              This method will work when this message contains sensitive content.
     * @see Channels
     */
    public void sendWarnInfo(Guild guild, MessageReceivedEvent event, String smsg) {
        MessageChannel channel = CheckEvent.checkChannel(Channels.WARN_INFO_CHANNEL, guild);
        event.getMessage().delete().queue();
        event.getChannel().sendMessage("Qadağan olunmuş ifadə ! " + "<@" + event.getAuthor().getId() + ">").queue();
        if (channel != null) {
            if (smsg != null) {
                channel.sendMessage("<@" + event.getAuthor().getId() + ">" + " qadağan olunmuş ifadə işlətdi :  " + "***" + smsg + "***").queue();

            }
        }
    }

    /**
     * @param event is member join event.
     * @param guild is server that event happened on.
     *              This method will work when someone joins a server, sends a welcome message that contains this member tag and server member count.
     * @see Channels
     */
    public void sendJoinInfo(GuildMemberJoinEvent event, Guild guild) {
        MessageChannel welcomechannel = CheckEvent.checkChannel(Channels.JOIN_INFO_CHANNEL, guild);
        if (welcomechannel != null) {
            welcomechannel.sendMessage("<@" + event.getMember().getId() + ">" + " aramıza qatıldı.Səninlə birliktə " + guild.getMemberCount() + " nəfərik").queue();
        }
    }

    /**
     * @param e is a message event that received.
     *          This method will react to a message on selected channel.Useful for advice channels.
     */
    public void reactAdviceMessage(MessageReceivedEvent e) {
        Emoji like = Emoji.fromUnicode("U+1F44D");
        Emoji disslike = Emoji.fromUnicode("U+1F44E");
        e.getMessage().addReaction(like).queue();
        e.getMessage().addReaction(disslike).queue();

    }

    /**
     * @param event is slash command interaction event.
     *              Checks slash commands and send relevant respond.
     */
    public static void sendSlashReply(SlashCommandInteractionEvent event) {
        if (event.getFullCommandName().equals("salam")) {
            event.reply("aleykum salam").queue();

        } else if (event.getFullCommandName().equals("necəsən")) {
            event.reply("buna şükür sən necəsən").queue();
        } else if (event.getFullCommandName().equals("hərkəsə salam")) {
            event.reply("Salam millət necəsiz @everyone").queue();

        }

    }

    /**
     * @param guild is server that something happened on.
     *              This method is to show total member count on a selected voice channel.
     */
    public static void showMemberCount(Guild guild) {
        VoiceChannel voice = guild.getVoiceChannelById("1161358966459203644");
        if (voice != null) {

            voice.getManager().setName("Üzv sayı " + Integer.toString(guild.getMemberCount())).queue();
            System.out.println(voice.getName());
        }
    }
}




