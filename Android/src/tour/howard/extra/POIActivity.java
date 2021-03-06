package tour.howard.extra;

import tour.howard.GuideActivity;
import tour.howard.MyOverlays;
import tour.howard.R;
import tour.howard.ShowStandard;
import tour.howard.SquareOverlay;
import tour.howard.R.drawable;
import tour.howard.R.id;
import tour.howard.R.layout;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class POIActivity extends MapActivity {

	private MapController mapController;
	private MapView mapView;
	private LocationManager locationManager;
	private MyOverlays itemizedoverlay,landmarks,mark;
	private SquareOverlay accent;
	private int maxLong= (int)(-77.0208 * 1E6);
	private int minLong= (int)(-77.0207 * 1E6);
	private int maxLat= (int)(38.9195 * 1E6);
	private int minLat= (int)(38.9205 * 1E6);
	private GeoPoint sections [] = {new GeoPoint((int)(38.917256*1E6),(int)(-77.020589*1E6)),new GeoPoint((int)(38.920566*1E6),(int)(-77.023602*1E6))};
	enum sector {nor,sou,div,law};
	private Spinner section;
	private WindowManager wm;

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.campus); // bind the layout to the activity
		
		Button standard = (Button) findViewById(R.id.sta);
		standard.setOnClickListener(showDefault);
		
		Button custom = (Button)findViewById(R.id.cus);
		custom.setOnClickListener(showCustom);
		
		Button scenic = (Button)findViewById(R.id.sce);
		scenic.setOnClickListener(showScenic);
		
		Button express = (Button)findViewById(R.id.exp);
		express.setOnClickListener(showExpress);
				

		wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        int height = wm.getDefaultDisplay().getHeight();
        
		mapView = (MapView) findViewById(R.id.map);
		mapView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,height/2 + 100));
		mapView.setBuiltInZoomControls(false);
		// Either satellite or 2d 
		//mapView.setSatellite(false);
		mapView.setTraffic(false);
		mapView.setStreetView(true);
		mapView.setFocusable(false);
		mapView.setClickable(false);
		Drawable drawable = this.getResources().getDrawable(R.drawable.point);
		Drawable landmark = this.getResources().getDrawable(R.drawable.jump);
		itemizedoverlay = new MyOverlays(drawable,this);
		landmarks = new MyOverlays(landmark,this);
		mark = new MyOverlays(landmark,this);
		//CenterMap();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListView places = (ListView)findViewById(R.id.list);
		/*String [] values = getResources().getStringArray(R.array.SouthPlaces);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,android.R.id.text1,values);
		places.setAdapter(adapter);
		places.setOnItemClickListener(highlight);*/
		
		/*section = (Spinner)findViewById(R.id.section);
		values = getResources().getStringArray(R.array.Sections);
		ArrayAdapter<String> views = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,values);
		section.setAdapter(views);
		section.setOnItemSelectedListener(pickSection);*/
	}

	
	protected boolean isRouteDisplayed() {
		return false;
	}

	private void createMarker() {
		GeoPoint p = mapView.getMapCenter();
		OverlayItem overlayitem = new OverlayItem(p, "", "");
		itemizedoverlay.addOverlay(overlayitem);
		mapView.getOverlays().add(itemizedoverlay);
	}
	private void createLandmarks(){
		for(int i = 0; i < sections.length; i ++){
			GeoPoint w = sections[i];
			landmarks.addOverlay(new OverlayItem(w,"",""));
			mapView.getOverlays().add(landmarks);
			//mapView.getOverlays().add(new SquareOverlay(new GeoPoint(38918056,-77019795),new GeoPoint(389179223,-77020943)));
		}
		
	}
	/*private void CenterMap(){
		//Runnable center = new Runnable(){
			//public void run(){
				//mapView.setSatellite(true);
				mapController = mapView.getController();
				mapController.setZoom(18);
				GeoPoint p;
				ListView places = (ListView)findViewById(R.id.list);
				String [] values;
				ArrayAdapter<String> adapter;
				switch(section.getSelectedItemPosition()){
				case 0:p = new GeoPoint((int)(38.924932*1E6),(int)(-77.019372*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.MCNORTH);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				case 1:p = new GeoPoint((int)(38.918944*1E6),(int)(-77.019919*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.MCSOUTH);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				case 2:p = new GeoPoint((int)(38.922592*1E6),(int)(-77.020208*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.MCCENTER);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				case 3:p = new GeoPoint((int)(38.921148*1E6),(int)(-77.017730*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.MCEAST);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				case 4:p = new GeoPoint((int)(38.919094*1E6),(int)(-77.024307*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.MCWEST);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				case 5:p = new GeoPoint((int)(38.943840*1E6),(int)(-77.057995*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.SOLAW);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				case 6:p = new GeoPoint((int)(38.939843*1E6),(int)(-76.983184*1E6));
					mapController.animateTo(p);
					values = getResources().getStringArray(R.array.SODIV);
					adapter = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1,android.R.id.text1,values);
					places.setAdapter(adapter);
					places.setOnItemClickListener(highlight);
					break;
				}
				//createMarker();
				//createLandmarks();
		//	}
		//};
		//new Thread(center).start();
	}*/
	private class Center implements OnTouchListener{

		public boolean onTouch(View arg0, MotionEvent arg1) {
			if(arg1.getAction() == arg1.ACTION_DOWN){
				//CenterMap();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return false;
		}
		
	}
	private OnItemClickListener highlight = new OnItemClickListener(){

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			/*GeoPoint shape [] = {new GeoPoint(38918056,-77019795), new GeoPoint(38917923,-77020943), new GeoPoint(38916868,-77020836),new GeoPoint(38916995,-77019774)};
			mapView.getOverlays().add(new SquareOverlay(shape));
			mapView.invalidate();*/
			new BlinkMarker().execute(arg0.getItemAtPosition(arg2).toString());
			/*GeoPoint x;
			if(arg0.getItemAtPosition(arg2).toString().toString().equals("Drew Hall"))
			{
				x = new GeoPoint(38927191,-77020949);
			}
			else
				x = new GeoPoint(0,0);
			try {
				for(int i = 0; i < 3; i ++){
					mark.addOverlay(new OverlayItem(x,"",""));
					mapView = (MapView) findViewById(R.id.map);
					mapView.getOverlays().add(mark);
					mapView.invalidate();
					Thread.sleep(800);
					//mapView.getOverlays().clear();
					Thread.sleep(100);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			
		}
		
	};
	private class BlinkMarker extends AsyncTask<String, Void, GeoPoint> { 
	  // can use UI thread here
	  protected void onPreExecute() {
	      }
	 
	      // automatically done on worker thread (separate from UI thread)
	      protected GeoPoint doInBackground(final String... args) {
				GeoPoint x;
	    	  String y = args[0];
				if(y.toString().equals("Drew Hall"))
				{
					x = new GeoPoint(38927191,-77020949);
				}
				else
					x = new GeoPoint(0,0);
	         return x;
	      }
	 
	      // can use UI thread here
	  protected void onPostExecute(GeoPoint x) {
		  try {
				for(int i = 0; i < 3; i ++){
					mark.addOverlay(new OverlayItem(x,"",""));
					mapView = (MapView) findViewById(R.id.map);
					mapView.getOverlays().add(mark);
					mapView.invalidate();
					Thread.sleep(1000);
					mapView.getOverlays().clear();
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  	}
	}
	private OnClickListener showDefault = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(POIActivity.this,ShowStandard.class ));
		}
	};
	private OnClickListener showCustom = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(POIActivity.this,ShowMapActivity.class ));
		}
	};
	private OnClickListener showScenic = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(POIActivity.this,GuideActivity.class ));
		}
	};
	private OnClickListener showExpress = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(POIActivity.this,ShowMeActivity.class ));
		}
	};
	protected void onResume(){
		super.onResume();
		//CenterMap();
	}
	private OnItemSelectedListener pickSection= new OnItemSelectedListener(){

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			Toast.makeText(arg0.getContext(), "Changing the View to the  " + arg0.getItemAtPosition(arg2).toString(),Toast.LENGTH_SHORT).show();
			//CenterMap();

		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			//CenterMap();
		}
		
	};
	public void Blink(GeoPoint x){
			mark.addOverlay(new OverlayItem(x,"",""));
			mapView = (MapView) findViewById(R.id.map);
			mapView.getOverlays().add(mark);
			mapView.invalidate();
	}
}
