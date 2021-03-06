package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtility {

	public static void sendJSON(HttpServletResponse response, net.sf.json.JSONObject json)
			throws IOException {
		response.setContentType("application/json");
		if(!json.containsKey("success")) json.put("success", true);
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}
	
	public static void sendJSON(HttpServletResponse response, JSONObject json)
			throws IOException {
		response.setContentType("application/json");
		boolean hasSuccess = false;
		try { 
			hasSuccess = json.get("success") != null;
		} catch (JSONException e) {
			hasSuccess = false;
		}
		if(!hasSuccess) json.put("success", true);
		
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
	}
	
	public static void sendError(HttpServletResponse response, String message) throws IOException {
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		json.put("error", message);
		json.put("success", false);
		sendJSON(response, json);
	}
	
	public static void sendOK(HttpServletResponse response, boolean ok) throws IOException {
		JSONObject json = new JSONObject();
		response.setContentType("application/json");
		json.put("success", ok);
		if(!ok) json.put("error", "Something went wrong! Try again later.");
		sendJSON(response, json);
		
	}
}
