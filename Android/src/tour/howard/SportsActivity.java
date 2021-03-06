package tour.howard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

//show a brief summary of the sports club and a link to their websites
//future: add in stats and history of success
public class SportsActivity extends Activity {
	private TextView summary;
	private ImageView pic;
	private Gallery gallery;
	private Integer[] pics = {R.drawable.std1,R.drawable.std2,R.drawable.std3};

	/** Called when the activity is first created. */
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sports); 
        
        //summary = (TextView)findViewById(R.id.info);
        //summary.setText(getResources().getString(R.string.Football));
        
        //pic = (ImageView)findViewById(R.id.image);
        //pic.setImageResource(R.drawable.foot1);
        gallery = (Gallery)findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        
    }
	/*private OnClickListener showPOI = new OnClickListener() {

		public void onClick(View v) {
			//startActivity(new Intent(Start.this,POIActivity.class ));
		}
	};
	private OnClickListener showLife = new OnClickListener() {

		public void onClick(View v) {
			//startActivity(new Intent(Start.this,LifeActivity.class ));
		}
	};*/
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

}
/* having trouble with onCreate. This is a temporary work around.
 * this sometimes works and sometimes does not. It might have to do
 * with zombies on my phone after I cancel the previous apps*/
