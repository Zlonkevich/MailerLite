package com.mailer.common.dto.subscribers;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FieldRec(@JsonProperty("field_id") int fieldId, String title, String type, String value) {
}
