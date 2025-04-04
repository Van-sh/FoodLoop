package com.edusync.response;

import java.time.LocalDateTime;

public record CommentResponse(
        String userName,
        LocalDateTime timestamp,
        Integer upvotes,
        String textComment
) {
}
