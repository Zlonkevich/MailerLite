package com.mailer.ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum FieldEnum {
    DATE("date"),
    NUMBER("number"),
    STRING("string"),
    BOOLEAN("boolean");

    private final String type;

    public static Stream<FieldEnum> stream() {
        return Stream.of(FieldEnum.values());
    }
}
