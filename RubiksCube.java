package Rubix;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;

import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.ArrayList;

import Rubix.Util.ColorUtil;
import static Rubix.RubixSide.SIZE;

public class RubixCube {
    private static final int CUBELET_SIZE = 50;
    private static final int GAP = 2;
    private static final Color[] colorPalette = {
        Color.GREEN, Color.RED, Color.YELLOW, Color.WHITE, Color.BLUE, Color.CYAN
    };

    RubixSide left;
    RubixSide right;
    RubixSide up;
    RubixSide down;
    RubixSide front;
    RubixSide back;    

    private static final double DISTANCE = 2.5;
    private static final String labelStyle = 
        "-fx-font-size: 20px; " +
        "-fx-text-fill: black; " +
        "-fx-text-color: black; " +
        "-fx-font-weight: bold; " +
        "-fx-background-radius: 0; " +
        "-fx-background-color: transparent; " +
        "-fx-padding: 0; " +
        "-fx-alignment: center;";

    Group cubeGroup = new Group();

    public RubixCube() {
        left = new RubixSide(colorPalette[0]);
        right = new RubixSide(colorPalette[1]);
        up = new RubixSide(colorPalette[2]);
        down = new RubixSide(colorPalette[3]);
        front = new RubixSide(colorPalette[4]);
        back = new RubixSide(colorPalette[5]);
    }

    
    public Group buildCube() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                for (int z = 0; z < SIZE; z++) {
                    Group cubelet = createColoredCubelet(x, y, z);
                    cubeGroup.getChildren().add(cubelet);
                }
            }
        }
        return cubeGroup;
    }

    private Group createColoredCubelet(int x, int y, int z) {
        Box base = new Box(CUBELET_SIZE, CUBELET_SIZE, CUBELET_SIZE);
        base.setTranslateX((x - 1) * (CUBELET_SIZE + GAP));
        base.setTranslateY((y - 1) * (CUBELET_SIZE + GAP));
        base.setTranslateZ((z - 1) * (CUBELET_SIZE + GAP));
        base.setMaterial(new PhongMaterial(Color.GRAY));

        Group cubelet = new Group(base);
        double offset = CUBELET_SIZE / 2 + 0.5;
        double size = CUBELET_SIZE;

        cubelet.setUserData(new int[]{x, y, z});

        // +X face (Left, face 0)
        if (x == 0) {
            Box sticker = new Box(size, size, 1);
            PhongMaterial material = new PhongMaterial(left.values[SIZE - z - 1][y]);
            material.setSpecularColor(left.values[SIZE - z - 1][y]);
            sticker.setMaterial(material);
            sticker.setTranslateX(base.getTranslateX() - offset);
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.Y_AXIS);
            sticker.setRotate(90);
            if (y == 1 && z == 1) {
                Label stickerLabel = new Label("L");
                stickerLabel.setStyle(labelStyle);
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateX(sticker.getTranslateX() - offset - DISTANCE);
                stickerLabel.setTranslateY(sticker.getTranslateY() - size / 2);
                stickerLabel.setTranslateZ(sticker.getTranslateZ());
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);
                stickerLabel.setRotationAxis(Rotate.Y_AXIS);
                stickerLabel.setRotate(90);
                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }

        // -X face (Right, face 1)
        if (x == 2) {
            Box sticker = new Box(size, size, 1);
            PhongMaterial material = new PhongMaterial(right.values[SIZE - z - 1][y]);
            material.setSpecularColor(right.values[SIZE - z - 1][y]);
            sticker.setMaterial(material);
            sticker.setTranslateX(base.getTranslateX() + offset);
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.Y_AXIS);
            sticker.setRotate(90);
            if (y == 1 && z == 1) {
                Label stickerLabel = new Label("R");
                stickerLabel.setStyle(labelStyle);
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateX(sticker.getTranslateX() - offset + DISTANCE);
                stickerLabel.setTranslateY(sticker.getTranslateY() - size / 2);
                stickerLabel.setTranslateZ(sticker.getTranslateZ());
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);
                stickerLabel.setRotationAxis(Rotate.Y_AXIS);
                stickerLabel.setRotate(-90);
                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }

        // +Y face (Up, face 2)
        if (y == 0) {
            Box sticker = new Box(size, size, 1);
            PhongMaterial material = new PhongMaterial(up.values[x][z]);
            material.setSpecularColor(up.values[x][z]);
            sticker.setMaterial(material);
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY() - offset);
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.X_AXIS);
            sticker.setRotate(90);
            if (x == 1 && z == 1) {
                Label stickerLabel = new Label("U");
                stickerLabel.setStyle(labelStyle);
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateY(sticker.getTranslateY() - offset - DISTANCE);
                stickerLabel.setTranslateX(sticker.getTranslateX() - size / 2);
                stickerLabel.setTranslateZ(sticker.getTranslateZ());
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);
                stickerLabel.setRotationAxis(Rotate.X_AXIS);
                stickerLabel.setRotate(-90);
                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }
        // -Y face (Down, face 3)
        if (y == 2) {
            Box sticker = new Box(size, size, 1);
            PhongMaterial material = new PhongMaterial(down.values[x][z]);
            material.setSpecularColor(down.values[x][z]);
            sticker.setMaterial(material);
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY() + offset);
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.X_AXIS);
            sticker.setRotate(90);
            if (x == 1 && z == 1) {
                Label stickerLabel = new Label("D");
                stickerLabel.setStyle(labelStyle);
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateY(sticker.getTranslateY() - offset + DISTANCE);
                stickerLabel.setTranslateX(sticker.getTranslateX() - size / 2);
                stickerLabel.setTranslateZ(sticker.getTranslateZ());
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);
                stickerLabel.setRotationAxis(Rotate.X_AXIS);
                stickerLabel.setRotate(90);
                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }

        // +Z face (Front, face 4)
        if (z == 0) {
            Box sticker = new Box(size, size, 1);
            PhongMaterial material = new PhongMaterial(front.values[x][y]);
            material.setSpecularColor(front.values[x][y]);
            sticker.setMaterial(material);
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ() - offset);
            if (x == 1 && y == 1) {
                Label stickerLabel = new Label("F");
                stickerLabel.setStyle(labelStyle);
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateZ(sticker.getTranslateZ() - DISTANCE);
                stickerLabel.setTranslateY(sticker.getTranslateY() - size / 2);
                stickerLabel.setTranslateX(sticker.getTranslateX() - size / 2);
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);

                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }

        // -Z face (Back, face 5)
        if (z == 2) {
            Box sticker = new Box(size, size, 1);
            PhongMaterial material = new PhongMaterial(back.values[x][y]);
            material.setSpecularColor(back.values[x][y]);
            sticker.setMaterial(material);
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ() + offset);
            sticker.setRotationAxis(Rotate.Y_AXIS);
            if (x == 1 && y == 1) {
                Label stickerLabel = new Label("B");
                stickerLabel.setStyle(labelStyle);
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateZ(sticker.getTranslateZ() + DISTANCE);
                stickerLabel.setTranslateY(sticker.getTranslateY() - size / 2);
                stickerLabel.setTranslateX(sticker.getTranslateX() - size / 2);
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);
                stickerLabel.setRotationAxis(Rotate.Z_AXIS);
                stickerLabel.setRotate(180);

                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
            
        }
        
        return cubelet;
    }

    private boolean isOnFace (Node node, int faceIndex) {
        int[] coords = (int[]) node.getUserData();

        switch (faceIndex) {
            case 0: // +X face
                return coords[0] == 0;
            case 1: // -X face
                return coords[0] == SIZE - 1;
            case 2: // +Y face
                return coords[1] == 0;
            case 3: // -Y face
                return coords[1] == SIZE - 1;
            case 4: // +Z face
                return coords[2] == 0;
            case 5: // -Z face
                return coords[2] == SIZE - 1;
            default:
                return false;
        }
    }

    private void updateFacesArray(int faceIndex, boolean clockwise){

        switch (faceIndex) {
            case 0:
                if (clockwise) {
                    left.rotateSideCCW();

                    Color[] temp = front.getCol(0);
                    front.setCol(0, reverseArray(up.getCol(0)));
                    up.setCol(0, back.getCol(0));
                    back.setCol(0, reverseArray(down.getCol(0)));
                    down.setCol(0, temp);  
                }
                else {
                    left.rotateSideCW();

                    Color[] temp = front.getCol(0);
                    front.setCol(0, down.getCol(0));
                    down.setCol(0, reverseArray(back.getCol(0)));
                    back.setCol(0, up.getCol(0));
                    up.setCol(0, reverseArray(temp));  
                }

                break;
            case 1:
                if (clockwise) {
                    right.rotateSideCW();

                    Color[] temp = back.getCol(2);
                    back.setCol(2, up.getCol(2));
                    up.setCol(2, reverseArray(front.getCol(2)));
                    front.setCol(2, down.getCol(2));
                    down.setCol(2, reverseArray(temp));  
                }
                else {
                    right.rotateSideCCW();

                    Color[] temp = back.getCol(2);
                    back.setCol(2, reverseArray(down.getCol(2)));
                    down.setCol(2, front.getCol(2));
                    front.setCol(2, reverseArray(up.getCol(2)));
                    up.setCol(2, temp);  
                }

                break;
            case 2:
                if (clockwise) {
                    up.rotateSideCW();

                    Color[] temp = front.getRow(0);
                    front.setRow(0, reverseArray(right.getRow(0)));
                    right.setRow(0, back.getRow(0));
                    back.setRow(0, reverseArray(left.getRow(0)));
                    left.setRow(0, temp);
                }
                else {
                    up.rotateSideCCW();

                    Color[] temp = front.getRow(0);
                    front.setRow(0, left.getRow(0));
                    left.setRow(0, reverseArray(back.getRow(0)));
                    back.setRow(0, right.getRow(0));
                    right.setRow(0, reverseArray(temp));
                }

                break;
            case 3:
                if (clockwise) {
                    down.rotateSideCCW();

                    Color[] temp = front.getRow(2);
                    front.setRow(2, left.getRow(2));
                    left.setRow(2, reverseArray(back.getRow(2)));
                    back.setRow(2, right.getRow(2));
                    right.setRow(2, reverseArray(temp));
                }
                else {
                    down.rotateSideCW();

                    Color[] temp = front.getRow(2);
                    front.setRow(2, reverseArray(right.getRow(2)));
                    right.setRow(2, back.getRow(2));
                    back.setRow(2, reverseArray(left.getRow(2)));
                    left.setRow(2, temp);
                }

                break;
            case 4:
                if (clockwise) {
                    front.rotateSideCCW();

                    Color[] temp = up.getRow(0);
                    up.setRow(0, reverseArray(left.getCol(2)));
                    left.setCol(2, down.getRow(0));
                    down.setRow(0, reverseArray(right.getCol(2)));
                    right.setCol(2, temp);
                }
                else {
                    front.rotateSideCW();

                    Color[] temp = up.getRow(0);
                    up.setRow(0, right.getCol(2));
                    right.setCol(2, reverseArray(down.getRow(0)));
                    down.setRow(0, left.getCol(2));
                    left.setCol(2, reverseArray(temp));
                }

                break;
            case 5:
                if (clockwise) {
                    back.rotateSideCW();

                    Color[] temp = up.getRow(2);
                    up.setRow(2, right.getCol(0));
                    right.setCol(0, reverseArray(down.getRow(2)));
                    down.setRow(2, left.getCol(0));
                    left.setCol(0, reverseArray(temp));
                }
                else {
                    back.rotateSideCCW();

                    Color[] temp = up.getRow(2);
                    up.setRow(2, reverseArray(left.getCol(0)));
                    left.setCol(0, down.getRow(2));
                    down.setRow(2, reverseArray(right.getCol(0)));
                    right.setCol(0, temp);
                }
                break;
        }
    }

    private Color[] reverseArray(Color[] arr) {
        Color[] rev = new Color[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rev[i] = arr[arr.length - 1 - i];
        }
        return rev;
    }

    public void randomMove() {
        int[] faceOrder = {0, 1, 2, 3, 4, 5};
        int faceIndex = (int) (Math.random() * faceOrder.length);
        boolean clockwise = Math.random() < 0.5;
        rotateFace(faceOrder[faceIndex], clockwise);
    }

    public void scramble() {
        int moves = 20;
        Timeline timeline = new Timeline();
        for (int i = 0; i < moves; i++) {
            KeyFrame kf = new KeyFrame(Duration.millis(i * 350), _ -> {
                randomMove();
            });
            timeline.getKeyFrames().add(kf);
        }
        timeline.play();
    }

    public void reset() {
        left.reset(colorPalette[0]);
        right.reset(colorPalette[1]);
        up.reset(colorPalette[2]);
        down.reset(colorPalette[3]);
        front.reset(colorPalette[4]);
        back.reset(colorPalette[5]);
        cubeGroup.getChildren().clear();
        buildCube();
    }
    
    public void rotateFace(int faceIndex, boolean clockwise) {
        boolean[] currentTurn = {clockwise};
        int angle = 0;
        List<Node> faceNodes = new ArrayList<>();
        for (Node node : cubeGroup.getChildren()) {
            if (isOnFace(node, faceIndex)) {
                faceNodes.add(node);
            }
        }

        cubeGroup.getChildren().removeAll(faceNodes);

        Group rotatingGroup = new Group();
        rotatingGroup.getChildren().addAll(faceNodes);
        cubeGroup.getChildren().add(rotatingGroup);

        RotateTransition rt = new RotateTransition(Duration.millis(300), rotatingGroup);
        switch (faceIndex) {
            case 0:
            case 1:
                rt.setAxis(Rotate.X_AXIS); // +X or -X face
                break;
            case 2:
            case 3:
                rt.setAxis(Rotate.Y_AXIS); // +Y or -Y face
                break;
            case 4:
            case 5:
                rt.setAxis(Rotate.Z_AXIS); // +Z or -Z face
                break;
        }

        if (faceIndex % 2 == 1) { 
            angle = currentTurn[0] ? -90 : 90;
        }
        else {
            angle = currentTurn[0] ? 90 : -90;
        }
        rt.setByAngle(angle); // or -90 for counterclockwise
        rt.setOnFinished(_ -> {
            cubeGroup.getChildren().addAll(rotatingGroup.getChildren());
            cubeGroup.getChildren().remove(rotatingGroup);
            updateFacesArray(faceIndex, currentTurn[0]);
            buildCube();
        });

        rt.play();
    }

    private void printCube() {
        System.out.println("Left Side:");
        printSide(left);
        System.out.println("Right Side:");
        printSide(right);
        System.out.println("Up Side:");
        printSide(up);
        System.out.println("Down Side:");
        printSide(down);
        System.out.println("Front Side:");
        printSide(front);
        System.out.println("Back Side:");
        printSide(back);
    }

    private void printSide(RubixSide side) {
        for (int i = SIZE - 1; i >= 0; i--) {
            for (int j = SIZE - 1; j >= 0; j--) {
                System.out.print(ColorUtil.getColorName(side.values[j][i]) + " at (" + j + ", " + i + ") ");
            }
            System.out.println();
        }
    }
}