package models;

import models.actions.TakeCrystal;

public class JsonMessageTakeCrystal {
    private String header;
    private TakeCrystal payload;

    public JsonMessageTakeCrystal() {
    }

    public JsonMessageTakeCrystal(String header, TakeCrystal payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public TakeCrystal getPayload() {
        return payload;
    }

    public void setPayload(TakeCrystal payload) {
        this.payload = payload;
    }
}
