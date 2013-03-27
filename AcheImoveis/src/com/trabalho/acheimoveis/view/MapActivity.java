package com.trabalho.acheimoveis.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.org.sidia.aguilaactionbar.AguilaActionBar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.trabalho.acheimoveis.R;
import com.trabalho.acheimoveis.actionbar.ActionBarListClickListener;
import com.trabalho.acheimoveis.utils.Constants;

public class MapActivity extends FragmentActivity implements OnMarkerClickListener, OnInfoWindowClickListener, OnMarkerDragListener{
	
	private static final LatLng MANAUS = new LatLng(-3.1064093,-60.0264297);
	
    private GoogleMap mMap;
    private UiSettings mUiSettings;

    private Marker mManaus;
    private TextView mTopText;

	private ActionBarListClickListener clickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.map);
        changeActionBar(0);

        setUpMapIfNeeded();
    }
    
    public void changeActionBar(int op) {
        if (op == 0) {

            AguilaActionBar.instanceActionBar.setHomeLogo(
                R.drawable.ic_launcher, clickListener,
                Constants.HOME_IMG_BUTTON);
            //AguilaActionBar.instanceActionBar.removeAllItemsActionIcon();
            AguilaActionBar.instanceActionBar.addActionIcon(
                R.drawable.actionbar_pesquisar, true, View.VISIBLE,
                clickListener, Constants.SEARCH_IMG_BUTTON);
        }
    }
    
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    
    private void setUpMap() {
    	
        mMap.setMyLocationEnabled(true);
        mUiSettings = mMap.getUiSettings();
        setupUiSettings();

        // Add lots of markers to the map.
        addMarkersToMap();

        // Setting an info window adapter allows us to change the both the contents and look of the
        // info window.
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Set listeners for marker events.  See the bottom of this class for their behavior.
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerDragListener(this);
        
        CameraPosition cameraPosition = 
			    new CameraPosition.Builder()
			      .target(MANAUS)   
			      .zoom(12)     
			      .build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));    

    }
    
    private void setupUiSettings(){
    	mUiSettings.setZoomControlsEnabled(true);
    	mUiSettings.setCompassEnabled(true);
    	mUiSettings.setMyLocationButtonEnabled(true);
    	mMap.setMyLocationEnabled(true);
    	mUiSettings.setScrollGesturesEnabled(true);
    	mUiSettings.setZoomGesturesEnabled(true);
    	mUiSettings.setTiltGesturesEnabled(true);
    	mUiSettings.setRotateGesturesEnabled(true);
    }
    
    private void addMarkersToMap() {
        // Uses a colored icon.
    	mManaus = mMap.addMarker(new MarkerOptions()
                .position(MANAUS)
                .title("Manaus")
                .snippet("Population: 2,074,200")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_building)));
    }
	

	@Override
	public void onMarkerDrag(Marker marker) {
		mTopText.setText("onMarkerDrag.  Current Position: " + marker.getPosition());
		
	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		mTopText.setText("onMarkerDragEnd");
		
	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		mTopText.setText("onMarkerDragStart");
		
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(getBaseContext(), "Click Info Window", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}
	
    /** Demonstrates customizing the info window and/or its contents. */
    class CustomInfoWindowAdapter implements InfoWindowAdapter {

        // These a both viewgroups containing an ImageView with id "badge" and two TextViews with id
        // "title" and "snippet".
        private final View mWindow;

        CustomInfoWindowAdapter() {
            mWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            render(marker, mWindow);
            return mWindow;
        }

        private void render(Marker marker, View view) {
            int badge = 0;
            // Use the equals() method on a Marker to check for equals.  Do not use ==.
            if (marker.equals(mManaus)) {
                badge = R.drawable.badge_victoria;
            } 
            ((ImageView) view.findViewById(R.id.badge)).setImageResource(badge);

            String title = marker.getTitle();
            TextView titleUi = ((TextView) view.findViewById(R.id.title));
            if (title != null) {
                // Spannable string allows us to edit the formatting of the text.
                SpannableString titleText = new SpannableString(title);
                titleText.setSpan(new ForegroundColorSpan(Color.RED), 0, titleText.length(), 0);
                titleUi.setText(titleText);
            } else {
                titleUi.setText("");
            }

            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.snippet));
            if (snippet != null && snippet.length() > 12) {
                SpannableString snippetText = new SpannableString(snippet);
                snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, 10, 0);
                snippetText.setSpan(new ForegroundColorSpan(Color.BLUE), 12, snippet.length(), 0);
                snippetUi.setText(snippetText);
            } else {
                snippetUi.setText("");
            }
        }

		@Override
		public View getInfoContents(Marker marker) {
			// TODO Auto-generated method stub
			return null;
		}
    }

}
