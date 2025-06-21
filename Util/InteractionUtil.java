package Rubix.Util;

import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Rotate;

public class InteractionUtil {
    public static void enableRotation(SubScene scene, Rotate rotateX, Rotate rotateY) {
        final double[] anchorX = new double[1];
        final double[] anchorY = new double[1];
        final double[] anchorAngleX = new double[1];
        final double[] anchorAngleY = new double[1];

        scene.setOnMousePressed((MouseEvent event) -> {
            anchorX[0] = event.getSceneX();
            anchorY[0] = event.getSceneY();
            anchorAngleX[0] = rotateX.getAngle();
            anchorAngleY[0] = rotateY.getAngle();
        });

        scene.setOnMouseDragged((MouseEvent event) -> {
            rotateX.setAngle(anchorAngleX[0] - (anchorY[0] - event.getSceneY()));
            rotateY.setAngle(anchorAngleY[0] + (anchorX[0] - event.getSceneX()));
        });
    }

    public static void enableZoom(SubScene scene, PerspectiveCamera camera) {
        scene.setOnScroll((ScrollEvent event) -> {
            double zoomSpeed = 15;
            if ((camera.getTranslateZ() < -850 && event.getDeltaY() < 0) || (camera.getTranslateZ() > -500 && event.getDeltaY() > 0)) {
                zoomSpeed = 0;
            }
            else {
                zoomSpeed = 15;
            }
            camera.setTranslateZ(camera.getTranslateZ() + (event.getDeltaY() > 0 ? zoomSpeed : -zoomSpeed));
        });
    }

    public static void zoomIn(PerspectiveCamera camera) {
        double zoomSpeed = 15;
        if (camera.getTranslateZ() > -500) {
            zoomSpeed = 0;
        } else {
            camera.setTranslateZ(camera.getTranslateZ() + zoomSpeed);
        }
    }

    public static void zoomOut(PerspectiveCamera camera) {
        double zoomSpeed = 15;
        if (camera.getTranslateZ() < -850) {
            zoomSpeed = 0;
        } else {
            camera.setTranslateZ(camera.getTranslateZ() - zoomSpeed);
        }
    }
}
