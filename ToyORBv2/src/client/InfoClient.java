package client;
import java.net.*;
import java.io.*;
import org.json.simple.*;

public class InfoClient {

    public static void get_road_info(Socket socket, String serverName, int arg) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        JSONArray arguments = new JSONArray();
        arguments.add(serverName);
        arguments.add(arg);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("method", "GET_INFO");
        jsonRequest.put("args", arguments);

        out.println(jsonRequest.toJSONString());
        System.out.println("Sent request: " + jsonRequest.toJSONString());

        String line = in.readLine();
        JSONObject jsonResponse = (JSONObject) JSONValue.parse(line);
        System.out.println("Received response: " + jsonResponse.toJSONString());

        in.close();
        out.close();
    }

    public static void get_temp(Socket socket, String serverName, String arg) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        JSONArray arguments = new JSONArray();
        arguments.add(serverName);
        arguments.add(arg);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("method", "GET_TEMP");
        jsonRequest.put("args", arguments);

        out.println(jsonRequest.toJSONString());
        System.out.println("Sent request: " + jsonRequest.toJSONString());

        String line = in.readLine();
        JSONObject jsonResponse = (JSONObject) JSONValue.parse(line);
        System.out.println("Received response: " + jsonResponse.toJSONString());

        in.close();
        out.close();
    }

    public static void lookup (Socket socket, String clientName, String serverName) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        JSONArray arguments = new JSONArray();
        arguments.add(serverName);
        arguments.add(clientName);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("method", "LOOKUP");
        jsonRequest.put("args", arguments);

        out.println(jsonRequest.toJSONString());
        System.out.println("Sent request: " + jsonRequest.toJSONString());

        String line = in.readLine();
        JSONObject jsonResponse = (JSONObject) JSONValue.parse(line);
        System.out.println("Received response: " + jsonResponse.toJSONString());

        in.close();
        out.close();
    }
    public static void main(String[] args) {
        try {
            int portNumber = 1234;
            Socket socketConnection = new Socket("localhost", portNumber);
            System.out.println("Connected to server on port 1234");

            lookup(socketConnection, "InfoMarketClient", "InfoMarket");

            Socket socketResponse = new Socket("localhost", portNumber);
            get_road_info(socketResponse,"InfoMarket", 3);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
