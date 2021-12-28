package com.leshchenko.starcraftbot.command;

import com.leshchenko.starcraftbot.service.DataBaseService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Reset extends ListenerAdapter {

    private DataBaseService dataBaseService;

    public Reset(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("reset")) {
            dataBaseService.deleteAll();
            event.reply("Таблица сброшена").submit();
        }
    }

}
