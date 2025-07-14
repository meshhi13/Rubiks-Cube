package Rubix.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javafx.scene.Group;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.transform.Rotate;
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

    static HashMap<Button, Boolean> buttonStates = new HashMap<>();
    static HashMap<Button, Thread> buttonThreads = new HashMap<>();
    static List<String> tempButtonLabels = Arrays.asList("F", "R", "U", "F'", "R'", "U'", "L", "B", "D", "L'", "B'", "D'", "◀", "▶", "🎲", "🔄", "+", "-");
    static List<String> tempDisableLabels = Arrays.asList("F", "R", "U", "F'", "R'", "U'", "L", "B", "D", "L'", "B'", "D'", "◀", "▶", "🎲", "🔄");

    public static AnchorPane createUI(RubixCube cube, PerspectiveCamera camera, Group cubeGroup, Rotate rotateX, Rotate rotateY) {
        AnchorPane overlay = new AnchorPane();
        overlay.setPickOnBounds(false);


        HBox zoomBox = new HBox(10);
        zoomBox.setStyle("-fx-padding: 20 10 20 10;");
        zoomBox.getChildren().addAll(createButton("+"), createButton("-"), createButton("⤾" ));

        ObservableList<Node> zoomButtons = zoomBox.getChildren();
        Button zoomIn = (Button) zoomButtons.get(0);
        Button zoomOut = (Button) zoomButtons.get(1);
        Button resetView = (Button) zoomButtons.get(2);

        resetView.setOnAction(_ ->{
            // Reset camera zoom
            camera.setTranslateZ(-650);

            // Reset cube rotation
            cubeGroup.getTransforms().clear();
            cubeGroup.getTransforms().addAll(rotateX, rotateY);
            rotateX.setAngle(30);
            rotateY.setAngle(30);

            buttonAction(resetView, 100);
        });

        zoomIn.setOnAction(_ ->{
            InteractionUtil.zoomIn(camera);
            buttonAction(zoomIn, 100);
        });

        zoomOut.setOnAction(_ ->{
            InteractionUtil.zoomOut(camera);
            buttonAction(zoomOut, 100);
        });

        AnchorPane.setLeftAnchor(zoomBox, 10.0);
        AnchorPane.setBottomAnchor(zoomBox, 10.0);

        HBox scrambleBox = new HBox(0);
        scrambleBox.setStyle("-fx-padding: 20 10 20 10;");
        scrambleBox.getChildren().addAll(createButton("🎲"), createButton("🔄") );

        AnchorPane.setLeftAnchor(scrambleBox, 10.0);
        AnchorPane.setTopAnchor(scrambleBox, 10.0);

        Button scrambleButton = (Button) scrambleBox.getChildren().get(0);
        scrambleButton.setOnAction(_ -> {
            cube.scramble();
            buttonAction(scrambleButton, 7500);
        });

        Button resetButton = (Button) scrambleBox.getChildren().get(1);
        resetButton.setOnAction(_ -> {
            cube.reset();
            buttonAction(resetButton, 1000);
        });

        HBox rotateRow1 = new HBox(10);
        rotateRow1.getChildren().addAll(
            createButton("F"), createButton("R"), createButton("U"),
            createButton("F'"), createButton("R'"), createButton("U'")
        );

        HBox rotateRow2 = new HBox(10);
        rotateRow2.getChildren().addAll(
            createButton("B"), createButton("L"), createButton("D"),
            createButton("B'"), createButton("L'"), createButton("D'")
        );

        VBox rotateBox = new VBox(rotateRow1, rotateRow2);
        rotateBox.setSpacing(10);
        rotateBox.setStyle("-fx-padding: 20 10 20 10;");

        AnchorPane.setRightAnchor(rotateBox, 10.0);
        AnchorPane.setTopAnchor(rotateBox, 10.0);
        
        ObservableList<Node> rotateButtons = FXCollections.observableArrayList();
        rotateButtons.addAll(rotateRow1.getChildren());
        rotateButtons.addAll(rotateRow2.getChildren());

        Button front = (Button) rotateButtons.get(0);
        Button right = (Button) rotateButtons.get(1);
        Button up = (Button) rotateButtons.get(2);
        Button frontReverse = (Button) rotateButtons.get(3);
        Button rightReverse = (Button) rotateButtons.get(4);
        Button upReverse = (Button) rotateButtons.get(5);
        Button back = (Button) rotateButtons.get(6);
        Button left = (Button) rotateButtons.get(7);
        Button down = (Button) rotateButtons.get(8);
        Button backReverse = (Button) rotateButtons.get(9);
        Button leftReverse = (Button) rotateButtons.get(10);
        Button downReverse = (Button) rotateButtons.get(11);

        front.setOnAction(_ ->{
            cube.rotateFace(4, true);
            buttonAction(front, 1000);
        });
        
        right.setOnAction(_ ->{
            cube.rotateFace(1, true);
            buttonAction(right, 1000);
        });

        up.setOnAction(_ ->{
            cube.rotateFace(2, true);
            buttonAction(up, 1000);
        });

        frontReverse.setOnAction(_ -> {
            cube.rotateFace(4, false);
            buttonAction(frontReverse, 1000);
        });

        rightReverse.setOnAction(_ -> {
            cube.rotateFace(1, false);
            buttonAction(rightReverse, 1000);
        });

        upReverse.setOnAction(_ ->{
            cube.rotateFace(2, false);
            buttonAction(upReverse, 1000);
        });

        left.setOnAction(_ ->{
            cube.rotateFace(0, true);
            buttonAction(left, 1000);
        });

        back.setOnAction(_ ->{
            cube.rotateFace(5, true);
            buttonAction(back, 1000);
        });

        down.setOnAction(_ ->{
            cube.rotateFace(3, true);
            buttonAction(down, 1000);
        });

        leftReverse.setOnAction(_ -> {
            cube.rotateFace(0, false);
            buttonAction(leftReverse, 1000);
        });

        backReverse.setOnAction(_ -> {
            cube.rotateFace(5, false);
            buttonAction(backReverse, 1000);
        });

        downReverse.setOnAction(_ -> {
            cube.rotateFace(3, false);
            buttonAction(downReverse, 1000);
        });

        overlay.getChildren().addAll(zoomBox, scrambleBox, rotateBox);
        return overlay;
    }
    
    private static Button createButton(String label) {
        Button btn = new Button(label);

        buttonStates.put(btn, false);

        btn.setStyle(baseStyle);

        btn.setOnAction(_ -> buttonAction(btn, 100));

        btn.setOnMouseEntered(_ -> btn.setStyle(hoverStyle));
        btn.setOnMouseExited(_ ->{
            if (!buttonStates.get(btn))
                btn.setStyle(baseStyle);
        });

        return btn;
    }

    private static void buttonAction(Button btn, int timeout) {
        String label = btn.getText();

        if (tempButtonLabels.contains(label)) {
            Thread oldThread = buttonThreads.get(btn);
            if (oldThread != null && oldThread.isAlive()) {
                oldThread.interrupt();
            }

            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException ex) {
                    return;
                }
                javafx.application.Platform.runLater(() -> {
                    btn.setDisable(false);
                    btn.setStyle(baseStyle);
                    buttonStates.put(btn, false);
                });
            });

            buttonStates.put(btn, true);
            btn.setStyle(hoverStyle);
            buttonThreads.put(btn, thread);
            if (tempDisableLabels.contains(label)) {
                btn.setDisable(true);
            }

            thread.start();

        }
        else {
            if (buttonStates.get(btn))
                buttonStates.put(btn, false);
            else 
                buttonStates.put(btn, true);

            String style = buttonStates.get(btn) ? hoverStyle : baseStyle;
            btn.setStyle(style);

            for (Button b : buttonStates.keySet()) {
                if (b != btn) {
                    b.setStyle(baseStyle);
                }
            }       
        }
    }
}
