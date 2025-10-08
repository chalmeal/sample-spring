package sample.context.util;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * メッセージ取得クラス
 * messages.propertiesからメッセージを取得します。
 * </pre>
 */
@Component
public class Message {
    // DI
    private final MessageSource messageSource;

    public Message(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * <pre>
     * メッセージ取得
     * エラーメッセージはハードコードせず、必ずmessages.propertiesから取得してください。
     * </pre>
     * 
     * @param msg メッセージキー
     * @return メッセージ
     */
    public String get(String msg) {
        return messageSource.getMessage(msg, null, Locale.getDefault());
    }
}
