package mmp.gps.common.util;


public class MileageUtil {

    private static final double EARTH_RADIUS = 6378137.0D;


    private static double rad(double d) {
        return d * 3.141592653589793D / 180.0D;
    }

    public static double LantitudeLongitudeDist(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double radLon1 = rad(lon1);
        double radLon2 = rad(lon2);
        if (radLat1 < 0.0D) {
            radLat1 = 1.5707963267948966D + Math.abs(radLat1);
        }

        if (radLat1 > 0.0D) {
            radLat1 = 1.5707963267948966D - Math.abs(radLat1);
        }

        if (radLon1 < 0.0D) {
            radLon1 = 6.283185307179586D - Math.abs(radLon1);
        }

        if (radLat2 < 0.0D) {
            radLat2 = 1.5707963267948966D + Math.abs(radLat2);
        }

        if (radLat2 > 0.0D) {
            radLat2 = 1.5707963267948966D - Math.abs(radLat2);
        }

        if (radLon2 < 0.0D) {
            radLon2 = 6.283185307179586D - Math.abs(radLon2);
        }

        double x1 = 6378137.0D * Math.cos(radLon1) * Math.sin(radLat1);
        double y1 = 6378137.0D * Math.sin(radLon1) * Math.sin(radLat1);
        double z1 = 6378137.0D * Math.cos(radLat1);
        double x2 = 6378137.0D * Math.cos(radLon2) * Math.sin(radLat2);
        double y2 = 6378137.0D * Math.sin(radLon2) * Math.sin(radLat2);
        double z2 = 6378137.0D * Math.cos(radLat2);
        double d = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2));
        double theta = Math.acos((8.1361263181538E13D - d * d) / 8.1361263181538E13D);
        double dist = theta * 6378137.0D;
        return dist;
    }

    public static void DistanceOfTwoPoints(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= 6378137.0D;
        s = (double) (Math.round(s * 10000.0D) / 10000L);
        double ss = s * 1.0936132983377D;
        System.out.println("两点间的距离是：" + s * 0.001D + "米" + "," + (int) ss + "码");
    }

    public static void main(String[] args) {
        double L = LantitudeLongitudeDist(37.823226D, 112.54624D, 37.823182D, 112.546213D);
        DistanceOfTwoPoints(112.54624D, 37.823226D, 112.546213D, 37.823182D);
        DistanceOfTwoPoints(112.483546D, 37.760635D, 112.533848D, 37.777893D);
        System.out.println("L :" + L);
    }
}
