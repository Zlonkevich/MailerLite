package com.mailer.common.dto;

import com.mailer.common.enums.SubscriberStateEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@Accessors(chain = true)
public class SubscriberDTO {
    private String name;
    private String email;
    private SubscriberStateEnum state;
    private Map<String, String> additionalInfo;
}
