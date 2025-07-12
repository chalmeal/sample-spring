package sample.context.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * メッセージ取得クラス
 * messages.propertiesからメッセージを取得します。
 * </p>
 */
@Component
public class Message {

    // DI
    private final MessageSource messageSource;

    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * <p>
     * メッセージ取得
     * エラーメッセージはハードコードせず、必ずmessages.propertiesから取得してください。
     * </p>
     * 
     * @param msg メッセージキー
     * @return メッセージ
     */
    public String get(String msg) {
        return messageSource.getMessage(msg, null, Locale.getDefault());
    }
}
