package com.leshchenko.starcraftbot.service;

import com.leshchenko.starcraftbot.command.AddPlayer;
import com.leshchenko.starcraftbot.command.Reset;
import com.leshchenko.starcraftbot.command.ScoreAdd;
import com.leshchenko.starcraftbot.command.Status;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class BotService {

    DataBaseService dataBaseService;

    private  String SECRET_TOKEN;
    private JDA jda;

    @Autowired
    public BotService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
        try {
            SECRET_TOKEN = "ODUyMzE0MDQwNzU5MjIyMzEy.YMFBTg.AL0aLfRw_cV186bI-_CnazQESLQ";
            jda = JDABuilder
                    .createDefault(SECRET_TOKEN)
                    .addEventListeners(
                            new AddPlayer(dataBaseService), new ScoreAdd(dataBaseService),
                            new Status(dataBaseService) ,new Reset(dataBaseService))
                    .build();
            jda
                    .upsertCommand("status", "Показывается статистика")
                    .queue();
            jda
                    .upsertCommand("add", "Добавляется новый игрок")
                    .queue();
            jda
                    .upsertCommand("score", "Добавляется очко игроку")
                    .queue();
            jda
                    .upsertCommand("reset", "Сбрасывается таблица")
                    .queue();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
