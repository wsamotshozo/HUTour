package tour.howard;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;
import android.widget.Toast;
import android.widget.MediaController;
import android.os.Bundle;
public class Start extends Activity {
   
	/** Called when the activity is first created. */
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome); 
        
       //Create Buttons
        ImageView left = (ImageView)findViewById(R.id.tour);
        left.setOnClickListener(showPOI);
        
        ImageView right = (ImageView)findViewById(R.id.life);
        right.setOnClickListener(showLife);
        
        Button pointsOInt = (Button)findViewById(R.id.detpoi);
        pointsOInt.setOnClickListener(showPOI);
        
        Button howardLife = (Button)findViewById(R.id.dethi);
        howardLife.setOnClickListener(showLife);
        
    }
    //go to point of interests screen
	private OnClickListener showPOI = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(Start.this,HUMap.class ));
		}
	};
	//go to the culture of howard screen
	private OnClickListener showLife = new OnClickListener() {

		public void onClick(View v) {
			startActivity(new Intent(Start.this,LifeActivity.class ));
		}
	};
}