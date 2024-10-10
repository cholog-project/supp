package cholog.supp.api.comment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCommentRequest(
    Long postId,
    @NotBlank String content
) {

}
