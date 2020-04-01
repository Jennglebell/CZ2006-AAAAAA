/*
 * Copyright 2020 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package UI;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import Model.Item;
import Control.ItemController;
import Model.ItemList;
import Control.ItemListController;
import com.example.sharingapp.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;


import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MapDisplay extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnInfoWindowCloseListener, GoogleMap.OnInfoWindowLongClickListener {
	private static final LatLng JurongPark = new LatLng(1.338250, 103.707793);
	private static final LatLng testMarkerPos = new LatLng(1.337904, 103.707866);
	private SupportMapFragment mapFragment;

	private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId){
		Drawable vectorDrawable= ContextCompat.getDrawable(context, vectorResId);
		vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
		Bitmap bitmap=Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas=new Canvas(bitmap);
		vectorDrawable.draw(canvas);
		return BitmapDescriptorFactory.fromBitmap(bitmap);
	}


	private GoogleMap mMap;
	private ItemList item_list = new ItemList();
	private ItemListController item_list_controller = new ItemListController(item_list);
	private Context context;
	// Checks if location permission granted
	private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
	private boolean mLocationPermissionGranted;
	
	// Default zoom of 15
	private static final int DEFAULT_ZOOM = 100;
	
	private final static String mLogTag = "MapDisplay";
	
	// The entry point to the Fused Location Provider.
	private FusedLocationProviderClient mFusedLocationProviderClient;
	
	/**
	 * Assigns a color based on the given magnitude
	 */
//    private static float magnitudeToColor(double magnitude) {
//        if (magnitude < 1.0) {
//            return BitmapDescriptorFactory.HUE_CYAN;
//        } else if (magnitude < 2.5) {
//            return BitmapDescriptorFactory.HUE_GREEN;
//        } else if (magnitude < 4.5) {
//            return BitmapDescriptorFactory.HUE_YELLOW;
//        } else {
//            return BitmapDescriptorFactory.HUE_RED;
//        }
//    }
	

	private static final String TAG = MapDisplay.class.getSimpleName();
	
	// A default location (NTU) and default zoom to use when location permission is
	// not granted.
	private final LatLng mDefaultLocation = new LatLng(1.350670, 103.685921);
	
	// The geographical location where the device is currently located. That is, the last-known
	// location retrieved by the Fused Location Provider.
	private Location mLastKnownLocation;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setUpMap();
		context = getApplicationContext();
		item_list_controller.loadItems(context);

    }

//    public List<com.example.sharingapp.Location> getLocations(){
//
//	}

	@RequiresApi(api = Build.VERSION_CODES.N)
	@Override
	public void onMapReady(GoogleMap map) {
//		if (mMap != null) {
//			return;
//		}
		mMap = map;
		System.out.println("Hello");
		mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

		getLocationPermission();
		updateLocationUI();
		getDeviceLocation(mLocationPermissionGranted, mFusedLocationProviderClient);


		// Download the GeoJSON file.
		//	retrieveFileFromUrl();
		// Alternate approach of loading a local GeoJSON file.
		retrieveFileFromResource();

		Marker mJurongPark = mMap.addMarker(new MarkerOptions()
				.position(JurongPark)
				.title("Jurong Central Park")
				);
		Marker testMarker = mMap.addMarker(new MarkerOptions()
				.position(testMarkerPos)
				.title("marker test")
				.snippet("Population: 4,137,400"));
		mMap.moveCamera(CameraUpdateFactory.newLatLng(JurongPark));
		mMap.addCircle(
				new CircleOptions()
						.center(JurongPark)
						.radius(100.0)
						.strokeWidth(1f)
						.strokeColor(Color.rgb(51, 153, 255))
						.fillColor(Color.argb(70, 102, 178, 255))
		);
		mMap.setOnInfoWindowClickListener(this);
		mMap.setOnInfoWindowCloseListener(this);
		mMap.setOnInfoWindowLongClickListener(this);
//		mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(this));
//		mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//			@Override
//			public void onInfoWindowClick(Marker marker) {
////				System.out.println("sadasdasdasd");
////				Intent intent = new Intent(MapDisplay.this, EditItemActivity.class);
////				startActivity(intent);
//
//				if(marker.isInfoWindowShown()){
//					marker.hideInfoWindow();
//				} else {
//					marker.showInfoWindow();
//				}
//			}
//		});
//		mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
//			@Override
//			public void onInfoWindowClick(Marker marker) {
//				MarkerManager.Collection collection = mAllObjects.get(marker);
//				if (collection != null && collection.mInfoWindowClickListener != null) {
//					collection.mInfoWindowClickListener.onInfoWindowClick(marker);
//				}
//			}
//		});
	}

//	@Override
//	public boolean onMarkerClick(Marker marker) {
//		return true;
//	}

    private void setUpMap(){
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mMap)).getMapAsync(this);
    }
	
	private void getLocationPermission() {
		/*
		 * Request location permission, so that we can get the location of the
		 * device. The result of the permission request is handled by a callback,
		 * onRequestPermissionsResult.
		 */
		if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
				android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			mLocationPermissionGranted = true;
		} else {
			ActivityCompat.requestPermissions(this,
					new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
					PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       @NonNull String[] permissions,
	                                       @NonNull int[] grantResults) {
		mLocationPermissionGranted = false;
		switch (requestCode) {
			case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0
						&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					mLocationPermissionGranted = true;
				}
			}
		}
		updateLocationUI();
	}
	
	private void updateLocationUI() {
		if (mMap == null) {
			return;
		}
		try {
			if (mLocationPermissionGranted) {
				mMap.setMyLocationEnabled(true);
				mMap.getUiSettings().setMyLocationButtonEnabled(true);
			} else {
				mMap.setMyLocationEnabled(false);
				mMap.getUiSettings().setMyLocationButtonEnabled(false);
				getLocationPermission();
			}
		} catch (SecurityException e)  {
			Log.e("Exception: %s", e.getMessage());
		}
	}
	
	private void getDeviceLocation(boolean mLocationPermissionGranted, FusedLocationProviderClient mFusedLocationProviderClient) {
		/*
		 * Get the best and most recent location of the device, which may be null in rare
		 * cases when a location is not available.
		 */
		try {
			if (mLocationPermissionGranted) {
				Task locationResult = mFusedLocationProviderClient.getLastLocation();
				locationResult.addOnCompleteListener(this, new OnCompleteListener() {
					@Override
					public void onComplete(@NonNull Task task) {
						if (task.isSuccessful()) {
							mLastKnownLocation = (Location) task.getResult();
							if(mLastKnownLocation!=null)
							mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
						} else {
							Log.d(TAG, "Current location is null. Using defaults.");
							Log.e(TAG, "Exception: %s", task.getException());
							mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));;
						}
					}
				});
			}
		} catch(SecurityException e)  {
			Log.e("Exception: %s", e.getMessage());
		}
	}
	
//	private void retrieveFileFromUrl() {
//		new DownloadGeoJsonFile().execute(getString(R.string.geojson_url));
//	}
	
	@RequiresApi(api = Build.VERSION_CODES.N)
	private void retrieveFileFromResource() {
		try {
			GeoJsonLayer layer = new GeoJsonLayer(mMap, R.raw.heritagetrees, this);

			List<LatLng> locations = new ArrayList<>();
			List<String> names = new ArrayList<>();
			List<String> descriptions = new ArrayList<>();
			for (GeoJsonFeature feature: layer.getFeatures()){
				locations.add((LatLng) feature.getGeometry().getGeometryObject());
				names.add(feature.getProperty("Name"));
				descriptions.add(feature.getProperty("Description"));
			}
// For testing, set the number of OIs
			List<String> names_test = new ArrayList<>();
			for(int i = 0; i < 5; i++) {
				names_test.add(names.get(i));
			}
			    int i=0;
				for (String name : names_test) {
					Item item = new Item("HT", name, "description", (Bitmap) null, UUID.randomUUID().toString());
					ItemController item_controller = new ItemController(item);
					item_controller.setLocation((String.valueOf(locations.get(i).longitude)), String.valueOf(locations.get(i).latitude));
					boolean success = item_list_controller.addItem(item, context);
					i++;
					if (!success) {
						return;
					}
			}
			List<String> names_test2 = new ArrayList<>();
			for(int j = 5; j < 10; j++) {
				names_test2.add(names.get(j));
			}

			int j=0;
			for (String name : names_test2) {
				Item item = new Item("BBQ", name, "description", (Bitmap) null, UUID.randomUUID().toString());
				ItemController item_controller = new ItemController(item);
				item_controller.setLocation((String.valueOf(locations.get(j).longitude)), String.valueOf(locations.get(j).latitude));
				boolean success = item_list_controller.addItem(item, context);
				j++;
				if (!success) {
					return;
				}
			}


//			KmlLayer layer2 = new KmlLayer(mMap, R.raw.nparksbbq, this);
//			List<LatLng> locations_bbq = new ArrayList<>();
//			List<String> names_bbq = new ArrayList<>();
//			List<String> descriptions_bbq = new ArrayList<>();
//			for (KmlPlacemark placemarker: layer2.getPlacemarks()){
//				System.out.println("KML:" + placemarker.getMarkerOptions().getTitle());
//				locations_bbq.add((LatLng) feature.getGeometry().getGeometryObject());
//				names_bbq.add(feature.getProperty("Name"));
//				descriptions_bbq.add(feature.getProperty("Description"));
//			}





			addGeoJsonLayerToMap(layer);
		} catch (IOException e) {
			Log.e(mLogTag, "GeoJSON file could not be read");
		} catch (JSONException e) {
			Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
		}
	}

//	private void getLocations(GeoJsonLayer layer) {
////		for (GeoJsonFeature feature : layer.getFeatures()) {
////			List<String> coordinates = Collections.singletonList(feature.getProperty("geometry"));
////			System.out.println("Coordinate: " + coordinates.get(0));
////		}
//
//		for (GeoJsonFeature feature : layer.getFeatures()) {
//			System.out.println("Coordinate: " + feature.getGeometry().getGeometryObject());
//		}
//	}

		/**
         * Adds a point style to all features to change the color of the marker based on its magnitude
         * property
         */
	private void addColorsToMarkers(GeoJsonLayer layer) {
		// Iterate over all the features stored in the layer
		for (GeoJsonFeature feature : layer.getFeatures()) {
			// Check if the magnitude property exists
			if (feature.getProperty("mag") != null && feature.hasProperty("place")) {
				double magnitude = Double.parseDouble(feature.getProperty("mag"));
				
				// Get the icon for the feature
				BitmapDescriptor pointIcon = BitmapDescriptorFactory
//						.defaultMarker(magnitudeToColor(magnitude));
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE);
				// Create a new point style
				GeoJsonPointStyle pointStyle = new GeoJsonPointStyle();
				
				// Set options for the point style
				pointStyle.setIcon(pointIcon);
				pointStyle.setTitle("Magnitude of " + magnitude);
				pointStyle.setSnippet("Earthquake occured " + feature.getProperty("place"));
				
				// Assign the point style to the feature
				feature.setPointStyle(pointStyle);
			}
		}
	}
	
//	private class DownloadGeoJsonFile extends AsyncTask<String, Void, GeoJsonLayer> {
//
//		@Override
//		protected GeoJsonLayer doInBackground(String... params) {
//			try {
//				// Open a stream from the URL
//				InputStream stream = new URL(params[0]).openStream();
////                InputStream stream = new FileInputStream(params[0]);
//
//				String line;
//				StringBuilder result = new StringBuilder();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//				while ((line = reader.readLine()) != null) {
//					// Read and save each line of the stream
//					result.append(line);
//				}
//
//				// Close the stream
//				reader.close();
//				stream.close();
//
//				return new GeoJsonLayer(mMap, new JSONObject(result.toString()));
//			} catch (IOException e) {
//				Log.e(mLogTag, "GeoJSON file could not be read");
//			} catch (JSONException e) {
//				Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
//			}
//			return null;
//		}
		
//		@Override
//		protected void onPostExecute(GeoJsonLayer layer) {
//			if (layer != null) {
//				addGeoJsonLayerToMap(layer);
//			}
//		}
//	}
	
	private void addGeoJsonLayerToMap(GeoJsonLayer layer) {

		addColorsToMarkers(layer);
		layer.addLayerToMap();
		// Demonstrate receiving features via GeoJsonLayer clicks.
		layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
			@Override
			public void onFeatureClick(Feature feature) {
				Toast.makeText(MapDisplay.this,
						"Feature clicked: " + feature.getProperty("title"),
						Toast.LENGTH_SHORT).show();
			}
			
		});
	//	getLocations(layer);
	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		Toast.makeText(this, "Info window clicked",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onInfoWindowClose(Marker marker) {
		Toast.makeText(this, "Info window closed",
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onInfoWindowLongClick(Marker marker) {
		Toast.makeText(this, "Info window clicked",
				Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, EditItemActivity.class);
		startActivity(intent);
	}
}
