package com.trabalho.acheimoveis.actionbar;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.utils.Constants;

public class ActionBarListClickListener implements OnClickListener {

	// private final TextChangeListener keyListener;
	private final Activity activity;
	// protected FragmentSearchResults fragSearchResults;
	// private final boolean firstSearch;
	// public static ActionBarListClickListener instance;
	private final Context context;
	private final GoogleMap map;

	public ActionBarListClickListener(Activity activity, Context context, GoogleMap map) {

		this.activity = activity;
		this.context = context;
		this.map = map;

	}

	public void clearshowKeyboard(int op) {

		if (activity != null) {
			InputMethodManager imm = (InputMethodManager) activity
					.getSystemService(activity.getApplicationContext().INPUT_METHOD_SERVICE);

			View currentfocus = activity.getWindow().getCurrentFocus();

			if (currentfocus != null) {
				if (op == 0)
					imm.hideSoftInputFromWindow(currentfocus.getWindowToken(),
							0);
				else
					imm.showSoftInput(currentfocus, 0);
			}

		}
	}

	public void searchImgButton() {
		AguilaActionBar.instanceActionBar.removeAllItemsActionIcon();
        AguilaActionBar.instanceActionBar.setHomeLogo(
            R.drawable.actionbar_up_icon, this,
            Constants.HOME_IMG_BUTTON);
        AguilaActionBar.instanceActionBar.addActionEdiText(R.dimen.width_actionbar_search,
            R.dimen.height_actionbar_search);
        AguilaActionBar.instanceActionBar.addActionIcon(
            R.drawable.pesquisar_x, true, View.VISIBLE,
            this, Constants.CLOSE_IMG_BUTTON);
		clearshowKeyboard(1);
		
		EditText editText = AguilaActionBar.instanceActionBar.getEditText();

        editText.clearFocus();
        
        editText.requestFocus();
        
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId,
                KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                    && AguilaActionBar.instanceActionBar
                        .getEditTextContent().length() != 0) {
                	String address = mountAddress(AguilaActionBar.instanceActionBar.getEditTextContent() + ", manaus");
                	new GeocoderTask().execute(address);
                	
                    clearshowKeyboard(0);
                    return true;
                }
                return true;
            }
        });

	}
	
	public String mountAddress(String address){
		String addr = address.replace(" ", "+");
		return addr;
	}
	
	public class GeocoderTask extends AsyncTask<String, Void, LatLng>{

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
        	    Log.d("test", "LatLng:" + location.getLong("lat") + location.getLong("lng"));
        	    
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
	
	public void closeImgButton() {
		AguilaActionBar.instanceActionBar.setEditText("");
	}

	@Override
	public void onClick(View v) {
		int viewTag = Integer.parseInt("" + v.getTag());

		switch (viewTag) {
		case Constants.SEARCH_IMG_BUTTON:
			searchImgButton();
			break;
		case Constants.CLOSE_IMG_BUTTON:
			closeImgButton();
			break;

		default:
			break;
		}

	}
}
