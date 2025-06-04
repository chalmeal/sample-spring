package sample.context.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class Message {

    // DI
    private final MessageSource messageSource;

    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String msg) {
        return messageSource.getMessage(msg, null, Locale.getDefault());
    }
}
