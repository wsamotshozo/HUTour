package tour.howard;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class LifeActivity extends Activity{
	private Handler handler = new Handler();
	private int show = 1;
	/** Called when the activity is first created. */
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.life);
        final ImageView pic = (ImageView)findViewById(R.id.pic);
        
        //make a slideshow of Howard pics
		Runnable slide = new Runnable(){
			public void run(){
				switch(show){
				case 1:pic.setImageResource(R.drawable.hu2); show = 2; break;
				case 2:pic.setImageResource(R.drawable.hu3); show = 3; break;
				case 3:pic.setImageResource(R.drawable.hu1); show = 1; break;
				}
				handler.postDelayed(this, 3000);
				pic.invalidate();
			}
		};
		new Thread(slide).start();
		
		Button sports = (Button)findViewById(R.id.spo);
		sports.setOnClickListener(showSports);
		
		Button orgs = (Button)findViewById(R.id.clu);
		orgs.setOnClickListener(showOrgs);
                
    }
	private OnClickListener showSports = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(LifeActivity.this, SportsActivity.class ));
		}
	};
	private OnClickListener showOrgs = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(LifeActivity.this, StuOrgActivity.class ));
		}
	};

}
/**
 * Need to populate organizations and sports club information
 */

