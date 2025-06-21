package Rubix;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import Rubix.RubixCube;

public class UIUtil {
    static String baseStyle = "-fx-font-size: 16px; "
        + "-fx-min-width: 45px; "
        + "-fx-min-height: 45px; "
        + "-fx-background-radius: 10px; "
        + "-fx-border-radius: 10px; "
        + "-fx-background-color: #f5f5f5; "
        + "-fx-border-color: #cccccc; "
        + "-fx-cursor: hand;";

    static String hoverStyle = "-fx-font-size: 16px; "
        + "-fx-min-width: 45px; "
        + "-fx-min-height: 45px; "
        + "-fx-background-radius: 10px; "
        + "-fx-border-radius: 10px; "
        + "-fx-background-color: #e0e0e0; "
        + "-fx-border-color: #999999; "
        + "-fx-cursor: hand; "
        + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.15), 4, 0, 0, 2);";

    public static AnchorPane createUI(RubixCube cube) {
        AnchorPane overlay = new AnchorPane();
        overlay.setPickOnBounds(false);

        HBox zoomBox = new HBox(10);
        zoomBox.setStyle("-fx-padding: 20 10 20 10;");
        zoomBox.getChildren().addAll(createButton("+"), createButton("-"));
        AnchorPane.setLeftAnchor(zoomBox, 10.0);
        AnchorPane.setBottomAnchor(zoomBox, 10.0);

        HBox scrambleBox = new HBox(0);
        scrambleBox.setStyle("-fx-padding: 20 10 20 10;");
        scrambleBox.getChildren().add(createButton("⟳"));
        overlay.getChildren().add(scrambleBox);
        AnchorPane.setLeftAnchor(scrambleBox, 10.0);
        AnchorPane.setTopAnchor(scrambleBox, 10.0);
        
        HBox playbackBox = new HBox(10);
        playbackBox.setStyle("-fx-padding: 20 10 20 10;");
        playbackBox.getChildren().addAll(
            createButton("⏪"), createButton("◀"),createButton("▶"),
            createButton("⏩")
        );

        HBox rotateBox = new HBox(10);
        rotateBox.setStyle("-fx-padding: 20 10 20 10;");
        rotateBox.getChildren().addAll(
            createButton("F"), createButton("R"), createButton("U"),
            createButton("F'"), createButton("R'"), createButton("U'"),
            createButton("L"), createButton("B"), createButton("D"),
            createButton("L'"), createButton("B'"), createButton("D'")
        );


        AnchorPane.setRightAnchor(rotateBox, 10.0);
        AnchorPane.setTopAnchor(rotateBox, 10.0);
        ObservableList<Node> rotateButtons = rotateBox.getChildren();
        Button front = (Button) rotateButtons.get(0);
        Button right = (Button) rotateButtons.get(1);
        Button up = (Button) rotateButtons.get(2);
        Button frontReverse = (Button) rotateButtons.get(3);
        Button rightReverse = (Button) rotateButtons.get(4);
        Button upReverse = (Button) rotateButtons.get(5);
        Button left = (Button) rotateButtons.get(6);
        Button back = (Button) rotateButtons.get(7);
        Button down = (Button) rotateButtons.get(8);
        Button leftReverse = (Button) rotateButtons.get(9);
        Button backReverse = (Button) rotateButtons.get(10);
        Button downReverse = (Button) rotateButtons.get(11);

        front.setOnAction(e -> {
            cube.rotateFace(4, true);
        });
        
        right.setOnAction(e -> {
            cube.rotateFace(1, true);
        });

        up.setOnAction(e -> {
            cube.rotateFace(2, true);
        });

        frontReverse.setOnAction(e -> {
            cube.rotateFace(4, false);
        });

        rightReverse.setOnAction(e -> {
            cube.rotateFace(1, false);
        });

        upReverse.setOnAction(e -> {
            cube.rotateFace(2, false);
        });

        left.setOnAction(e -> {
            cube.rotateFace(0, true);
        });

        back.setOnAction(e -> {
            cube.rotateFace(5, true);
        });

        down.setOnAction(e -> {
            cube.rotateFace(3, true);
        });

        leftReverse.setOnAction(e -> {
            cube.rotateFace(0, false);
        });

        backReverse.setOnAction(e -> {
            cube.rotateFace(5, false);
        });

        downReverse.setOnAction(e -> {
            cube.rotateFace(3, false);
        });

        
        AnchorPane.setRightAnchor(playbackBox, 10.0);
        AnchorPane.setBottomAnchor(playbackBox, 10.0);

        ObservableList<Node> playbackButtons = playbackBox.getChildren();
        Button fastReverse = (Button) playbackButtons.get(0);
        Button slowReverse = (Button) playbackButtons.get(1);
        Button slowForward = (Button) playbackButtons.get(2);
        Button fastForward = (Button) playbackButtons.get(3);

        overlay.getChildren().addAll(zoomBox, playbackBox, rotateBox);
        return overlay;
    }
    
    private static final List<Button> allButtons = new ArrayList<>();

    private static Button createButton(String label) {
        Button btn = new Button(label);
        System.out.println(btn.getText());
        final boolean[] clicked = {false};

        String style = clicked[0] ? hoverStyle : baseStyle;
        btn.setStyle(style);

        allButtons.add(btn);

        btn.setOnAction(e -> {
            for (Button b : allButtons) {
                if (b != btn) {
                    b.setStyle(baseStyle);
                }
            }
            clicked[0] = !clicked[0];
            btn.setStyle(clicked[0] ? hoverStyle : baseStyle);

            if (label.equals("◀") || label.equals("▶") || label.equals("⟳")) {
                btn.setStyle(hoverStyle);
                new Thread(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    javafx.application.Platform.runLater(() -> {
                        clicked[0] = false;
                        btn.setStyle(baseStyle);
                    });
                }).start();
            }
        });

        btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(e -> {
            if (!clicked[0]) btn.setStyle(baseStyle);
        });

        return btn;
    }
}
