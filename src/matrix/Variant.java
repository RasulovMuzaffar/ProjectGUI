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
public class Variant {

    static double x0 = 400, y0 = 100, x_0 = 400, y_0 = 100;
    static double B = 7.6;
    static double H = 4;
    static double h_bl = 0.5;
    static double l_sh = 2.7;
    ////////////////////////////
    static double K;
    static double Kfi;
    static double Kfi1;
    static double Kc;
    static double Fidn;
    static double Fist;
    static double Fi_y;
    static double A0;
    static double Azy;
    static double n;
    static double z;
    static double y;
    static double S1;
    static double S1_0;
    static double S0;
    static double Fihi_j;
    static double a = 0.85;//Ширина обочины

    ////////////////////////////
    static Points[][] p = new Points[4][11];

    public static int[] X = new int[5];
    public static int[] Y = new int[5];

    public static void mayn() {
        double alfa1 = Math.atan(1); //получаем в радианах

//        X[0]=1;
        double b0 = (l_sh + 2 * h_bl * Math.tan(Math.toRadians(30)));
        double dH = (0.1 * b0);
        double a = (B - b0) / 2;

        double alfa = Math.toDegrees(Math.atan(H / (a + H * 1 / Math.tan(alfa1))));

        int k = 0;
        boolean b = true;
        for (int i = 0; i < p.length; i++) {
            k++;

            for (int j = 0; j < p[i].length; j++) {

                if ((j < p.length - k) || (j > p[i].length - p.length - 1 + k)) {
//                    p[i][j] = 0;
/*Заполняем нулями*/
                    Points point = new Points();
                    point.setX(0);
                    point.setY(0);
                    p[i][j] = point;
//                    p[i][j] = 1;
                    b = false;
                } else if (i == 0) {
                    OS(i, j);
                } else //                    System.out.println("b " + b);
                {
                    if (!b) {
                        OSX(i, j, alfa, dH);
                        b = true;
                    } else if (j > p[i].length - p.length - 2 + k) {
                        OSX0(i, j, dH);
                    } else {
                        Points point = new Points();

//                        System.out.println("p[i-1][j].toString() ---->>> " + p[i - 1][j].toString() + "--p[i][j-1].toString()-- " + p[i][j - 1].toString());
                        if (j < p.length || j >= p[i].length - p.length) {
                            if (j == p.length - 1) {
                                point.setX(3);
                                point.setY(3);
                                p[i][j] = point;
                            } else {
                                point.setX(2);
                                point.setY(2);
                                p[i][j] = point;
                            }
                        } else {
                            point.setX(1);
                            point.setY(1);
                            p[i][j] = point;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                System.out.print("" + p[i][j].toString());
            }
            System.out.println("");
        }
        drawLX();
        drawLY();
//////////////////
    }
//

    private static void OSX0(int i, int j, double dH) {
        Points point = new Points();

        if (i != 0) {
            x_0 = (x_0 + Math.cos(Math.toRadians(0)) * (100 * dH));//x_0+1;
            y_0 = (y_0 + Math.sin(Math.toRadians(0)) * (100 * dH));//y_0+1;
        }
        point.setX(x_0);
        point.setY(y_0);
        p[i][j] = point;

    }

    private static void OSX(int i, int j, double alfa, double dH) {
        Points point = new Points();

        if (i != 0) {
            x0 = (x0 + Math.cos(Math.toRadians(180 - alfa)) * (100 * dH));//x0-10;
            y0 = (y0 + Math.sin(Math.toRadians(180 - alfa)) * (100 * dH));//y0-10;
        }
        point.setX(x0);
        point.setY(y0);
        p[i][j] = point;

    }

    private static void OS(int i, int j) {
        Points point = new Points();

        point.setX(400);
        point.setY(100);
        p[i][j] = point;

    }

    private static void drawLX() {
//        System.out.println("--------------------");
//        int[] X = new int[10];
//        for (int i = 1; i <= 1; i++) {
//            for (int j = 0; j < p[i].length; j++) {
//                X[i] = (int) p[i][j].getX();
//            }
//        }

        for (int i = 0; i < 3; i++) {
            X[i] = (int) p[i][1].getX();
//            System.out.println("--->>> "+p[i][1].getX());
        }
//        System.out.println("pLX " + X);
//        return X;
    }

    private static void drawLY() {
//        int[] pY = new int[10];
//        System.out.println("+++++++++++++");
//        for (int i = 1; i <=1; i++) {
//            for (int j = 0; j < p[i].length; j++) {
//                Y[i] = (int) p[i][j].getY();
//            }
//        }
        for (int i = 0; i < 3; i++) {
            Y[i] = (int) p[i][1].getY();
        }
//        System.out.println("pLY " + Y);
//        return Y;
    }

    private double FiDn(double alfa) {
        n = Math.log10(S1);
        if (Math.abs(y + 0.5 * B) <= 1.35) {
            Fi_y = 0;
        } else {
            Fi_y = Math.abs(y + 0.5 * B) - 1.35;
        }
        if (y <= a) {
            Fihi_j = 0;
        } else {
            Fihi_j = (y - a) * Math.toRadians(180 - alfa);
        }
        Azy = A0 * Math.exp(n * z - S1_0 * Fi_y + S0 * Fihi_j);
        Fidn = Fist * (Kfi1 + Kfi * Math.exp(-K * A0 * Math.exp(n * z - S1_0 * (Math.abs(y + 0.5 * B) - 1.35) + Fihi_j * Fi_y)));
        return 1;
    }
}
