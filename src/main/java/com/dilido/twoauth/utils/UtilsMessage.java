package com.dilido.twoauth.utils;

public class UtilsMessage {

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = "<div class=\"alert alert-danger\" role=\"alert\">\n" +
                message + "\n" +
                "</div>";
    }
}
