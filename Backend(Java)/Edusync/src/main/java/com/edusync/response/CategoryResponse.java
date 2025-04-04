package com.edusync.response;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name
) {
}
