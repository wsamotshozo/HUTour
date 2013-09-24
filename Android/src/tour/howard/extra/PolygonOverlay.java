package tour.howard.extra;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class PolygonOverlay extends Overlay {
    private GeoPoint[] gp;
    private int color;
 
    public PolygonOverlay(GeoPoint [] gp, int color) {
        this.gp = gp;
        this.color = color;
    }
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
	    Projection projection = mapView.getProjection();
	    
	    Paint paint = new Paint();
	    paint.setColor(color);
	    paint.setStrokeWidth(10);
	    paint.setAlpha(120);
	    /*ArrayList<Point> pts = new ArrayList<Point>(); 

	    for(int i = 0; i < gp.length; i++){
		    Point point = new Point();
		    projection.toPixels(gp[0], point);
		    pts.add(point);
	    }*/
	    Point point = new Point();
    	Point point2 = new Point();
	    projection.toPixels(gp[0], point);
	    for(int i =1; i <gp.length-1; i++){
	    	projection.toPixels(gp[i], point2);
	    	canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
	    	point2 = point;
	    }
	    projection.toPixels(gp[0], point);
	    canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);
	    super.draw(canvas, mapView, shadow);
	}

}
