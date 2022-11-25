package com.example.mydrinkinggame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public abstract class REST_API {
    private String apiUrl="https://6380fffe786e112fe1c021ed.mockapi.io/Challenges";

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
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

    public String postData(String methodName, String userName, String fileJSON)
        throws Exception{
        String result = "";
        HashMap<String, String> params=new HashMap<>();
        params.put("methodName", methodName);
        params.put("userName", userName);
        params.put("fileJSON", fileJSON);
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
            String line="";
            while((line = br.readLine())!=null){
                result+=line+"\n";
            }
            br.close();
        }else{
            throw new Exception("HTTP ERROR Response Code" + ResponseCode);
        }
        return result;
    }
}
