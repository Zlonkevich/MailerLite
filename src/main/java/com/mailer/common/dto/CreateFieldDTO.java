package com.mailer.common.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreateFieldDTO {
    private String title;
    private String type;
}
