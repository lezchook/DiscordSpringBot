package com.leshchenko.starcraftbot.command;

import com.leshchenko.starcraftbot.domain.User;
import com.leshchenko.starcraftbot.service.DataBaseService;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Iterator;
import java.util.List;

public class Status extends ListenerAdapter {

    private DataBaseService dataBaseService;

    public Status(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("status")) {
                List<User> users = dataBaseService.showTable();
                Iterator<User> iterator = users.iterator();
                int i = 0;
                if (!iterator.hasNext()) {
                    event.reply("Таблица пустая").submit();
                    return;
                }
                String message = "";
                while (iterator.hasNext()) {
                    message = message + users.get(i).getUsername() + ": " + users.get(i).getCount() + '\n';
                    i++;
                    iterator.next();
                }
                event.reply(message).submit();
        }
    }

}
