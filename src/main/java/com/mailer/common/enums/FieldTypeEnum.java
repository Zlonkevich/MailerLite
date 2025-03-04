package com.mailer.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum FieldTypeEnum {
    DATE("date"),
    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean");

    private final String type;

    public static Stream<FieldTypeEnum> stream() {
        return Stream.of(FieldTypeEnum.values());
    }
}
