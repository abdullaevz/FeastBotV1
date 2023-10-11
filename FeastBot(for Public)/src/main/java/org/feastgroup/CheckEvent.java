package org.feastgroup;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import java.util.ArrayList;


/**
 * This class contains check methods.These methods check all events and returns relevant true or false value
 */
public class CheckEvent {

    /**
     * @see Channels
     * @param e     is Channels enum.
     * @param guild is server that event happened on.
     * @return MessageChannel depending on how e parameter is used.
     * We need to declare all channels id in this method. This is more useful than adding one by one,
     * So after we need to use only enums in other methods.
     */
    public static MessageChannel checkChannel(Channels e, Guild guild) {
        if (e == Channels.READY_INFO_CHANNEL) {
            return guild.getTextChannelById("1161001616929984582");
        } else if (e == Channels.ADVICE_CHANNEL) {
            return guild.getTextChannelById("1161328097430339726");
        } else if (e == Channels.WARN_INFO_CHANNEL) {
            return guild.getTextChannelById("1159184211924762684");
        } else if (e == Channels.JOIN_INFO_CHANNEL)
            return guild.getTextChannelById("1161001616929984582");
        return null;
    }


    /**
     * @param message is already sent by member.
     * @param event is a message event that received.
     * @return true if message contains sensitive content.
     * Checks sent message and returns true or false value for content.
     */
    public boolean checkContent(String message, MessageReceivedEvent event) {
        ArrayList<String> bannedWords = new ArrayList<>();
        bannedWords.add("badword");

        String[] msg = message.split(" ");
        for (String s : msg) {
            if (!event.getAuthor().isBot()) {
                if (bannedWords.contains(s)) {
                    return true;
                } else {
                    for (String string : bannedWords) {
                        if (s.contains(string)) return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * @param message is already sent by member.
     * @param event is message event that received.
     * @return true if a message starts with "!".(This is a bot command prefix).
     */
    public boolean checkCommand(String message, MessageReceivedEvent event) {
        return message.startsWith("!") && !event.getAuthor().isBot();
    }

    /**
     * @param msg is already sent by member.
     * @param event is a message received event.
     *              This method will work when checkCommand returns true value cause this method checks command message content.
     */
    public void checkCommandMessage(String msg, MessageReceivedEvent event) {
        if (msg.equals("!clear")) {
            System.out.println("This is a !clear command");
        } else {
            event.getChannel().sendMessage("Wrong command ").queue();
        }

    }


    /**
     * This method is no used, cause slash commands already exist.
     *
     * @param message already typed message by user.
     * @return true if the message specific fun message.
     */
    public boolean checkFunMesage(String message) {
        return message.equalsIgnoreCase("fun message");
    }


    /**
     * @param guild is server that event happened on.
     * @param channel is message sent channel.
     * @return true if channel is stated channel.
     * @see Channels
     */
    public boolean checkAdviceMessage(Guild guild, MessageChannel channel) {
        return channel == checkChannel(Channels.ADVICE_CHANNEL, guild);
    }
}
