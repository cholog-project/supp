package cholog.supp.api.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SuccessMessage {

    READ_POSTS_SUCCESS("질문 목록 조회 성공", HttpStatus.OK.value()),
    READ_POST_SUCCESS("단일 질문 조회 성공", HttpStatus.OK.value()),
    WRITE_POST_SUCCESS("질문글 작성 성공", HttpStatus.CREATED.value()),
    DELETE_POST_SUCCESS("질문글 삭제 성공", HttpStatus.NO_CONTENT.value()),
    MODIFY_POST_SUCCESS("질문글 수정 성공", HttpStatus.OK.value()),
    WRITE_COMMENT_SUCCESS("의견 작성 성공", HttpStatus.OK.value()),
    DELETE_COMMENT_SUCCESS("의견 삭제 성공", HttpStatus.NO_CONTENT.value()),
    MODIFY_COMMENT_SUCCESS("의견 수정 성공", HttpStatus.OK.value())
    ;

    private final String message;
    private final int status;
}
