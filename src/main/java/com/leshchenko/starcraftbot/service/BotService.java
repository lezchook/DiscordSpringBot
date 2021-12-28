package com.leshchenko.starcraftbot.service;

import com.leshchenko.starcraftbot.command.AddPlayer;
import com.leshchenko.starcraftbot.command.CreateTable;
import com.leshchenko.starcraftbot.command.ScoreAdd;
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
            System.out.println("текстик");
            jda = JDABuilder
                    .createDefault(SECRET_TOKEN)
                    .addEventListeners(new CreateTable(), new AddPlayer(dataBaseService), new ScoreAdd(dataBaseService))
                    .build();
            jda
                    .upsertCommand("start", "Создается новая таблица")
                    .queue();
            jda
                    .upsertCommand("add", "Добавляется новый игрок")
                    .queue();
            jda
                    .upsertCommand("score", "Добавляется очко игроку")
                    .queue();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
