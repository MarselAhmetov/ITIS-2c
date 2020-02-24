package components.application;

import attributes.Args;
import com.beust.jcommander.JCommander;
import components.protocol.ClientSocket;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import models.*;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.actions.Move;
import components.service.JsonMessageService;
import models.actions.PutCrystals;
import models.actions.TakeCrystal;
import models.gameModels.CrystalModel;
import models.gameModels.GameData;
import models.gameModels.PlayerModel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Game extends Application {
    private Pane root;
    private HashMap<Integer, PlayerModel> playerHashMap = null;
    private HashMap<String, CrystalModel> crystalHashMap = null;
    private HashMap<Integer, GameObject> players = new HashMap<>();
    private HashMap<String, Crystal> crystals = new HashMap<>();
    private Integer playerId;
    private JsonMessageService jsonMessageService = new JsonMessageService();
    private ClientSocket clientSocket;
    private Integer tempScore = 0;
    private Integer playerScore = 0;
    private Integer playerX = 0;
    private Integer playerY = 0;
    private Integer countOfCrystals = 0;
    private static String address = "";
    private static Integer port = 0;
    Label scoreLabel = new Label();
    Label tempScoreLabel = new Label();

    public void makeAction(  String message) {
        JsonMessage jsonMessage = (JsonMessage) jsonMessageService.decodeJson(message, JsonMessage.class);
        switch (jsonMessage.getHeader()) {
            case "Move":
                LinkedHashMap move = (LinkedHashMap) jsonMessage.getPayload();
                Integer id = (Integer) move.get("playerId");
                switch ((String) move.get("direction")) {
                    case "UP":
                        players.get(id).moveUp();
                        if (id.equals(playerId)) {
                            playerY -= 30;
                        }
                        break;
                    case "DOWN":
                        players.get(id).moveDown();
                        if (id.equals(playerId)) {
                            playerY += 30;
                        }
                        break;
                    case "RIGHT":
                        players.get(id).moveRight();
                        if (id.equals(playerId)) {
                            playerX += 30;
                        }
                        break;
                    case "LEFT":
                        players.get(id).moveLeft();
                        if (id.equals(playerId)) {
                            playerX -= 30;
                        }
                        break;
                }
                break;
            case "SetCrystal":
                JsonMessageSetCrystal jsonMessageSetCrystal = jsonMessageService.decodeJson(message, JsonMessageSetCrystal.class);
                CrystalModel crystal = jsonMessageSetCrystal.getPayload();
                Crystal crystalObject = new Crystal(Color.LIGHTBLUE);
                crystalObject.setValue(crystal.getValue());
                crystals.put(crystal.getX() + "" + crystal.getY(), crystalObject);
                addGameObject(crystalObject, crystal.getX(), crystal.getY());
                break;
            case "SetPlayer":
                JsonMessageSetPlayer jsonMessageSetPlayer = jsonMessageService.decodeJson(message, JsonMessageSetPlayer.class);
                PlayerModel playerModel = jsonMessageSetPlayer.getPayload();
                Color color = getColorFromString(playerModel.getColor());
                Player playerObject = new Player(color);
                players.put(playerModel.getId(), playerObject);
                addGameObject(playerObject, playerModel.getGameCharacter().getX(), playerModel.getGameCharacter().getY());
                Base base = new Base(color);
                addGameObject(base, playerModel.getBaseModel().getX(), playerModel.getBaseModel().getY());
                break;
            case "GameData":
                Platform.runLater(() -> {
                    root.getChildren().clear();
                    scoreLabel.setText("Score: " + playerScore);
                    tempScoreLabel.setText("In Bag: " + tempScore + " (" + countOfCrystals + "/5)");
                    tempScoreLabel.setTranslateX(10);
                    tempScoreLabel.setTranslateY(25);
                    scoreLabel.setTranslateX(10);
                    scoreLabel.setTranslateY(10);
                    root.getChildren().add(scoreLabel);
                    root.getChildren().add(tempScoreLabel);
                });

                JsonMessageGameData jsonMessageGameData = jsonMessageService.decodeJson(message, JsonMessageGameData.class);
                GameData gameData = jsonMessageGameData.getPayload();
                playerId = gameData.getPlayerId();
                playerX = gameData.getPlayers().get(playerId).getGameCharacter().getX();
                playerY = gameData.getPlayers().get(playerId).getGameCharacter().getY();
                playerHashMap = gameData.getPlayers();
                crystalHashMap = gameData.getCrystals();

                for (Map.Entry<Integer, PlayerModel> player : playerHashMap.entrySet()) {
                    Color playerColor = getColorFromString(player.getValue().getColor());
                    Player player1 = new Player(playerColor);
                    players.put(player.getKey(), player1);
                    addGameObject(player1, player.getValue().getGameCharacter().getX(), player.getValue().getGameCharacter().getY());
                    base = new Base(playerColor);
                    addGameObject(base, player.getValue().getBaseModel().getX(), player.getValue().getBaseModel().getY());

                }
                for (Map.Entry<String, CrystalModel> crystalEntry : crystalHashMap.entrySet()) {
                    crystalObject = new Crystal(Color.LIGHTBLUE);
                    addGameObject(crystalObject, crystalEntry.getValue().getX(), crystalEntry.getValue().getY());
                    crystalObject.setValue(crystalEntry.getValue().getValue());
                    crystals.put(crystalEntry.getValue().getX() + "" + crystalEntry.getValue().getY(), crystalObject);
                }
                break;
            case "TakeCrystal":
                JsonMessageTakeCrystal jsonMessageTakeCrystal = jsonMessageService.decodeJson(message, JsonMessageTakeCrystal.class);
                TakeCrystal takeCrystal = jsonMessageTakeCrystal.getPayload();
                System.out.println(takeCrystal.getX() + "" + takeCrystal.getY());
                if (takeCrystal.getPlayerId() == playerId) {
                    tempScore = tempScore + crystals.get(takeCrystal.getX() + "" + takeCrystal.getY()).getValue();
                    System.out.println(tempScore);
                    countOfCrystals++;
                }
                Platform.runLater(() -> {
                    root.getChildren().remove(tempScoreLabel);
                    tempScoreLabel.setText("In Bag: " + tempScore + " (" + countOfCrystals + "/5)");
                    root.getChildren().add(tempScoreLabel);
                    if (crystals.get(takeCrystal.getX() + "" + takeCrystal.getY()) != null) {
                        root.getChildren().remove(crystals.get(takeCrystal.getX() + "" + takeCrystal.getY()).getView());
                    }
                });
                crystalHashMap.remove(takeCrystal.getX() + "" + takeCrystal.getY());
                break;
            case "PutCrystals":
                JsonMessagePutCrystals jsonMessagePutCrystals = jsonMessageService.decodeJson(message, JsonMessagePutCrystals.class);
                PutCrystals putCrystals = jsonMessagePutCrystals.getPayload();
                playerScore += tempScore;
                System.out.println("Score: " + playerScore);
                countOfCrystals = 0;
                tempScore = 0;
                Platform.runLater(() -> {
                    root.getChildren().remove(scoreLabel);
                    scoreLabel.setText("Score: " + playerScore);
                    tempScoreLabel.setText("In Bag: " + 0 + " (" + countOfCrystals + "/5)");
                    root.getChildren().add(scoreLabel);
                });
                break;
            case "Win":
                Integer playerId = (Integer) jsonMessage.getPayload();
                Platform.runLater(() -> {
                    Label label = new Label();
                    label.setText("Winner is " + playerHashMap.get(playerId).getColor());
                    scoreLabel.setText("Score: " + playerScore);
                    tempScoreLabel.setText("In Bag: " + 0 + " (" + countOfCrystals + "/5)");
                    root.getChildren().add(scoreLabel);
                });
                clientSocket.stopConnection();
                break;
        }
    }

    private Color getColorFromString(String textColor) {
        Color color = null;
        switch (textColor) {
            case "RED":
                color = Color.RED;
                break;
            case "GREEN":
                color = Color.GREEN;
                break;
            case "BLUE":
                color = Color.BLUE;
                break;
            case "YELLOW":
                color = Color.YELLOW;
                break;
            case "PURPLE":
                color = Color.PURPLE;
                break;
            case "BLACK":
                color = Color.BLACK;
                break;
            case "PINK":
                color = Color.PINK;
                break;
            case "ORANGE":
                color = Color.ORANGE;
                break;
        }
        return color;
    }


    private Parent createContent() {
        root = new Pane();
        root.setPrefSize(1200, 600);
        return root;
    }

    private void addGameObject(GameObject object, double x, double y) {
        Platform.runLater(() -> {
            object.getView().setTranslateX(x);
            object.getView().setTranslateY(y);
            root.getChildren().add(object.getView());
        });

    }

    private static class Player extends GameObject {
        Player(Color color) {
            super(new Rectangle(30, 30, color));
        }
    }

    private static class Crystal extends GameObject {
        Integer value = 0;

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        Crystal(Color color) {
            super(new Rectangle(15, 15, color));
            rotate(45);
        }
    }

    private static class Base extends GameObject {
        Base(Color color) {
            super(new Rectangle(30, 30, color));
            rotate(45);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        clientSocket = new ClientSocket();
        clientSocket.setGame(this);
        stage.setScene(new Scene(createContent()));
        clientSocket.startConnection(address, port);

        stage.getScene().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.A) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "LEFT")));
            } else if (e.getCode() == KeyCode.D) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "RIGHT")));
            } else if (e.getCode() == KeyCode.W) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "UP")));
            } else if (e.getCode() == KeyCode.S) {
                clientSocket.sendMessage(jsonMessageService.createJson("Move", new Move(playerId, "DOWN")));
            } else if (e.getCode() == KeyCode.SPACE) {
                if (countOfCrystals < 5) {
                    clientSocket.sendMessage(jsonMessageService.createJson("TakeCrystal", new TakeCrystal(playerX, playerY, playerId)));
                }
                System.out.println(playerX + " | " + playerY);
            } else if (e.getCode() == KeyCode.R) {
                if ((playerX == playerHashMap.get(playerId).getBaseModel().getX()) && (playerY == playerHashMap.get(playerId).getBaseModel().getY())) {
                    clientSocket.sendMessage(jsonMessageService.createJson("PutCrystals", new PutCrystals(tempScore, playerId)));
                }
            } else if (e.getCode() == KeyCode.Q) {
                clientSocket.sendMessage(jsonMessageService.createJson("GetGameData", playerId));
            }
        });
        stage.show();
    }

    public static void main(String[] argv) {
        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        address = args.address;
        port = args.port;
        launch(argv);
    }
}