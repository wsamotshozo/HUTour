package tour.howard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;


//Run the splash screen
public class Splash extends Activity /*implements ViewFactory*/{
	private SoundPool sounds;
	private int howl;
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.splash);
		//animate the bull for the splash screen
		ImageView img = (ImageView)findViewById(R.id.splashscreen);
		img.setBackgroundResource(R.drawable.bull);
		AnimationDrawable anime = (AnimationDrawable)img.getBackground();
		anime.start();
		//play sound for splash screen
		sounds = new SoundPool(10,AudioManager.STREAM_MUSIC,0);
		howl = sounds.load(getBaseContext(), R.raw.bison, 1);
		sounds.play(howl, 1.0f, 1.0f, 0 , 0, 1.5f);
		new CountDownTimer(2000, 1000) {
		     public void onTick(long millisUntilFinished) {
		    	 
		     }
		     public void onFinish() {
		    	 //lauch application
		    	 startActivity(new Intent(getBaseContext(), HUMap.class));
		     }
		}.start();
	}
}