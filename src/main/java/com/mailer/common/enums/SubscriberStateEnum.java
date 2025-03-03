package com.mailer.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum SubscriberStateEnum {
    ACTIVE("active"),
    UNSUBSCRIBED("unsubscribed"),
    JUNK("junk"),
    BOUNCED("bounced"),
    UNCONFIRMED("unconfirmed");

    private final String state;

    public static Stream<SubscriberStateEnum> stream() {
        return Stream.of(SubscriberStateEnum.values());
    }
}
