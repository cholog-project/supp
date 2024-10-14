package cholog.supp.api.post.dto.response;

import java.util.List;

public record EachPostResponse(
    EachPost eachPost,
    List<EachComment> comments
) {

}
