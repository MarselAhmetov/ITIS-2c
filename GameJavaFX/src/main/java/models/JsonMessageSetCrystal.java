package models;

import models.gameModels.CrystalModel;

public class JsonMessageSetCrystal {
    private String header;
    private CrystalModel payload;

    public JsonMessageSetCrystal() {
    }

    public JsonMessageSetCrystal(String header, CrystalModel payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public CrystalModel getPayload() {
        return payload;
    }

    public void setPayload(CrystalModel payload) {
        this.payload = payload;
    }
}
