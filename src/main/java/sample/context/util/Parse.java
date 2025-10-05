package sample.context.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 * <pre>
 * 変換ユーティリティクラス
 * </pre>
 */
@Component
public class Parse {

    /**
     * <pre>
     * 入社年月日をLocalDateに変換
     * </pre>
     * 
     * @param date 入社年月日(yyyy-MM-dd)
     * @return LocalDate
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
