package tour.howard.extra;

import tour.howard.MyOverlays;
import tour.howard.R;
import tour.howard.R.drawable;
import tour.howard.R.id;
import tour.howard.R.layout;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class ShowMapActivity extends MapActivity /*implements LocationListener*/ {

	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private String provider;
	private MyOverlays itemizedoverlay,landmarks;
	private int maxLong= (int)(-77.0208 * 1E6);
	private int minLong= (int)(-77.0207 * 1E6);
	private int maxLat= (int)(38.9195 * 1E6);
	private int minLat= (int)(38.9205 * 1E6);
	private GeoPoint waypost [] = {new GeoPoint((int)(38.917256*1E6),(int)(-77.020589*1E6)),new GeoPoint((int)(38.920566*1E6),(int)(-77.023602*1E6))};

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.gps); // bind the layout to the activity

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		// Either satellite or 2d 
		mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapView.setStreetView(true);
		//mapView.setOnTouchListener(new Touch());
		mapController = mapView.getController();
		mapController.setZoom(18);
		//GeoPoint  p = new GeoPoint((int)(38.92*1E6),(int)(-77.0195*1E6));
		//mapController.animateTo(p);
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GeoUpdateHandler());
		Drawable drawable = this.getResources().getDrawable(R.drawable.point);
		Drawable landmark = this.getResources().getDrawable(R.drawable.jump);
		itemizedoverlay = new MyOverlays(drawable,this);
		landmarks = new MyOverlays(landmark,this);
		/*Runnable center = new Runnable(){

			public void run() {
				mapController.animateTo();
			}
			
		};
		new Thread(center).start();*/
	}

	
	protected boolean isRouteDisplayed() {
		return false;
	}

	public class GeoUpdateHandler implements LocationListener {

		public void onLocationChanged(Location location) {
			int lat = (int) (location.getLatitude() * 1E6);
			int lng = (int) (location.getLongitude() * 1E6);
			GeoPoint point = new GeoPoint(lat, lng);
			mapController.animateTo(point);
			createMarker();
			mapView.invalidate();

		}

		public void onProviderDisabled(String provider) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	private void createMarker() {
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapView.getOverlays().add(itemizedoverlay);
		//mapView.invalidate();
	}
	private void createLandmarks(){
		for(int i = 0; i < waypost.length; i ++){
			GeoPoint w = waypost[i];
			landmarks.addOverlay(new OverlayItem(w,"",""));
			mapView.getOverlays().add(landmarks);
			mapView.invalidate();
		}
		
	}
	/*private class DrawMarkers extends AsyncTask<String,Void,String>{
		protected String doInBackground(String... urls) {
			locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GeoUpdateHandler());
			Drawable drawable = getResources().getDrawable(R.drawable.point);
			Drawable landmark = getResources().getDrawable(R.drawable.jump);
			createMarker();
			return "waht";
		}

		
		protected void onPostExecute(String result) {
			//mapView.invalidate();
		}
	}*/
	/*public class Touch implements OnTouchListener{
		public boolean onTouch(View v, MotionEvent event){
	        //---when user lifts his finger---
	        if (event.getAction() == 1) {                
	            GeoPoint p = mapView.getProjection().fromPixels(
	                (int) event.getX(),
	                (int) event.getY());
	                Toast.makeText(getBaseContext(), 
	                    p.getLatitudeE6() / 1E6 + "," + 
	                    p.getLongitudeE6() /1E6 , 
	                    Toast.LENGTH_SHORT).show();
	                System.out.println(event.getX() +" , " +event.getY());
	        }         
			return false;
		}
	}*/
/*
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		GeoPoint p = new GeoPoint((int)arg0.getLatitude(),(int)arg0.getLongitude());
		mapController.animateTo(p);
		createMarker();
		mapView.invalidate();
	}

	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}*/
}
