package Rubix;

import Rubix.Util.InteractionUtil;
import Rubix.Util.LightingUtil;
import Rubix.Util.UIUtil;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class RubixCube3DApp extends Application {

    private Rotate rotateX = new Rotate(30, Rotate.X_AXIS);
    private Rotate rotateY = new Rotate(30, Rotate.Y_AXIS);

    @Override
    public void start(Stage primaryStage) {
        RubixCube cube = new RubixCube();
        Group cubeGroup = cube.buildCube();
        cubeGroup.getTransforms().addAll(rotateX, rotateY);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setTranslateZ(-650);
        camera.setNearClip(0.1);
        camera.setFarClip(1000.0);

        Group sceneContent = new Group(cubeGroup);
        sceneContent.getChildren().addAll(LightingUtil.createLighting());

        SubScene subScene = new SubScene(sceneContent, 800, 680, true, SceneAntialiasing.BALANCED);
        subScene.setCamera(camera);
        subScene.setFill(Color.LIGHTGRAY);

        InteractionUtil.enableRotation(subScene, rotateX, rotateY);
        InteractionUtil.enableZoom(subScene, camera);

        AnchorPane uiOverlay = UIUtil.createUI(cube, camera, cubeGroup, rotateX, rotateY);

        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: white;");
        root.getChildren().addAll(subScene, uiOverlay);

        Scene scene = new Scene(root, 800, 680);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Rubik's Cube 3D");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
