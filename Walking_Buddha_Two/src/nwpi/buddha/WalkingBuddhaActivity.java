package nwpi.buddha;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class WalkingBuddhaActivity extends Activity {
    final int GREETING = 0;
    final int PRAYER_SELECTION = 1;
    final int STUPA_SELECTION = 2;
    final int p3 = 3;
    View curView;
    ViewGroup viewRoot;
    ViewGroup sliderRoot;
    RelativeLayout slidingDrawer;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewRoot = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.main,null);
        sliderRoot = (ViewGroup) viewRoot.findViewById(R.id.sliderView);
        slidingDrawer = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.m_sliding_drawer,null);
        sliderRoot.addView(slidingDrawer);
        setContentView(viewRoot);
        setScreen(GREETING);
    }
    public void setScreen(int screen) {
    	if(curView!=null || slidingDrawer!= null) {
    		viewRoot.removeView(curView);
    		viewRoot.removeView(sliderRoot);
    	}
    	switch(screen) {
    	case GREETING:
    		curView = LayoutInflater.from(this).inflate(R.layout.greeting_page, null);
    		viewRoot.addView(curView);
    		break;
    	case PRAYER_SELECTION:
    		curView = LayoutInflater.from(this).inflate(R.layout.select_prayer_page, null);
    		viewRoot.addView(curView);
    		break;
    	case STUPA_SELECTION:
    		 break;
    	}
    	viewRoot.addView(sliderRoot);
    }
    public void prayers(View v) {
    	setScreen(PRAYER_SELECTION);
    }
   
}