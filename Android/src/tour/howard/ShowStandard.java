package tour.howard;

import tour.howard.extra.Note;

import com.google.android.maps.*;
import com.google.android.*;
import android.widget.Gallery;
import android.app.ListActivity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.ViewSwitcher;
import android.app.Activity;
import android.widget.BaseAdapter;
import android.content.Context;
import android.content.res.TypedArray;

public class ShowStandard extends MapActivity implements TextToSpeech.OnInitListener, OnClickListener{

	private Integer[] pics = {R.drawable.std1,R.drawable.std2,R.drawable.std3};
	private Note[] information = {new Note(" Mordecai Wyatt Johnson was Howard�s first Black President. Under his administration, every school and college " +
			"was reorganized. When he became president in1926, the University was comprised of eight schools, none of which held national accreditation." +
			" When Johnson retired 34 years later, there were 10 schools and colleges all fully accredited. The Administration building houses many of " +
			"the University�s key administrative offices, as well as the Office of the President. There is also a US Post Office on the Ground floor."),
			new Note(
			"Located on the main campus, the School of Business is the newest academic building on campus. The school has 80 faculty members and more " +
			"than 1500 students. Its facilities	include a highly rated library, computer facilities, and special centers for accounting education, " +
			"insurance,banking, and small business development."),
			new Note(
			"George W. Cook was born a slave, but came to Howard and earned a Bachelor of Arts degree in 1881. A very dedicated alumnus; he was a " +
			"tutor, professor, dean, business manager, secretary, treasurer, and acting president. The building was constructed in 1938, renovated on " +
			"the interior in 1992, and totally refurbished in 1997. This is a coed dormitory that primarily houses student athletes, and requires that" +
			"each person have a meal plan. All rooms contain telephone lines with basic telephone service included in the room rate and air " +
			"conditioning. For leisure,	there is a ResNet computer lab with extensive software and Internet access, as well as an exercise and " +
			"weight room.")
						
	};
	private ImageView imageView;
	private TextToSpeech talk;
	private GeoPoint [] waypoint = {new GeoPoint(38923111,-77021292), new GeoPoint(38924447,-77021474),new GeoPoint(38924964,-77021549)};
	private ViewSwitcher control;
	private Button flow;
	private int j=0;
	private MapView mapView;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tour);
		
		Gallery show = (Gallery)findViewById(R.id.Gallery);
		show.setAdapter(new ImageAdapter(this));
		
		talk = new TextToSpeech(this, this);
		talk.speak("turn left", TextToSpeech.QUEUE_FLUSH, null);
		
		control = (ViewSwitcher)findViewById(R.id.Control);
		flow = (Button)findViewById(R.id.show);
		
		//imageView = (ImageView)findViewById(R.id.Image);
		show.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Toast.makeText(getBaseContext(), 
						"You are on part " + (arg2+1) + " of the Howard Tour", 
						Toast.LENGTH_SHORT).show();
				imageView.setImageResource(pics[arg2]);
				talk.speak(information[arg2].getMessage(), TextToSpeech.QUEUE_FLUSH, null);
			}
		});

	}
    public class ImageAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
    	public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}
    	public int getCount() {
    		
    		return pics.length;
    	}
    	public Object getItem(int arg0) {
    		
    		return arg0;
    	}
    	public long getItemId(int arg0) {
    		
    		return arg0;
    	}
    	public View getView(int arg0, View arg1, ViewGroup arg2) {
    		ImageView iv = new ImageView(ctx);
    		iv.setImageResource(pics[arg0]);
    		iv.setScaleType(ImageView.ScaleType.FIT_XY);
    		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
    		iv.setBackgroundResource(imageBackground);
    		return iv;
    	}

    }
	
	// when app exits, shutdown TTS
	
	public void onDestroy() {
		super.onDestroy();
		if (talk != null) {
			talk.stop();
			talk.shutdown();
		}
	}
	
	// update the adapter
	private void updateAdapter() {
		//setListAdapter(new ArrayAdapter<Note>(this, 
			//	android.R.layout.simple_list_item_1, noteDAO.getAll()));
	}
	
	// called on TTS initialization 
	public void onInit(int status) {}

	
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
			control.showNext();
	}
}