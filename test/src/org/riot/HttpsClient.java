package org.riot;


//http://www.mkyong.com/java/java-https-client-httpsurlconnection-example/

//error solution
//https://stackoverflow.com/questions/860187/access-restriction-on-class-due-to-restriction-on-required-library-rt-jar
//
//Go to the Build Path settings in the project properties.
//Remove the JRE System Library
//Add it back; Select "Add Library" and select the JRE System Library. The default worked for me.


import java.net.MalformedURLException;
import java.net.URL;
//import java.security.cert.Certificate;
import java.io.*;

import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLPeerUnverifiedException;

import org.json.*;
public class HttpsClient{
String[] api_keys;
int api_index;


 
public HttpsClient(String[] api_keys) {
	this.api_index = 0;
	this.api_keys = api_keys;
}

private void increaseApiIndex() {
	api_index ++;
	api_index %= api_keys.length;
}

/**
 * Gets the content from a connection
 * @param con Connection to the server
 * @return Content
 */
private String getContent(HttpsURLConnection con){
	if(con!=null){
		try {
			StringBuilder b = new StringBuilder();

			BufferedReader br = 
				new BufferedReader(
				new InputStreamReader(con.getInputStream()));  
			
			String input;
			
			while ((input = br.readLine()) != null){
				b.append(input + "\r\n");
			}
			br.close();
			return b.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	return "";
}

private String request(String api_location) throws ResponseException {
	URL url;
	
	//synchronized?
	if (api_location.contains("?")) {
		api_location += "&api_key=" + api_keys[api_index];
	} else {
		api_location += "?api_key=" + api_keys[api_index];
	}
	increaseApiIndex();
	
	try {
		url = new URL(api_location);
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		int responseCode = con.getResponseCode();
		if (responseCode != 200) {
			throw new ResponseException (con.getResponseCode() + " : " + con.getResponseMessage() + " : " + url.toString());
			//throw new Exception ("Response code: " + responseCode);
		}
		String data = getContent(con);
		System.out.println(data.length() + ":" + data);
		if (data.length() == 0) {
			System.out.println("No data received?");
		}
		return data;
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	System.out.println("No data return?");
	return "";
}

public JSONObject requestData(String api_location) throws ResponseException {
	String data = request("https://euw.api.pvp.net/api/lol/euw/" + api_location);
	return new JSONObject(data);
}

public JSONObject staticRequestData(String api_location) throws ResponseException {
	String data = request("https://na.api.pvp.net/api/lol/static-data/euw/" + api_location);
	return new JSONObject(data);
}

public JSONArray staticRequestDataArray(String api_location) throws ResponseException {
	String data = request("https://na.api.pvp.net/api/lol/static-data/euw/" + api_location);
	return new JSONArray(data);
}

 
//   private void testIt(){
//      String https_url = "https://euw.api.pvp.net/api/lol/euw/v1.2/champion?api_key=bf9782d6-8d7f-424a-bbfb-1b2dc389d2dc"; //"https://www.google.com/";
//      URL url;
//      try {
// 
//	     url = new URL(https_url);
//	     HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
// 
//	     //dumpl all cert info
//	     //print_https_cert(con);
//	     
//	     int responseCode = con.getResponseCode();
//	     System.out.println(responseCode);
// 
//	     //dump all the content
//	     //print_content(con);
//	     
//	     String data = getContent(con);
//		 //JSONArray conv = new JSONArray(data);
//		 JSONObject ah = new JSONObject(data);
//		 
//		 if (ah.has("champions")) {
//			 System.out.println("champion data found");
//			 JSONArray champions = ah.getJSONArray("champions");
//			 for (int i = 0; i< champions.length(); i++) {
//				 JSONObject champion = champions.getJSONObject(i);
//				 System.out.print("Id: " + champion.get("id") + "\t");
//				 System.out.print("Ranked: " + champion.get("rankedPlayEnabled") + "\t");
//				 System.out.print("Bot: " + champion.get("botEnabled") + "\t");
//				 System.out.print("BotMm: " + champion.get("botMmEnabled") + "\t");
//				 System.out.print("Active: " + champion.get("active") + "\t");
//				 System.out.println("Free: " + champion.get("freeToPlay"));
//				 
//				 System.out.println(champions.get(i));
//			 }
//		 }
////		 for (int i = 0; i< champions.length(); i++) {
////			 System.out.println(champions.get(i));
////		 }
//		 
////		 Iterator<String> it = ah.keys();
////		 while (it.hasNext()) {
////			 System.out.println(it.next());
////		 }
//      } catch (MalformedURLException e) {
//	     e.printStackTrace();
//      } catch (IOException e) {
//	     e.printStackTrace();
//      }
// 
//   }
 
//   private void print_https_cert(HttpsURLConnection con){
// 
//    if(con!=null){
// 
//      try {
// 
//	System.out.println("Response Code : " + con.getResponseCode());
//	System.out.println("Cipher Suite : " + con.getCipherSuite());
//	System.out.println("\n");
// 
//	Certificate[] certs = con.getServerCertificates();
//	for(Certificate cert : certs){
//	   System.out.println("Cert Type : " + cert.getType());
//	   System.out.println("Cert Hash Code : " + cert.hashCode());
//	   System.out.println("Cert Public Key Algorithm : " 
//                                    + cert.getPublicKey().getAlgorithm());
//	   System.out.println("Cert Public Key Format : " 
//                                    + cert.getPublicKey().getFormat());
//	   System.out.println("\n");
//	}
// 
//	} catch (SSLPeerUnverifiedException e) {
//		e.printStackTrace();
//	} catch (IOException e){
//		e.printStackTrace();
//	}
// 
//     }
// 
//   }
 
//   private void print_content(HttpsURLConnection con){
//	if(con!=null){
// 
//	try {
// 
//	   System.out.println("****** Content of the URL ********");			
//	   BufferedReader br = 
//		new BufferedReader(
//			new InputStreamReader(con.getInputStream()));
// 
//	   String input;
// 
//	   while ((input = br.readLine()) != null){
//	      System.out.println(input);
//	   }
//	   br.close();
// 
//	} catch (IOException e) {
//	   e.printStackTrace();
//	}
// 
//       }
// 
//   }

}