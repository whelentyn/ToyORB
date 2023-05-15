package info;

public class InfoMarketImpl implements InfoMarket{
    public String get_road_info (int road_ID) {
        String res = new String();
        switch (road_ID) {
            case 1:
                res = "Real time information for road with ID " + road_ID + " are: " +
                        "traffic: free / " + "radar: on / " + "speed limit: 50 km/h / " + "working: off";
                break;
            case 2:
                res = "Real time information for " + road_ID + " are: " +
                        "traffic: jam / " + "radar: off / " + "speed limit: 25 km/h / " + "working: on";
                break;
            case 3:
                res = "Real time information for " + road_ID + " are:" +
                        "traffic: accident / " + "radar: on / " + "speed limit: 15 km/h / " + "working: off";
                break;

        }
        return res;
    }

    public double get_temp (String city) {
        double res = 0;
        switch (city) {
            case "Timisoara":
                res = 28;
                break;
            case "Bucuresti":
                res = 30;
                break;
            case "Brasov":
                res = 25;
                break;
        }
        return res;
    }
}
