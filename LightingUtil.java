package Rubix;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PointLight;
import javafx.scene.paint.Color;

public class LightingUtil {
    public static Group createLighting() {
        AmbientLight ambient = new AmbientLight(Color.rgb(80, 80, 80));

        PointLight keyLight = new PointLight(Color.WHITE);
        keyLight.setTranslateX(-300);
        keyLight.setTranslateY(-300);
        keyLight.setTranslateZ(-300);

        PointLight fillLight = new PointLight(Color.WHITE);
        fillLight.setTranslateX(300);
        fillLight.setTranslateY(300);
        fillLight.setTranslateZ(300);

        return new Group(ambient, keyLight, fillLight);
    }
}