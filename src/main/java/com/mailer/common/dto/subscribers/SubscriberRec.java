package com.mailer.common.dto.subscribers;

import java.util.List;

public record SubscriberRec(int id, String email, String name, String state, List<FieldRec> fields) {
}
