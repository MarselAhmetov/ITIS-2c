package models.gameModels;

public class PlayerModel {
    private int id;
    private BaseModel baseModel;
    private GameCharacter gameCharacter;
    private String color;
    private int score;

    public PlayerModel() {
    }

    public PlayerModel(int id, BaseModel baseModel, GameCharacter gameCharacter, String color, int score) {
        this.id = id;
        this.baseModel = baseModel;
        this.gameCharacter = gameCharacter;
        this.color = color;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseModel getBaseModel() {
        return baseModel;
    }

    public void setBaseModel(BaseModel baseModel) {
        this.baseModel = baseModel;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
