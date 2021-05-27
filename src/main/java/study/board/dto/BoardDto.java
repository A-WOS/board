package study.board.dto;

import lombok.*;
import study.board.domain.entity.Board;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String origFilename;
    private String filename;
    private String filepath;

    public Board toEntity() {
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .origFilename(origFilename)
                .filename(filename)
                .filepath(filepath)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String author, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate, String origFilename, String filename, String filepath) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filepath = filepath;
    }
}
