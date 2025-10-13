package sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import sample.context.util.Message;
import sample.dto.ResultDto;

/**
 * <pre>
 * テストヘルパークラス
 * 各テストクラスはこのクラスを継承して使用します。
 * </pre>
 */
public class TestHelper {
    protected final TestRestTemplate restTemplate;
    protected final ObjectMapper objectMapper;
    @Autowired
    private Message message;

    public TestHelper() {
        // TestRestTemplateの初期化
        this.restTemplate = new TestRestTemplate();
        // ObjectMapperの設定
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * <pre>
     * successが期待する汎用レスポンス
     * </pre>
     * 
     * @param message メッセージ
     * @return 期待するレスポンス
     */
    protected ResultDto success_response(String message) {
        ResultDto dto = new ResultDto();
        dto.setResult(ResultDto.ResultType.SUCCESS);
        dto.setMessage(this.message.get(message));

        return dto;
    }

}
