package com.example.mydrinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class REST_API extends AppCompatActivity {
    private String apiUrl="https://retoolapi.dev/OM15Fh/content";

    public String getPostDataString(HashMap<String, String> params)
        throws Exception{
        StringBuilder feedback=new StringBuilder();
        boolean first=true;
        for(Map.Entry<String, String> entry: params.entrySet()){
            if(first)
                first=false;
            feedback.append("&");
            feedback.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            feedback.append("=");
            feedback.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return  feedback.toString();
    }

    public String postData(String content)
        throws Exception{
        String result = "";
        HashMap<String, String> params=new HashMap<>();
       // params.put("methodName", methodName);
        params.put("content", content);
       // params.put("fileJSON", fileJSON);
        URL url = new URL(apiUrl);
        HttpURLConnection client = (HttpURLConnection)url.openConnection();
        client.setRequestMethod("POST");
        client.setRequestProperty("multipart/form-data", apiUrl+";charset=UTF-8");

        client.setDoOutput(true);
        client.setDoInput(true);

        OutputStream os = client.getOutputStream();
        BufferedWriter writer=new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8")
        );
        String a=getPostDataString(params);
        writer.write(a);
        writer.close();
        os.close();

        int ResponseCode = client.getResponseCode();
        if(ResponseCode == HttpURLConnection.HTTP_OK){
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(client.getInputStream())
            );
            String line;
            while((line = br.readLine())!=null){
                result+=line+"\n";
            }
            br.close();
        }else{
            throw new Exception("HTTP ERROR Response Code" + ResponseCode);
        }
        return result;
    }
    public boolean match(String expression, String input)
            throws IllegalArgumentException, PatternSyntaxException {
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(input);
        return m.matches();
    }
    public void AsyncDataGetContentResultManipulation(
            String methodName, String content, String fileJSON
    )
            throws IOException, ExecutionException, InterruptedException{
        String result = "";
        HashMap<String, String> params=new HashMap<>();
        // params.put("methodName", methodName);
        params.put("content", content);
        // params.put("fileJSON", fileJSON);
        //AsyncHttpClient client = new AsyncHttpClient(){

        //}

    }
}
