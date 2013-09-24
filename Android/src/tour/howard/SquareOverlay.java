package tour.howard;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
//draws a square based on
public class SquareOverlay extends Overlay{
GeoPoint gp1;
GeoPoint gp2;
GeoPoint corners [];

public SquareOverlay(GeoPoint gp1, GeoPoint gp2) {

    this.gp1 = gp1;
    this.gp2 = gp2;

}
public SquareOverlay(GeoPoint corner []){
	corners=corner;
}
public void draw(Canvas canvas, MapView mapView, boolean shadow) {

	Projection projection = mapView.getProjection();
	Paint mPaint = new Paint();
	mPaint.setStrokeWidth(9);
	mPaint.setAlpha(120);
	Point from = new Point();
	projection.toPixels(corners[0], from);
	mPaint.setColor(Color.BLUE);
	Point to = new Point();
	projection.toPixels(corners[corners.length-1],to);
	canvas.drawLine(from.x, from.y, to.x, to.y, mPaint);
	for(int i =1; i < corners.length; i++){
		projection.toPixels(corners[i], to);
		canvas.drawLine(from.x, from.y, to.x, to.y, mPaint);
		projection.toPixels(corners[i], from);
	}
	super.draw(canvas, mapView, shadow);
}

/*public void draw(Canvas canvas, MapView mapView, boolean shadow) {

    Projection projection = mapView.getProjection();
    Paint mPaint = new Paint();
    Point from = new Point();
    projection.toPixels(gp1, from);
    mPaint.setColor(Color.BLUE);

    Point to = new Point();
    projection.toPixels(gp2, to);
    mPaint.setStrokeWidth(9);
    mPaint.setAlpha(120);

    canvas.drawLine(from.x, from.y, to.x, to.y, mPaint);
    super.draw(canvas, mapView, shadow);

}*/

    /*public SquareOverlay(Projection proj){
    	projection =proj;
    }   

    public void draw(Canvas canvas, MapView mapv, boolean shadow){
        super.draw(canvas, mapv, shadow);

        Paint   mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setColor(Color.MAGENTA);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(2);

        GeoPoint gP1 = new GeoPoint((int)(38918056*1E6), (int)(-77.019795*1E6));
        GeoPoint gP2 = new GeoPoint((int)(38917923*1E6), (int)(-77.020944*1E6));

        Point p1 = new Point();
        Point p2 = new Point();
        Path path = new Path();

        projection.toPixels(gP1, p1);
        projection.toPixels(gP2, p2);

        path.moveTo(p2.x, p2.y);
        path.lineTo(p1.x,p1.y);

        canvas.drawPath(path, mPaint);
    }*/
}