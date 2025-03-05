package com.mailer.common.dto;

public record CreateFieldResponse(Data data, String message) {

    public record Data(int id, String title, String type) {
    }
}