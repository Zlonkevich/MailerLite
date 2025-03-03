package com.mailer.common.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class SubscriberDTO {
    private String name;
    private String email;
    private String state;
    private Map<String, String> additionalInfo;
}
