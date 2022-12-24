package com.driver;

import java.util.Date;

public class Mail {
    private Date date;
    private String name;
    private String message;

    public Mail(Date date, String name, String message) {
        this.date = date;
        this.name = name;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }
}
