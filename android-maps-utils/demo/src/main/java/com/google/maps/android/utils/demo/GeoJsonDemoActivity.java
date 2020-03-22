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

package com.google.maps.android.utils.demo;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.data.Feature;
import com.google.maps.android.data.geojson.GeoJsonFeature;
import com.google.maps.android.data.geojson.GeoJsonLayer;
import com.google.maps.android.data.geojson.GeoJsonPointStyle;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class GeoJsonDemoActivity extends BaseDemoActivity {
	
	private static final String TAG = GeoJsonDemoActivity.class.getSimpleName();
	
	// The entry point to the Fused Location Provider.
	private FusedLocationProviderClient mFusedLocationProviderClient;
	
	// A default location (NTU) and default zoom to use when location permission is
	// not granted.
	private final LatLng mDefaultLocation = new LatLng(1.350670, 103.685921);
	private static final int DEFAULT_ZOOM = 15;
	private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
	private boolean mLocationPermissionGranted;
	
	// The geographical location where the device is currently located. That is, the last-known
	// location retrieved by the Fused Location Provider.
	private Location mLastKnownLocation;
	
	
	
	private final static String mLogTag = "GeoJsonDemo";
	
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
	
	protected int getLayoutId() {
		return R.layout.geojson_demo;
	}
	
	@Override
	protected void startDemo(boolean isRestore) {
		if (!isRestore) {
			// Construct a FusedLocationProviderClient.
			mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
			
			getLocationPermission();
			updateLocationUI();
			getDeviceLocation();
//			getMap().moveCamera(CameraUpdateFactory.newLatLng(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude())));
		}
		// Download the GeoJSON file.
		retrieveFileFromUrl();
		// Alternate approach of loading a local GeoJSON file.
		//retrieveFileFromResource();
	}
	
	private void getDeviceLocation() {
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
							// Set the map's camera position to the current location of the device.
							mLastKnownLocation = (Location) task.getResult();
							getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(
									new LatLng(mLastKnownLocation.getLatitude(),
											mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
						} else {
							Log.d(TAG, "Current location is null. Using defaults.");
							Log.e(TAG, "Exception: %s", task.getException());
							getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
							getMap().getUiSettings().setMyLocationButtonEnabled(false);
						}
					}
				});
			}
		} catch(SecurityException e)  {
			Log.e("Exception: %s", e.getMessage());
		}
	}
	
	private void updateLocationUI() {
		if (getMap() == null) {
			return;
		}
		try {
			if (mLocationPermissionGranted) {
				getMap().setMyLocationEnabled(true);
				getMap().getUiSettings().setMyLocationButtonEnabled(true);
			} else {
				getMap().setMyLocationEnabled(false);
				getMap().getUiSettings().setMyLocationButtonEnabled(false);
				mLastKnownLocation = null;
				getLocationPermission();
			}
		} catch (SecurityException e)  {
			Log.e("Exception: %s", e.getMessage());
		}
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
	
	private void retrieveFileFromUrl() {
		new DownloadGeoJsonFile().execute(getString(R.string.geojson_url));
	}
	
	private void retrieveFileFromResource() {
		try {
			GeoJsonLayer layer = new GeoJsonLayer(getMap(), R.raw.earthquakes_with_usa, this);
			addGeoJsonLayerToMap(layer);
		} catch (IOException e) {
			Log.e(mLogTag, "GeoJSON file could not be read");
		} catch (JSONException e) {
			Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
		}
	}
	
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
	
	private class DownloadGeoJsonFile extends AsyncTask<String, Void, GeoJsonLayer> {
		
		@Override
		protected GeoJsonLayer doInBackground(String... params) {
			try {
				// Open a stream from the URL
				InputStream stream = new URL(params[0]).openStream();
//                InputStream stream = new FileInputStream(params[0]);
				
				String line;
				StringBuilder result = new StringBuilder();
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				
				while ((line = reader.readLine()) != null) {
					// Read and save each line of the stream
					result.append(line);
				}
				
				// Close the stream
				reader.close();
				stream.close();
				
				return new GeoJsonLayer(getMap(), new JSONObject(result.toString()));
			} catch (IOException e) {
				Log.e(mLogTag, "GeoJSON file could not be read");
			} catch (JSONException e) {
				Log.e(mLogTag, "GeoJSON file could not be converted to a JSONObject");
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(GeoJsonLayer layer) {
			if (layer != null) {
				addGeoJsonLayerToMap(layer);
			}
		}
	}
	
	private void addGeoJsonLayerToMap(GeoJsonLayer layer) {
		
		addColorsToMarkers(layer);
		layer.addLayerToMap();
		// Demonstrate receiving features via GeoJsonLayer clicks.
		layer.setOnFeatureClickListener(new GeoJsonLayer.GeoJsonOnFeatureClickListener() {
			@Override
			public void onFeatureClick(Feature feature) {
				Toast.makeText(GeoJsonDemoActivity.this,
						"Feature clicked: " + feature.getProperty("title"),
						Toast.LENGTH_SHORT).show();
			}
			
		});
	}
}
