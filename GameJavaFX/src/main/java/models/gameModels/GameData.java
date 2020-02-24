package models.gameModels;

import java.util.HashMap;

public class GameData {
    private HashMap<Integer, PlayerModel> players;
    private HashMap<String, CrystalModel> crystals;
    private Integer playerId;

    public GameData() {
    }

    public GameData(HashMap<Integer, PlayerModel> players, HashMap<String, CrystalModel> crystals, Integer playerId) {
        this.players = players;
        this.crystals = crystals;
        this.playerId = playerId;
    }

    public HashMap<Integer, PlayerModel> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<Integer, PlayerModel> players) {
        this.players = players;
    }

    public HashMap<String, CrystalModel> getCrystals() {
        return crystals;
    }

    public void setCrystals(HashMap<String, CrystalModel> crystals) {
        this.crystals = crystals;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }
}
