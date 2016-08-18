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
public class NewClass {

    //Геометрия
    private static double H = 6;//насыпь высота
    private static double b_pl = 7.6;//ширина основной площадки
    private static double m = 2;//заложение откоса
    private static double B0 = 6;//ширина площадки загружения
    private static double hb = 0.35;//толщина балласта
    private static double l_sh = 2.7;//длина шпалы

    //Характеристики грунтов
    private static double gamma = 6;//объемный вес грунта
    private static double C = 6;//удельное сцепление
    private static double Fi = 6;//угол внутренного трение
    private static double Kc = 6;//коэфф относ. сниж. удель. сцепления
    private static double Kfi = 6;//коэфф относ. сниж. угла внутренного трения
    private static double K = 6;//коэфф виброразрушения

    //Нагрузка-динамика
    private static double A0 = 6;//амп колебании
    private static double A = 1;
    private static double delta1 = 6;//коэфф затухания по глубине
    private static double delta2_1 = 6;//коэфф затухания по горизонтале в первой зоне
    private static double delta2_2 = 6;//коэфф затухания по горизонтале во второй зоне
    private static double Q = 6;//пригрузка на основание земП
    private static double gamma_bl = 6;//объемный вес балласта

    //др.
    private static double E = 6;//погрешность расчетов

    ////////////////////////////////////////////////////////////////////////////////
    private static double tan_alfa1;
    private static double b0;
    private static double a;
    private static double y;
    private static double Fi_y;
    private static double delta3;
    private static double Fihi_j;
    private static double Azy;
    private static double n;
    private static double z;
    private static double Kc_1;
    private static double Kfi_1;

    private static double Cdn;
    private static double Fidn;

    private static double B;
    private static double D;
    private static double Fi1hi;
    private static double EF;
    private static double delta;//определяется при решении системы
    private static double sigma;//определяется при решении системы
    private static double delta1_0;

    private static double Fia;
    private static double alfa;
    private static double tan_alfa;
    private static double mju;
    private static double delta_iO;
    private static double delta_i;
    private static double sigma_iO;
//    private static double mjuij1;
//    private static double mjui1j;

    public static void main(String[] args) {
        tan_alfa1 = Math.atan(1 / m); //получаем угол в радиансах

        System.out.println("tan_alfa1 " + Math.toDegrees(tan_alfa1));

        b0 = l_sh + 2 * hb * Math.tan(Math.toRadians(30));
        System.out.println("b0 " + b0);
        a = (b_pl - b0) / 2;
        System.out.println("a " + a);
        if (Math.abs(y - 0.5 * b0) <= l_sh / 2) {
            Fi_y = 0;
        } else if (Math.abs(y - 0.5 * b0) > l_sh / 2) {
            Fi_y = Math.abs(y - 0.5 * b0) - l_sh / 2;
        }

        delta3 = Math.abs(Math.log(delta1)) * tan_alfa1 / 1.5;

        if (y <= 0.5 * b_pl) {
            Fihi_j = 0;
        } else if (y > 0.5 * b_pl) {
            Fihi_j = (y - 0.5 * b_pl) * tan_alfa1;
        }

        n = Math.log(delta1); //-------------------
//        delta1_0 = 

        delta1_0 = delta2_1 + delta2_2;

        Azy = A0 * Math.exp(n * z - delta2_1 * Fi_y - delta2_2 * (y - l_sh / 2) - delta3 * Fihi_j);

        Kc_1 = 1 - Kc;
        Kfi_1 = 1 - Kfi;

        Cdn = C * (Kc_1 + Kc * Math.exp(-K * (Azy - A0)));
        Fidn = Fi * (Kfi_1 + Kfi * Math.exp(-K * Azy));

        ///////////////////////////////////////////////////////
        if (y <= 0.5 * b_pl) {
            Fi1hi = 0;
        } else {
            Fi1hi = tan_alfa1;
        }

//        delta1_0 = delta2_1 + delta2_2;
        EF = K * A0 * Math.exp(n * z - delta1_0 * y + 1.35 * delta1_0 + Math.abs(n) * 0.667 * Fi1hi * tan_alfa1 - K * Azy);

        B = 0.15 * gamma * H * A - EF * ((delta1_0 - Math.abs(n) + 0.667 * Fi1hi * tan_alfa1) * Math.sin(Math.toRadians(2 * delta))
                + n * Math.cos(Math.toRadians(2 * delta)))
                * (Fi * Kfi * (sigma * Math.cos(Math.toRadians(Fidn)) - Cdn * Math.sin(Math.toRadians(Fidn)))
                + C * K * Math.cos(Math.toRadians(Fidn)));

        D = 0.04 * gamma * H * A + EF * (n * Math.sin(Math.toRadians(2 * delta))
                - (delta1_0 - Math.abs(n) * 0.667 * Fi1hi * tan_alfa1)
                * Math.cos(Math.toRadians(2 * delta)) + n * Math.cos(Math.toRadians(2 * delta)))
                * (Fi * Kfi * (sigma * Math.cos(Math.toRadians(Fidn)) - Cdn * Math.sin(Math.toRadians(Fidn)))
                + C * K * Math.cos(Math.toRadians(Fidn)));

        alfa = Math.PI / 4 + Fidn / 2;

        double aaalfa = Math.atan(H / (a + H / (Math.tan(tan_alfa1))));
        System.out.println("aaalfa===>>>> " + Math.toDegrees(aaalfa));

        tan_alfa = Math.tan(alfa);
        System.out.println("tan_alfa " + Math.toDegrees(tan_alfa));

        mju = Math.PI / 4 - Fidn / 2;

        if (y > a + H / tan_alfa1) {
            Fia = 0;
        } else if (a < y && y < H / tan_alfa1) {
            Fia = gamma * y * (tan_alfa - tan_alfa1) + gamma * a * tan_alfa1;
        } else if (y < a) {
            Fia = gamma * y * tan_alfa;
        }

        sigma = (Fia * Math.cos(alfa) + Cdn * Math.cos(2 * (delta - alfa))) / (1 - Math.sin(Fidn) * Math.cos(2 * (delta - alfa)));
        System.out.println("sigma --> " + sigma);

//.......................ЗОНА 1
        delta = 1 / 2 * Math.asin(Fia * Math.sin(alfa)
                * (Cdn + Fia * Math.tan(Fidn) * Math.cos(alfa)
                - Math.sqrt(Math.pow(Cdn + Fia * Math.cos(alfa) * Math.tan(Fidn), 2)
                        * Math.sin(Math.pow(Fidn, 2))
                        - (Math.pow(Fia * Math.sin(alfa) * Math.sin(Fidn), 2)))) / (Math.cos(Fidn)
                * (Math.pow(Fia * Math.sin(alfa) * Math.tan(Fidn), 2)
                + Math.pow(Cdn + Fia * Math.cos(alfa) * Math.tan(Fidn), 2)))) + alfa;
        //КАРОЧЕ СИСТЕМА ЗОНЫ 1

//        mju =  
////////////////////////////////////////////////////
//// формулы 4.42 и 4.43
NewClass nc = new NewClass();
//        int i = 0;
//delta_iO = (Math.PI - 2*alfa/20)*i + alfa;
        sigma_iO = ((gamma * Fia * Math.cos(alfa) - Cdn * Math.cos(Fidn) * Math.cos(2 * delta_iO * Math.tan(Fidn)))
                / (1 - Math.sin(Fidn) * Math.cos(2 * nc.delta_iO(10) - alfa)))*Math.exp(2*delta_i*Math.tan(Fidn));

    }
//формула 4.42
    double delta_iO(int i) {
        delta_iO = (Math.PI - 2 * alfa / 20) * i + alfa;
        return delta_iO;
    }

}
