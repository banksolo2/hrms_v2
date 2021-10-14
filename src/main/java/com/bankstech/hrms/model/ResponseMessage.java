package com.bankstech.hrms.model;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {
    private String type;
    private String message;
}
