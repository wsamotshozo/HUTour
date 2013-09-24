package tour.howard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyOverlays extends ItemizedOverlay<OverlayItem>{

	private static int maxNum = 100;
	private OverlayItem overlays[] = new OverlayItem[maxNum];
	private int index = 0;
	private boolean full = false;
	private Context context;
	//private MyOverlays itemizedoverlay;
	//private OverlayItem previousoverlay;

	public MyOverlays(Drawable defaultMarker, MapActivity r) {
		super(boundCenterBottom(defaultMarker));
		this.context = r.getBaseContext();
	}
	
	protected OverlayItem createItem(int i) {
		return overlays[i];
	}

	
	public int size() {
		if (full) {
			return overlays.length;
		} else {
			return index;
		}

	}
	public void clear(){
		index = 0;
		for(int i = 0; i < maxNum; i++)
		{
			overlays[index]= null;
		}
	}

	public void addOverlay(OverlayItem overlay) {
		//if(previousoverlay !=null){
			if (index < maxNum) {
				overlays[index] = overlay;
			} else {
				index = 0;
				full = true;
				overlays[index] = overlay;
			}
			index++;
			populate();
		//}
		//this.previousoverlay = overlay;
	}
    
    public boolean onTouchEvent(MotionEvent event, MapView mapView) 
    {   
        //---when user lifts his finger---
        if (event.getAction() == 1) {                
           /* GeoPoint p = mapView.getProjection().fromPixels(
                (int) event.getX(),
                (int) event.getY());
                Toast.makeText(context, p.getLatitudeE6() / 1E6 + "," + p.getLongitudeE6() /1E6 , Toast.LENGTH_SHORT).show();*/
        }                            
        return false;
    } 
}
			
