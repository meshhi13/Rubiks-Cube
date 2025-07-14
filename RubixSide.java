package Rubix;

import javafx.scene.paint.Color;

public class RubixSide {
    static final int SIZE = 3;
    Color[][] values = new Color[SIZE][SIZE];

    public RubixSide(Color color) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] = color;
            }
        }
    }

    public void reset(Color color) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                values[i][j] = color;
            }
        }

    }

    public void rotateSideCW(){
        int m = values.length;
        int n = values[0].length;

        Color[][] newValues = new Color[m][n];

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                newValues[j][n - i - 1] = values[i][j];
            }
        }

        values = newValues;
    }

    public void rotateSideCCW(){
        int m = values.length;
        int n = values[0].length;
        
        Color[][] newValues = new Color[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newValues[n - j - 1][i] = values[i][j];
            }
        }
        
        values = newValues;
    }

    public Color[] getRow(int row){
        Color[] getRow = new Color[SIZE];
        for (int i = 0; i < SIZE; i++){
            getRow[i] = values[i][row];
        }

        return getRow;
    }

    public Color[] getCol(int col){
        return values[col];
    }

    public void setRow(int row, Color[] newValues){
        for (int i = 0; i < SIZE; i++){
            values[i][row] = newValues[i];
        }
    }

    public void setCol(int col, Color[] newValues){
        values[col] = newValues;
    }
}
