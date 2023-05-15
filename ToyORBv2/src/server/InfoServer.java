package server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class InfoServer {

    public static void bind(Socket socket, String portNumber, String serverName) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        JSONArray arguments = new JSONArray();
        arguments.add(portNumber);
        arguments.add(serverName);

        JSONObject jsonRequest = new JSONObject();
        jsonRequest.put("method", "BIND");
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
            Socket socket = new Socket("localhost", portNumber);
            System.out.println("Connected to server on port " + portNumber);

            bind(socket,"1234", "InfoMarket");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
