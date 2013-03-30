package com.trabalho.acheimoveis.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class GeocoderTask extends AsyncTask<String, Void, LatLng>{
	
	private final GoogleMap map;
	
	public GeocoderTask(GoogleMap map){
		
		this.map = map;
	}

	@Override
	protected LatLng doInBackground(String... address) {
		JSONObject ret = getLocationInfo(address[0]); 
    	JSONObject results;
    	JSONObject geometry;
    	JSONObject location;
    	LatLng latlng = null;
    	try {
    		results = ret.getJSONArray("results").getJSONObject(0);
    		geometry = results.getJSONObject("geometry");
    		location = geometry.getJSONObject("location");
    		latlng = new LatLng(location.getLong("lat"), location.getLong("lng"));
    	   // Log.d("test", "LatLng:" + location.getLong("lat") + location.getLong("lng"));
    	    
    	} catch (JSONException e1) {
    	    e1.printStackTrace();

    	}                	
		return latlng;
	}
	
	@Override
	protected void onPostExecute(LatLng latlng) {
		if(latlng != null){
    	    CameraPosition cameraPosition = 
				    new CameraPosition.Builder()
				      .target(latlng)   
				      .zoom(15)     
				      .build();

				map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		}
	}
	
	public JSONObject getLocationInfo(String location) {		
        HttpGet httpGet = new HttpGet("http://maps.googleapis.com/maps/api/geocode/json?address="+location+"&sensor=true");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
            } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
	
}
