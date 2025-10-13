package sample.repository;

import sample.dto.request.log.LogWriteDto;

/**
 * <pre>
 * ログのRepositoryインターフェース
 * </pre>
 */
public interface LogRepository {

    /**
     * <pre>
     * ログ書込み
     * </pre>
     * 
     * @param log 書き込みパラメータ
     * @return 書き込み結果
     * @throws RuntimeException ログ書き込みに失敗した場合
     */
    long startWriteLog(LogWriteDto log) throws RuntimeException;

    /**
     * <pre>
     * ログ書込み
     * </pre>
     * 
     * @param log 書き込みパラメータ
     * @return 書き込み結果
     * @throws RuntimeException ログ書き込みに失敗した場合
     */
    void endWriteLog(LogWriteDto log) throws RuntimeException;

}
