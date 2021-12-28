package com.leshchenko.starcraftbot.command;

import com.leshchenko.starcraftbot.domain.User;
import com.leshchenko.starcraftbot.repo.UserRepo;
import com.leshchenko.starcraftbot.service.DataBaseService;
import net.dv8tion.jda.api.events.application.ApplicationCommandCreateEvent;
import net.dv8tion.jda.api.events.application.GenericApplicationCommandEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.jws.soap.SOAPBinding;
import java.util.Iterator;
import java.util.List;

public class AddPlayer extends ListenerAdapter {

    private DataBaseService dataBaseService;

    public AddPlayer(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void onSlashCommand(SlashCommandEvent event) {
        if (event.getName().equals("add")) {
            List<User> users = dataBaseService.showTable();
            Iterator<User> iterator = users.iterator();
            int i = 0;
            if (!iterator.hasNext()) {
                dataBaseService.saveUser(event.getUser().getName());
                event.reply("Добавлен новый игрок: " + event.getUser().getName()).submit();
                return;
            }
            while (iterator.hasNext()) {
                User user = users.get(i);
                if (user.getUsername().equals(event.getUser().getName())) {
                    event.reply("Вы уже добавлены").submit();
                    return;
                }
                i++;
                iterator.next();
            }
            dataBaseService.saveUser(event.getUser().getName());
            event.reply("Добавлен новый игрок: " + event.getUser().getName()).submit();
        }
    }
}
