package com.willies952002.MCSkinGrabber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.willies952002.Willies952002Core.bukkit.UUIDFetcher;

public class Base64Decoder {

	public static String base = "https://sessionserver.mojang.com/session/minecraft/profile/";
	static String url;
	String uuid;
	String user;

	public Base64Decoder(String username) throws Exception {
		String uuid_buffer = UUIDFetcher.getUUIDOf(username).toString();
		System.out.println("UUID (WITH '-'): " + uuid_buffer);
		uuid = uuid_buffer.replaceAll("-", "");
		System.out.println("UUID (WITHOUT '-'): " + uuid);
		url = base + uuid;
	}

	public static String getTextureURL(String username) throws Exception {
		Base64Decoder b64d = new Base64Decoder(username);
		System.out.println("URL: " + b64d.getURL());
		return _decode(b64d.getURL());
	}
	
	public static String _decode(String url2) throws Exception {
		String json = decode(url);
		System.out.println("RETURNED: " + json);
		JSONObject props = new JSONObject(json);
		System.out.println("PROPERTIES: " + props.toString());
		JSONObject textures = new JSONObject(props.get("textures").toString());
		System.out.println("TEXTURES: " + textures.toString());
		JSONObject SKIN = new JSONObject(textures.get("SKIN").toString());
		System.out.println("SKIN: " + SKIN.toString());
		String url = SKIN.get("url").toString();
		System.out.println("SKIN URL: " + url);
		return url;
	}

	private String getURL() {
		return url;
	}

	public static String decode(String url) throws Exception {
		String b64 = getTextureB64(url);
		return new String(Base64.decodeBase64(b64));
	}

	private static String getTextureB64(String url) {
		JSONObject json = null;
		try {
			json = readJsonFromUrl(url);
			System.out.println("JSON: " + json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException ex) {
			if (ex.getMessage().contains("429")) {
				Alert429 alert = new Alert429();
				alert.setVisible(true);
				return null;
			}
		}
		String props = json.get("properties").toString();
		JSONArray jsonProps = new JSONArray(props);
		JSONObject textures = jsonProps.getJSONObject(0);
		return textures.get("value").toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException,
			JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is,
					Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

}