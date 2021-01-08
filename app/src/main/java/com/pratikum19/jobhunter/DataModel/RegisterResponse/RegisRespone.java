package com.pratikum19.jobhunter.DataModel.RegisterResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisRespone {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("errors")
    @Expose
    private ErrorRespone errors;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorRespone getErrors() {
        return errors;
    }

    public void setErrors(ErrorRespone errors) {
        this.errors = errors;
    }
}
