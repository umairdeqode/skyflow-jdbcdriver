package org.jdbcdriver;

//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonParser;
//import com.google.gson.Gson;
//import com.google.api.client.json.JsonParser;
//import com.google.gson.*;
/*import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

//import org.json.simple.JSONObject;

public class HttpResponseHandler{
	
	 public HttpResponseHandler() {}
    
    
    public JSONArray SendPost (String s) throws Exception {
    	String token1 = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhY2MiOiJmNzk0ZmY4NmZiYzgxMWVhYmQ2YzNhOTExNDNlM2Q0MiIsImF1ZCI6Imh0dHBzOi8vbWFuYWdlLnNreWZsb3dhcGlzLmRldiIsImV4cCI6MTY3MjU4Mzg1MywiaWF0IjoxNjY5OTkxODUzLCJpc3MiOiJzYS1hdXRoQG1hbmFnZS5za3lmbG93YXBpcy5kZXYiLCJqdGkiOiJhNDliZjZiOGNhNjE0ZmFjODNhMGE5NmY2ZGRlZGJjMSIsInN1YiI6ImUzZWQ2YmE5OWY1NjQ1YzNiZjZmZGRhYmJiYmYzYzYyIn0.gMpiLGeHkzhx7bhk4lxg3CXvK7Re-hFQ0e9sHg8ogokmgJOHJa3M3iO1O1kFRR1OaABjYPOpD9iT2PbIGb3NvxWyl-av1co8AvNQKNmdsUXOn-NAkiva-3cy3dyhFfzqgMWxHBMhBpl5fIySkrAYjlFGG54Wc3QmcdCkqo2zxjJLgzyk9bDOcd5hoIdd9ePnWE4DWlk6F8fqYEVnTZg-Fa6nuPE0-rXAdfgngwC2H86Q4wc2XFFmBfmIJxUB7mwR6ySSC8woa5GYZ2is_xA3gVKjIzQ0ur9xD5Iqq0thoB2C8MP1OBv_OKJ8oBE_gGw_COubQfYByALAMNVFAd-hvg";
        //URL url = new URL("https://sb.area51.vault.skyflowapis.dev/v1/vaults/h54b9fa800cc4916974fdc7407463783/query");
        URL url = new URL("https://sb.area51.vault.skyflowapis.dev/v1/vaults/u4882705de68469d92b5aa1d9ada9740/query");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        //String authString = "Bearer " + Base64.getEncoder().withoutPadding().encodeToString(token.getBytes("utf-8"));
        String authString = "Bearer " + token1;
        conn.setRequestProperty("Authorization", authString);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

		/*
		 * Scanner sc = new Scanner(System.in); String str = sc.nextLine();
		 */
        String strFinal = "{ \"query\": \"" + s + "\"}";
        //System.out.println(strFinal);
        //String jsonInputString = "{ \"query\": \"select * from medicare;\"}";
        //String jsonInputString = "{ \"query\": \"select * from template;\"}";
		/*
		 * Gson g = new Gson(); String s1 = g.toJson(s);
		 */
        		
        try(OutputStream os = conn.getOutputStream()) {
            byte[] input = strFinal.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
//        //conn.getOutputStream().write(postDataBytes);
        int responseCode = conn.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String responsePost = sb.toString();
       
        JSONObject obj = new JSONObject(responsePost.toString());
        JSONArray arr = obj.getJSONArray("records");
        

        // Post request to json tree
        //JsonElement postTree = obj.getJso

       // System.out.println(postTree);
        //JsonArray postData = null;

		//if (postTree.isJsonObject()) {
			/*
			 * JsonElement postRows = postTree.getAsJsonObject().get("records");
			 * System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
			 * //System.out.println("Rows ---> " + postRows);
			 * 
			 * 
			 * if(postRows.isJsonArray()){
			 * //System.out.println("PostRows is a json array .................."); postData
			 * = postRows.getAsJsonArray();
			 * //System.out.println("&&&&&&&&&&&&&&&&&&&  "+postData.size()
			 * +" *************************************%%%%%%%%%%%%%%%%%%%%%%%%%%");
			 * //System.out.println(postData.get(0));
			 * //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			 * //System.out.println(postData.get(1));
			 * 
			 * // System.out.println(postData.getAsJsonObject()); }
			 * 
		 }*/
		return arr;
    }
    
    

	/*
	 * public HttpResponseHandler(String BASE_URL) {
	 * 
	 * this.BASE_URL = BASE_URL; this.requestFactory = new
	 * NetHttpTransport().createRequestFactory();
	 * 
	 * try {
	 * 
	 * NetHttpTransport.Builder builder = new NetHttpTransport.Builder();
	 * builder.doNotValidateCertificate();
	 * 
	 * this.requestFactory = builder.build().createRequestFactory(); } catch
	 * (GeneralSecurityException e) { throw new
	 * RuntimeException("Got General Security exception: " + e.getMessage()); } }
	 * 
	 * private void setHeaders (HttpRequest request, Map<String, String> headers) {
	 * if (headers != null) {
	 * 
	 * HttpHeaders httpHeaders = request.getHeaders();
	 * 
	 * for (String key : headers.keySet()) { httpHeaders.set(key, headers.get(key));
	 * } } }
	 * 
	 * private void setAuth (HttpRequest request) {
	 * 
	 * // No auth required }
	 * 
	 * public HttpRequest buildGetRequest (String api, Map<String, String> headers)
	 * throws IOException {
	 * 
	 * HttpRequest request = requestFactory.buildGetRequest( new GenericUrl(BASE_URL
	 * + api));
	 * 
	 * setHeaders(request, headers);
	 * 
	 * setAuth(request);
	 * 
	 * return request; }
	 */
}
