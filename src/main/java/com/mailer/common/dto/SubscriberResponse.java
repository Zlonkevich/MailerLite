package com.mailer.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SubscriberResponse(
    Data data,
    String message
) {
    public record Data(
        int id,
        String email,
        String name,
        String state,
        List<Field> fields
    ) {
    }

    public record Field(
        @JsonProperty("field_id")
        int fieldId,
        String title,
        String type,
        String value
    ) {
    }
}

