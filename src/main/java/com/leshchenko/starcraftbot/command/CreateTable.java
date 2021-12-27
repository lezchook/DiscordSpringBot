package com.leshchenko.starcraftbot.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CreateTable extends ListenerAdapter {

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("start")) {
            event.reply("Создана новая таблица\n" +
                    "┌─────────┬──────────────┐\n" +
                    " |    Имя игрока   │    Количество побед     │\n" +
                    "└─────────┴──────────────┘").submit();
        }
    }
}
