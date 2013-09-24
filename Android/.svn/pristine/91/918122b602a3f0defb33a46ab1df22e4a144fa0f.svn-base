package tour.howard;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;

public class MapOverlay extends com.google.android.maps.Overlay {
    GeoPoint p;
	
    public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) 
    {
        super.draw(canvas, mapView, shadow);                   

        //---translate the GeoPoint to screen pixels---
        Point screenPts = new Point();
        mapView.getProjection().toPixels(p, screenPts);

        //---add the marker---
        /*Bitmap bmp = BitmapFactory.decodeResource(
            getResources(), R.drawable.point);        
        canvas.drawBitmap(bmp, screenPts.x, screenPts.y-50, null);  */       
        return true;
    }
}
