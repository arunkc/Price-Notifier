/**
 *Author		: 	Arun Kumar Konduru Chandra
 *Purpose		:	Zappos Challenge
 *Date			:	02/20/2014
 *Description 	:	Application using Zappos API that lets a user pick their desired product(s) 
 *					and then notifies them when the price hit at least 20% off the original price.*/


package notifierPackage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//Class to perform REST operations.
@SuppressWarnings("serial")
public class RestOperations extends NotifierUI
{
	
	static public JSONArray results;
	
	//Method to get product information from Zappos Rest API.
	public static JSONArray GET() 
	{
		String urladdress;
		String urlext;
		String key = "&key=a73121520492f88dc3d33daf2103d7574f1a3166";
		try
		{
			urladdress = "http://api.zappos.com/Search?limit=100";
			urlext = UIOperations.productURL()+UIOperations.brandsURL()+UIOperations.sortURL()+UIOperations.genderURL();
			URL url = new URL(urladdress+urlext+key);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept","application/json");
			if (conn.getResponseCode() != 200) 
			{
				throw new RuntimeException("Failed : HTTP error code :"+ conn.getResponseCode());
			}
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String output;
			String out = ""; 
			while ((output = br.readLine()) != null) 
			{
				out = out + output;
			}
			JSONObject json;
			json = new JSONObject(out);
			results = (JSONArray) json.get("results");
			conn.disconnect();
		}
		catch (JSONException e) 
		{
			lblStatus.setText(e.toString());
		}
		catch (MalformedURLException e) 
		{	 
			lblStatus.setText(e.toString());
		}
		catch (IOException e) 
		{
			lblStatus.setText(e.toString());
		}
		return results;
	}

	//Method to get thumbnail image.
	public static BufferedImage getImage()
	{
		URL imageURL;
		BufferedImage img = null;
		try
		{
			imageURL = new URL(UIOperations.description.getString("thumbnailImageUrl").toString());
			img = ImageIO.read(imageURL);
		}
		catch (JSONException e) 
		{
			lblStatus.setText(e.toString());
		} catch (MalformedURLException e) {
			lblStatus.setText(e.toString());
		} catch (IOException e) {
			lblStatus.setText(e.toString());
		}
		return img;
	}
	
}