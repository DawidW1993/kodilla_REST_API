package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Mail {
    private String mailTo;
    private String subject;
    private String message;
    private List<String> toCC = new ArrayList<>();

    public Mail(String mailTo, String subject, String message) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
    }

    public void setToCC(List<String> toCC) {
        this.toCC = toCC;
    }
}
