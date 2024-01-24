package ru.complexhex.scrapernews.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.complexhex.scrapernews.config.BotConfig;
import ru.complexhex.scrapernews.dto.UserDTO;

import java.util.Optional;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final UserService userService;

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            long telegramId = update.getMessage().getFrom().getId();
            String firstName = update.getMessage().getFrom().getFirstName();
            String lastName = update.getMessage().getFrom().getLastName();
            String userName = update.getMessage().getFrom().getUserName();

            switch (messageText) {
                case "/start":
                    startBot(update, telegramId, firstName, lastName, userName, chatId);
                    break;
                default:
                    try {
                        sendMessage(chatId, messageText);
                        System.out.println(telegramId + " " + userName + " " + chatId + " " + firstName);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }

    private void startBot(Update update, long telegramId, String firstName, String lastName, String userName, long chatId) {
        Optional<UserDTO> existingUser = userService.findByTelegramId(telegramId);

        if (!existingUser.isPresent()) {
            UserDTO newUserDTO = new UserDTO();
            newUserDTO.setFirstName(firstName);
            newUserDTO.setLastName(lastName);
            newUserDTO.setUserName(userName);
            newUserDTO.setTelegramId(telegramId);

            userService.save(newUserDTO);
        }
        try {
            sendHiMessage(chatId, update.getMessage().getChat().getFirstName());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendHiMessage(long chatId, String name) throws TelegramApiException {
        String answer = "Hi, " + name + " nice to meet you!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException();
        }
    }
}
