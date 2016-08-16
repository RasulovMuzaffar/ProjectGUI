/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;

/**
 *
 * @author Muzaffar
 */
public class Points {

    private double x;
    private double y;

    @Override
    public String toString() {
        return "["  + x + " , " + y + ']';
    }

    public Points(double x, double y) {
        this.x = x;
        this.y = y;
    }

    Points() {
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

}
