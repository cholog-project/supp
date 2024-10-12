package cholog.supp.api.post.dto.response;

import java.time.LocalDateTime;

public record PostResponse(
    Long postId,
    String postTitle,
    LocalDateTime createdDate
) {

}
