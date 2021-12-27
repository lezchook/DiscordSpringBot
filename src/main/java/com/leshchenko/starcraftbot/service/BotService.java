package com.leshchenko.starcraftbot.service;

import com.leshchenko.starcraftbot.listener.CommandListener;
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
                    .addEventListeners(new CommandListener())
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String channel, String message) {
        try {
            jda
                    .getTextChannelById(channel)
                    .sendMessage(message)
                    .queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
