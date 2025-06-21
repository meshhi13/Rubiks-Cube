package Rubix;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class ColorUtil {
    private static final Map<String, String> hexToName = new HashMap<>();
    static {
        hexToName.put("#008000", "GREEN");
        hexToName.put("#FF0000", "RED");
        hexToName.put("#FFFF00", "YELLOW");
        hexToName.put("#FFFFFF", "WHITE");
        hexToName.put("#0000FF", "BLUE");
        hexToName.put("#00FFFF", "CYAN");
    }

    public static String getColorName(Color color) {
        String hex = String.format("#%02X%02X%02X",
            (int)(color.getRed()*255),
            (int)(color.getGreen()*255),
            (int)(color.getBlue()*255)
        );
        return hexToName.getOrDefault(hex, hex);
    }
}