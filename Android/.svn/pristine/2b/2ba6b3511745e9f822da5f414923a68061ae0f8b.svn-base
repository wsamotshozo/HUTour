package tour.howard.extra;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ViewFlipper;
 
public class ViewChanger extends ViewFlipper {
    public ViewChanger(Context context) 
    {
        super(context);
    }
 
    public ViewChanger(Context context, AttributeSet attrs) 
    {
        super(context, attrs);
    }
 
    
    protected void onDetachedFromWindow() 
    {
        if (Integer.parseInt(Build.VERSION.SDK) == 14) 
        {                          
            try 
            {                                  
                super.onDetachedFromWindow();                          
            } 
             catch (IllegalArgumentException e) 
            {                                  
                Log.e("MyViewFlipper", "Android issue 6191: http://code.google.com/p/android/issues/detail?id=6191");                          
            } 
            finally 
            {                                  
                super.stopFlipping();                          
            }                  
        }  
        else 
        {                          
            super.onDetachedFromWindow();                  
        }  
    }
}
