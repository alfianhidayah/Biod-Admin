package com.example.adminbiod.model.model_admin;

public class ResponseLogin {
    private Boolean status;
    private String message;
    private DataAdmin data = null;

    public ResponseLogin(Boolean status, String message, DataAdmin data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DataAdmin getData() {
        return data;
    }
}
