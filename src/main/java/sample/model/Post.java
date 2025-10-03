package sample.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * <pre>
 * 役職を定義します。
 * </pre>
 */
@Data
@Entity
@Table(name = "posts")
public class Post {
    /** 役職コード */
    @Id
    @Size(max = 4)
    private String postCode;

    /** 役職名 */
    @NotNull
    @Size(max = 256)
    private String postName;

    /** 登録日時 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    /** 更新日時 */
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime updatedAt;

    /** 状態 */
    @NotNull
    private int status;
}
