package com.mask.mask.entity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Mail {

    private String from;
    private String to;
    private String cc;
    private String subject;

    public Mail() {
    }

    public Mail(String from, String to, String subject) {
        this.from = from;
        this.to = to;
        this.subject = subject;
    }

    public Mail(String from, String to, String cc, String subject) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.subject = subject;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
