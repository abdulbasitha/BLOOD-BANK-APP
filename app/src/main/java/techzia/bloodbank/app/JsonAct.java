package techzia.bloodbank.app;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class JsonAct {

        // api response path (eg: http://localhost/BloodBankApp/)

        String path="";


    public JsonAct()
    {

    }

    public String setJsonVal(String upath)
    {
        HttpURLConnection c = null;
        try {
            URL u=new URL(path+upath);
            Log.v("FilmyPeople", "URL = "+u.toString());
            c=(HttpURLConnection) u.openConnection();
            c.setRequestMethod("GET");
            c.setRequestProperty("Content-length","0");
            c.setUseCaches(false);
            c.setAllowUserInteraction(false);
            c.connect();
            int status=c.getResponseCode();

            switch (status)
            {
                case 200:
                case 201:
                    BufferedReader br=new BufferedReader(new InputStreamReader(c.getInputStream()));
                    StringBuilder sb=new StringBuilder();
                    String line="";
                    while ((line=br.readLine())!=null)
                    {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
                default:
                    return "";
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
