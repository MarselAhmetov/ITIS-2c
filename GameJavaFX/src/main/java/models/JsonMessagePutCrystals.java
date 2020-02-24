package models;

import models.actions.PutCrystals;
import models.gameModels.PlayerModel;

public class JsonMessagePutCrystals {
    private String header;
    private PutCrystals payload;

    public JsonMessagePutCrystals() {
    }

    public JsonMessagePutCrystals(String header, PutCrystals payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public PutCrystals getPayload() {
        return payload;
    }

    public void setPayload(PutCrystals payload) {
        this.payload = payload;
    }
}
