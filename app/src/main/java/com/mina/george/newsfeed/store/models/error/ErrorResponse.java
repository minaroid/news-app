package com.mina.george.newsfeed.store.models.error;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("code")
    private String code;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
