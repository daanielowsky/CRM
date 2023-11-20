package com.daanielowsky.crm.Entities;

import java.time.LocalDateTime;

public class Activity {

    private LocalDateTime localDateTime;

    private String message;

    public Activity(String message) {
        this.localDateTime = LocalDateTime.now();
        this.message = message;
    }
}
