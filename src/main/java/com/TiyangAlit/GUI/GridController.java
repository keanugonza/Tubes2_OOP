package com.TiyangAlit.GUI;

import com.TiyangAlit.Ladang.Matrix;
import javafx.scene.layout.GridPane;

public class GridController {

    public Matrix<Double> XConstraint;
    public Matrix<Double> YConstraint;

    public GridController() {
        this.XConstraint = new Matrix<Double>(5,2);
        this.YConstraint = new Matrix<Double>(4,2);
        // X CONSTRAINT
        this.XConstraint.setEl(0,0,75.0);
        this.XConstraint.setEl(0,1, 165.0);
        this.XConstraint.setEl(1,0, 170.0);
        this.XConstraint.setEl(1,1, 260.0);
        this.XConstraint.setEl(2,0, 265.0);
        this.XConstraint.setEl(2,1, 355.0);
        this.XConstraint.setEl(3,0, 360.0);
        this.XConstraint.setEl(3,1, 450.0);
        this.XConstraint.setEl(4,0, 455.0);
        this.XConstraint.setEl(4,1, 546.0);

        // Y CONSTRAINT
        this.YConstraint.setEl(0,0,162.0);
        this.YConstraint.setEl(0,1,292.0);
        this.YConstraint.setEl(1,0,299.0);
        this.YConstraint.setEl(1,1,429.0);
        this.YConstraint.setEl(2,0,435.0);
        this.YConstraint.setEl(2,1,565.0);
        this.YConstraint.setEl(3,0,571.0);
        this.YConstraint.setEl(3,1,701.0);
    }

    public int[] getColRowFromPosition(double XPos, double YPos){
        int row = -1;
        int col = -1;

        for(int i = 0; i < 5; i++){
            if(this.XConstraint.getEl(i,0) < XPos && this.XConstraint.getEl(i,1) > XPos){
                col = i;
                break;
            }
        }

        for(int i = 0; i < 4; i++){
            if(this.YConstraint.getEl(i,0) < YPos && this.YConstraint.getEl(i,1) > YPos){
                row = i;
                break;
            }
        }

        return new int[]{row, col};
    }

    public static void FillGrid(GridPane Grid, int row, int col){

        int colCount = 0;
        int rowCount = 0;
        int count = 0;
        while(count < row * col){
            CardComponent newCard = new CardComponent("/StardewValley.png", "Daging Babi lah", Grid, colCount, rowCount);
            Grid.add(newCard, colCount, rowCount);
            if (colCount % col == 4){
                colCount = 0;
                rowCount++;
            } else{
                colCount++;
            }
            count++;
        }
    }
}
