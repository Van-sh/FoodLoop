package com.edusync.response;

import java.util.List;

public record DiscussionResponse(
    String topic,
    String content,
    Integer likes,
    List<CommentResponse> commentResponses,
    String userName
) {
}
