package org.feastgroup;

import net.dv8tion.jda.api.JDA;

/**
 * This class is graded for slash commands
 */
public class SlashCommands {
    /**
     * @param jda is a
     */
    public static void commands(JDA jda) {
        jda.upsertCommand("salam", "description").setGuildOnly(true).queue();
        jda.upsertCommand("necəsən", "description").setGuildOnly(true).queue();
    }
}
