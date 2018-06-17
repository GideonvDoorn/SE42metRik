package com.client;

import auction.domain.Item;
import auction.domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class AuctionWebServiceImpl {


    public static void main(String args[]) {

        AuctionWebServiceImpl a = new AuctionWebServiceImpl();
        try {
            a.registerUser("test@nll");
            a.getUser("test@nll");
            a.findItemByDescription("omsch2");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerUser(String email)throws IOException{


        URL url = new URL("http://localhost:8080/registerUser/" + email);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        System.out.println(content);

    }

    public User getUser(String email) throws IOException{
        URL url = new URL("http://localhost:8080/getUser/" + email);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        User u = new Gson().fromJson(content.toString(), new TypeToken<User>(){}.getType());

        System.out.println(u.getEmail());

        return u;
    }

    public Item findItemByDescription(String desc) throws IOException{
        URL url = new URL("http://localhost:8080/findItemByDescription/" + desc);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        Item item = new Gson().fromJson(content.toString(), new TypeToken<User>(){}.getType());

        System.out.println(item.getDescription());

        return item;
    }

}
