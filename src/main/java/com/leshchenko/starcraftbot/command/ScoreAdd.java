package com.leshchenko.starcraftbot.command;

import com.leshchenko.starcraftbot.service.DataBaseService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ScoreAdd extends ListenerAdapter {

    private DataBaseService dataBaseService;

    public ScoreAdd(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("score")) {
            if (!dataBaseService.containCheck(event.getUser().getName())) {
                event.reply("В начале Вам нужно добавиться в таблицу").submit();
            } else {
                dataBaseService.addScore(event.getUser().getName());
                event.reply("Добавлено очко: " + event.getUser().getName()).submit();
            }
        }
    }

}
