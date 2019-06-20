
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {

        try {
            //String API_KEY = "531bb4816b5f8ed16b7ef76181bc2634";
            Scanner L = new Scanner(System.in);

            System.out.println("Enter the location:(format: City, Country Code ");
            System.out.println("For example: Ottawa,CA ");

            String LOCATION  = L.nextLine();
            System.out.println("location is " + LOCATION);

         // reading in API using HTTP protocols

            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + LOCATION + "&appid=531bb4816b5f8ed16b7ef76181bc2634&units=metric";
            System.out.println(url);
            URL obj = new URL (url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            //optional default is GET
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null ){

                response.append(inputLine);
            } in.close();


            // part 2 convert to JSON object

            JSONObject obj_JSONObject = new JSONObject (response.toString());
            JSONObject a  = (JSONObject) obj_JSONObject.get("main");
            JSONObject b = (JSONObject) obj_JSONObject.get("wind");
            JSONArray c =  obj_JSONObject.getJSONArray("weather");

            JSONObject f = c.getJSONObject(0);


            System.out.println(a);

            Thread.onSpinWait();
            // Storing my values here
            //System.out.println("temp is :" + a.getInt("temp"));
            int temp = a.getInt("temp");

            int maxtemp = a.getInt("temp_min");
            int mintemp = a.getInt("temp_max");

            int windspeed  = b.getInt("speed");
            String state = f.getString("main");



            String city =  obj_JSONObject.getString("name");
            //System.out.println("city is "+ city  );

            System.out.println("The temp in " + city + " is: "+ temp + " degrees");
            System.out.println( "the max temp is " + maxtemp + " and the min temp is " + mintemp );
            System.out.println("wind speed is "+ windspeed);
            System.out.println("State is " + state);

        }catch (Exception e){


        }

    }

} //end of class

