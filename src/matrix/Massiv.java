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
public class Massiv {

    static double x0 = 400, y0 = 100, x_0 = 400, y_0 = 100;
    static double B = 7.6;
    static double H = 4;
    static double h_bl = 0.5;
    static double l_sh = 2.7;

    static Points[][] p = new Points[10][5];

    public static void main(String[] args) {

        double alfa1 = Math.atan(1); //получаем в радианах

        double b0 = (l_sh + 2 * h_bl * Math.tan(Math.toRadians(30)));
        double dH = (0.1 * b0);
        double a = (B - b0) / 2;

        double alfa = Math.toDegrees(Math.atan(H / (a + H * 1 / Math.tan(alfa1))));

        System.out.println(" " + (x0 + Math.cos(Math.toRadians(0)) * (dH * 100)));
        System.out.println(" " + (y0 + Math.sin(Math.toRadians(0)) * (dH * 100)));

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                Points point = new Points();
                switch (j) {
                    case 0: {

                        OSX0(i, j, dH);

                        break;
                    }
                    case 4: {
                        OSX(i, j, alfa, dH);

                        break;
                    }
                    default: {
                        double x1 = (0);
                        double y1 = (0);
                        point.setX(x1);
                        point.setY(y1);
                        p[i][j] = point;

                        break;
                    }
                }
            }
        }
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                System.out.print("[ " + p[i][j].toString() + " ],");
            }
            System.out.println("");
        }
    }

    private static void OSX0(int i, int j, double dH) {
        Points point = new Points();

        if (i != 0) {
            x_0 = (x_0 + Math.cos(Math.toRadians(0)) * (100 * dH));
            y_0 = (y_0 + Math.sin(Math.toRadians(0)) * (100 * dH));
        }
        point.setX(x_0);
        point.setY(y_0);
        p[i][j] = point;

    }

    private static void OSX(int i, int j, double alfa, double dH) {
        Points point = new Points();

        if (i != 0) {
            x0 = (x0 + Math.cos(Math.toRadians(180 - alfa)) * (100 * dH));
            y0 = (y0 + Math.sin(Math.toRadians(180 - alfa)) * (100 * dH));
        }
        point.setX(x0);
        point.setY(y0);
        p[i][j] = point;
    }
}
