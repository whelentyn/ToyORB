package broker;
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import info.InfoMarket;
import info.InfoMarketImpl;
import org.json.simple.*;

public class ToyORB {

    private static Map<String, String> bindings = new HashMap<>();
    private static Map<String, String> clientServerBind = new HashMap<>();
    private static InfoMarketImpl infoMarket = new InfoMarketImpl();


    public static JSONObject handleRequest(JSONObject request) {
        JSONObject response = new JSONObject();
        String method = (String) request.get("method");
        JSONArray args = (JSONArray) request.get("args");
        String res = null;

        switch(method) {
            case "LOOKUP":
                res = lookup(args);
                break;
            case "BIND":
                res = bind(args);
                break;
            case "GET_TEMP":
                res = get_temp(args);
                break;
            case "GET_INFO":
                res = get_road_info(args);
                break;
        }
        response.put("result", res);
        return response;
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started on port 1234");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected from " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String line = in.readLine();
                JSONObject request = (JSONObject) JSONValue.parse(line);

                JSONObject response = handleRequest(request);
                out.println(response.toJSONString());

                //in.close();
                //out.close();
                //clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String lookup(JSONArray args) {
        String res;
        Object obj = bindings.get((String)args.get(0));
        if (obj == null) {
            throw new RuntimeException("Name not found: " + args.get(0));
        }
        clientServerBind.put(bindings.get((String)args.get(0)), (String)(args.get(1)));
        res = "Client " + args.get(1) + " successfully bounded with server named " + args.get(0);
        return res;
    }

    public static String bind(JSONArray args) {
        String res;
        if (bindings.containsKey((String)args.get(0))) {
            throw new RuntimeException("Name already bound: " + args.get(0));
        }
        bindings.put((String)args.get(1), (String)args.get(0));
        res = "Bound server connected at port " + args.get(0) + " with name " + args.get(1);
        return res;
    }

    public static String get_road_info(JSONArray args) {
        String res = null;
        res = infoMarket.get_road_info(((Long)args.get(1)).intValue());
        return res;
    }

    public static String get_temp(JSONArray args) {
        String res = null;
        res = String.valueOf(infoMarket.get_temp((String)args.get(1)));
        return res;
    }

}
