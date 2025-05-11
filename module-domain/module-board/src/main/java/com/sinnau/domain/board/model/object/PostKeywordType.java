package com.sinnau.domain.board.model.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostKeywordType {

    COMMNUITY   ( "community"),
    BUSINESS    ( "business")
    ;

    private final String postKeywordType;

}

