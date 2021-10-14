
package com.example.demo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class APIRestController {
	@RequestMapping("/api")
	public String greeting() {

		// readFile("http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b");
		// readFile("http://www.mocky.io/v2/5ecfd630320000f1aee3d64d");
		// readFile("http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e");

		return "Hello World! from my Spring Boot REST API";
	}

	@GetMapping(value = "/getall")
	public String getAllHeroes() {
		RestTemplate restTemplate = new RestTemplate();
		String url1 = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b";
		String url2 = "http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e";
		String url3 = "http://www.mocky.io/v2/5ecfd630320000f1aee3d64d";
		String aven = restTemplate.getForObject(url1, String.class);
		String mutan = restTemplate.getForObject(url2, String.class);
		String anti = restTemplate.getForObject(url3, String.class);
		return aven + mutan + anti;

	}

	@GetMapping(value = "/avengers")
	public String getAvengers() {
		String url = "http://www.mocky.io/v2/5ecfd5dc3200006200e3d64b";
		RestTemplate restTemplate = new RestTemplate();
		String aven = restTemplate.getForObject(url, String.class);
		return aven;

	}

	@GetMapping(value = "/mutants")
	public String getMutants() {
		String url = "http://www.mocky.io/v2/5ecfd6473200009dc1e3d64e";
		RestTemplate restTemplate = new RestTemplate();
		String mutan = restTemplate.getForObject(url, String.class);
		return mutan;

	}

	@GetMapping(value = "/antiheroes")
	public String getAntiHeroes() {
		String url = "http://www.mocky.io/v2/5ecfd630320000f1aee3d64d";
		RestTemplate restTemplate = new RestTemplate();
		String anti = restTemplate.getForObject(url, String.class);
		return anti;

	}

	@GetMapping(value = "/getpower")
	public void getPower() {
		try {
			URL url = new URL("http://localhost:8080/avengers");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());
				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}
				scanner.close();
				JSONParser parse = new JSONParser();
				JSONObject data_obj = (JSONObject) parse.parse(inline);
				JSONArray arr = (JSONArray) data_obj.get("character");

				for (int i = 0; i < arr.size(); i++) {

					JSONObject new_obj = (JSONObject) arr.get(i);

					if (new_obj.get("name").equals("Black Panther")) {
						System.out.println("max_power: " + new_obj.get("max_power"));
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * public void readFile(String url) { RestTemplate restTemplate = new
	 * RestTemplate(); ResponseEntity<String> response =
	 * restTemplate.getForEntity(url, String.class); String jsonString = (String)
	 * response.getBody(); JSONObject jsonObject = new JSONObject();
	 * System.out.println(); JSONArray jsonArray =
	 * jsonObject.getJSONArray("character"); for (int i = 0; i < ((CharSequence)
	 * jsonArray).length(); i++) { JSONObject obj = jsonArray.getJSONObject();
	 * String name = obj.getString("name"); int max_power = obj.getInt("max_power");
	 * Character character = new Character(name, max_power);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * public static void readCharacter(String file) { JSONParser parser = new
	 * JSONParser(); try { FileReader fileReader = new FileReader(file); JSONObject
	 * json = (JSONObject) parser.parse(fileReader); String name = (String)
	 * json.get("name"); String max_power = (String) json.get("max_power");
	 * System.out.println("name: " + name); System.out.println("max_power: " +
	 * max_power); JSONArray characters = (JSONArray) json.get("characters");
	 * Iterator i = characters.iterator(); System.out.println("characters: "); while
	 * (i.hasNext()) { System.out.println(" " + i.next()); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * public void getCharacters(String url) { RestTemplate restTemplate = new
	 * RestTemplate(); ResponseEntity<String> response =
	 * restTemplate.getForEntity(url, String.class); String jsonString = (String)
	 * response.getBody(); JSONObject jsonObject = new JSONObject(jsonString);
	 * System.out.println(); JSONArray jsonArray =
	 * jsonObject.getJSONArray("character");
	 * 
	 * }
	 */

}
