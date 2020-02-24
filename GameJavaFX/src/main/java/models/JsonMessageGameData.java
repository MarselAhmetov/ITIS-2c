package models;

import models.gameModels.GameData;

public class JsonMessageGameData {
    private String header;
    private GameData payload;

    public JsonMessageGameData() {
    }

    public JsonMessageGameData(String header, GameData payload) {
        this.header = header;
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public GameData getPayload() {
        return payload;
    }

    public void setPayload(GameData payload) {
        this.payload = payload;
    }
}
