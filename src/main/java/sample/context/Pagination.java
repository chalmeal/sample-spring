package sample.context;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * <pre>
 * ページネーションのコンテキストクラス
 * ページネーションに関する情報を保持します。
 * </pre>
 */
@Data
@Component
public class Pagination<T> {
    /** デフォルトのページサイズ */
    public static final int DEFAULT_PAGE_SIZE = 30;

    /** ページネーションのデータ */
    private List<T> data;

    /** 総ページサイズ */
    private int totalPage;

    /** 総件数 */
    private long totalCount;

    /**
     * <pre>
     * ページングの設定
     * データと総件数からページング情報を設定します。
     * </pre>
     * 
     * @param result     ページング対象のデータ
     * @param totalCount 総件数
     * @return ページング情報
     */
    public Pagination<T> paging(List<T> result, long totalCount) {
        this.data = result;
        this.totalCount = totalCount;
        this.totalPage = this.calculateTotalPage(totalCount);

        return this;
    }

    /**
     * <pre>
     * 総ページサイズの計算
     * データの総件数とページサイズから総ページ数を計算します。
     * </pre>
     * 
     * @param totalCount 総件数
     * @return 総ページ数
     */
    public int calculateTotalPage(long totalCount) {
        if (totalCount == 0) {
            return 0;
        }
        return (int) Math.ceil((double) totalCount / DEFAULT_PAGE_SIZE);
    }

}