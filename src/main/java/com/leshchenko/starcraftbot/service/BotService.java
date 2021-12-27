package com.leshchenko.starcraftbot.service;

import com.leshchenko.starcraftbot.command.CreateTable;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
public class BotService {

    private String SECRET_TOKEN = "ODUyMzE0MDQwNzU5MjIyMzEy.YMFBTg.yYkTIVkB4rzDAdo5qGK4dwXQHpA";
    private JDA jda;

    public BotService() {
        try {
            jda = JDABuilder
                    .createDefault(SECRET_TOKEN)
                    .addEventListeners(new CreateTable())
                    .build();
            jda
                    .upsertCommand("start", "Создается новая таблица")
                    .queue();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
