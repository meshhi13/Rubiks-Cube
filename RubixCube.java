package Rubix;

import java.util.List;

import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.ArrayList;

public class RubixCube {
    private static final int SIZE = 3;
    private static final int CUBELET_SIZE = 50;
    private static final int GAP = 2;
    private static final int COLORS = 6;
    private static final Color[] colorPalette = {
        Color.GREEN, Color.RED, Color.YELLOW, Color.WHITE, Color.BLUE, Color.CYAN
    };
    Group cubeGroup = new Group();
        

    private Color[][][] faces =  new Color[COLORS][SIZE][SIZE];

    public RubixCube() {
        for (int i = 0; i < COLORS; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    faces[i][j][k] = colorPalette[i];
                    System.out.println("Face " + i + " at (" + j + ", " + k + ") color: " + ColorUtil.getColorName(faces[i][j][k]));
                }
            }
        }
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
            sticker.setMaterial(new PhongMaterial(faces[0][z][y]));
            sticker.setTranslateX(base.getTranslateX() - offset);
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.Y_AXIS);
            sticker.setRotate(90);
            if (y == 1 && z == 1) {
                Label stickerLabel = new Label("L");
                stickerLabel.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;"
                );
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateX(sticker.getTranslateX() - offset - 1.5);
                stickerLabel.setTranslateY(sticker.getTranslateY() - size / 2);
                stickerLabel.setTranslateZ(sticker.getTranslateZ());
                stickerLabel.setAlignment(javafx.geometry.Pos.CENTER);
                stickerLabel.setRotationAxis(Rotate.Y_AXIS);
                stickerLabel.setRotate(90);
                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }

        // -X face (Left, face 1)
        if (x == 2) {
            Box sticker = new Box(size, size, 1);
            sticker.setMaterial(new PhongMaterial(faces[1][z][2 - y]));
            sticker.setTranslateX(base.getTranslateX() + offset);
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.Y_AXIS);
            sticker.setRotate(90);
            if (y == 1 && z == 1) {
                Label stickerLabel = new Label("R");
                stickerLabel.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;"
                );
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateX(sticker.getTranslateX() - offset + 1.5);
                stickerLabel.setTranslateY(sticker.getTranslateY() - size / 2);
                stickerLabel.setTranslateZ(sticker.getTranslateZ());
                stickerLabel.setRotationAxis(Rotate.Y_AXIS);
                stickerLabel.setRotate(-90);
                cubelet.getChildren().add(stickerLabel);
            }
            cubelet.getChildren().add(sticker);
        }

        // +Y face (Up, face 2)
        if (y == 0) {
            Box sticker = new Box(size, size, 1);
            sticker.setMaterial(new PhongMaterial(faces[2][x][z]));
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY() - offset);
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.X_AXIS);
            sticker.setRotate(90);
            if (x == 1 && z == 1) {
                Label stickerLabel = new Label("U");
                stickerLabel.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;"
                );
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateY(sticker.getTranslateY() - offset - 1.5);
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
            sticker.setMaterial(new PhongMaterial(faces[3][x][2 - z]));
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY() + offset);
            sticker.setTranslateZ(base.getTranslateZ());
            sticker.setRotationAxis(Rotate.X_AXIS);
            sticker.setRotate(90);
            if (x == 1 && z == 1) {
                Label stickerLabel = new Label("D");
                stickerLabel.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;"
                );
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateY(sticker.getTranslateY() - offset + 1.5);
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
            sticker.setMaterial(new PhongMaterial(faces[4][x][y]));
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ() - offset);
            if (x == 1 && y == 1) {
                Label stickerLabel = new Label("F");
                stickerLabel.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;"
                );
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateZ(sticker.getTranslateZ() - 1.5);
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
            sticker.setMaterial(new PhongMaterial(faces[5][2 - x][y]));
            sticker.setTranslateX(base.getTranslateX());
            sticker.setTranslateY(base.getTranslateY());
            sticker.setTranslateZ(base.getTranslateZ() + offset);
            sticker.setRotationAxis(Rotate.Y_AXIS);
            if (x == 1 && y == 1) {
                Label stickerLabel = new Label("B");
                stickerLabel.setStyle(
                    "-fx-background-color: transparent;" +
                    "-fx-text-fill: black;" +
                    "-fx-font-size: 24px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-alignment: center;"
                );
                stickerLabel.setMinSize(size, size);
                stickerLabel.setMaxSize(size, size);
                stickerLabel.setTranslateZ(sticker.getTranslateZ() + 1.5);
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
        System.out.println("Checking node at " + coords[0] + ", " + coords[1] + ", " + coords[2] + " for face " + faceIndex);

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

    public void updateFacesArray (int faceIndex, boolean clockwise) {
        ;
    }

    public void rotateFace(int faceIndex, boolean clockwise) {
        // 1. Collect cubelets for the face/layer being turned
        List<Node> faceNodes = new ArrayList<>();
        for (Node node : cubeGroup.getChildren()) {
            if (isOnFace(node, faceIndex)) {
                faceNodes.add(node);
            }
        }

        // 2. Remove the face nodes from the main group
        cubeGroup.getChildren().removeAll(faceNodes);

        // 3. Create the rotating group and add the face nodes
        Group rotatingGroup = new Group();
        rotatingGroup.getChildren().addAll(faceNodes);
        cubeGroup.getChildren().add(rotatingGroup);

        // 2. Animate the rotation
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
        
        rt.setByAngle(clockwise ? 90 : -90); // or -90 for counterclockwise
        rt.setOnFinished(e -> {
            // 3. After animation, flatten group and update state
            cubeGroup.getChildren().addAll(rotatingGroup.getChildren());
            cubeGroup.getChildren().remove(rotatingGroup);
        });

        rt.play();
    }
}