/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectgui;

/**
 *
 * @author Muzaffar
 */
public class Formuli {

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
    static double Cdn;
    static double Cst;
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
    static double sigmZ;
    static double sigmY;
    static double tauZY;
    static double sigm1;
    static double sigm2;
    static double a = 0.85;

    static double delta;
    static double delta1_0;
    static double delta1;
    static double delta3;
    static double delta2_1;
    static double delta2_2;
    static double b0;
    static double alfa1;
    static double bpl;
    static double Kc_1;
    static double k;
    static double Ah;
    static double Kfi_1;
    static double Cdn_min;
    static double Fidn_min;

    public static void main(String[] args) {
        delta = 30;
        sigm1 = 10;
        sigm2 = 5;

        sigmY = ((sigm1 + sigm2 + (sigm1 - sigm2)) * (Math.cos(2 * Math.toRadians(delta)))) / 2;
        sigmZ = ((sigm1 + sigm2 - (sigm1 - sigm2)) * Math.cos(2 * Math.toRadians(delta))) / 2;
        tauZY = ((sigm1 - sigm2) * Math.sin(2 * Math.toRadians(delta))) / 2;
        System.out.println("--" + sigmY + " " + sigmZ + " " + tauZY);

        n = Math.log10(delta1);
        delta1_0 = delta2_1 + delta2_2;
        if (Math.abs(y - 0.5 * b0) <= 1.35) {
            Fi_y = 0;
        } else if (Math.abs(y - 0.5 * b0) > 1.35) {
            Fi_y = Math.abs(y - 0.5 * b0) - 1.35;
        }
        delta3 = Math.abs(delta1) / (1.5 / Math.tan(alfa1));

        b0 = l_sh + 2 * h_bl * Math.tan(Math.toRadians(30));
        a = (bpl - b0) / 2;

        if (y <= a) {
            Fihi_j = 0;
        } else if (y > a) {
            Fihi_j = (y - a) * Math.tan(alfa1);
        }
        Azy = A0 * Math.exp(n * z - delta1_0 * Fi_y + delta3 * Fihi_j);

        Cdn = Cst * (Kc_1 + Kc * Math.exp(-k * (Azy - Ah)));
        Fidn = Fist * (Kfi_1 + Kfi * Math.exp(-k * Azy));
        Kc = (Cst - Cdn_min) / Cst;
        Kfi = (Fist - Fidn_min) / Fist;
        Kc_1 = Cdn_min / Cst;
        Kfi = Fidn_min / Fist;

    }
}
