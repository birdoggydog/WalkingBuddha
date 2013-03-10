package nwpi.buddha;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WalkingBuddhaActivity extends Activity {
    final int GREETING = 0;
    final int PRAYER_SELECTION = 1;
    final int STUPA_SELECTION = 2;
    final int PRAY= 3;
    View curView;
    ViewGroup viewRoot;
    ViewGroup sliderRoot;
    RelativeLayout slidingDrawer;
    int visStup = 0;
    int visPray = 0;
    final int numStups = 1;
    ImageView stupaView;
    TextView prayerCounter;
    TextView stupaLabel;
    int numPrayers = 0;
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
    		stupaView =  null;
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
    		curView = LayoutInflater.from(this).inflate(R.layout.select_stupa_page, null);
    		stupaView = (ImageView)curView.findViewById(R.id.select_stupa_top_cur_stupa_holder);
    		stupaLabel = (TextView)curView.findViewById(R.id.prayStupaTitle);
    		setStupaView();
    		viewRoot.addView(curView);
    		 break;
    	case PRAY:
    		curView = LayoutInflater.from(this).inflate(R.layout.pray_page , null);
    		stupaView = (ImageView)curView.findViewById(R.id.select_stupa_top_cur_stupa_holder);
    		prayerCounter = (TextView)curView.findViewById(R.id.prayerCounter);
    		stupaLabel = (TextView)curView.findViewById(R.id.prayStupaTitle);
    		setStupaView();
    		viewRoot.addView(curView);
    		break;
    	
    	}
    	viewRoot.addView(sliderRoot);
    }
    public void setStupaView() {
    	
    }
    public void prayers(View v) {
    	setScreen(PRAYER_SELECTION);
    }
    public void stupas(View v) {
    	setScreen(STUPA_SELECTION);
    }
    public void pray(View v) {
    	setScreen(PRAY);
    }
    public void prevStupa(View v) {
    	if(visStup==0) {
    		visStup = numStups;
    	} else {
    		visStup=(visStup-1)%numStups;
    	}
        setStupaView();
    }
    public void nextStupa(View v) {
    	visStup = (visStup+1)%numStups;
        setStupaView();
    }
    public void plusClicked(View v) {
    	numPrayers+=1;
    	setPrayerCounter();
    }

    public void minusClicked(View v) {
    	if(numPrayers>0) {
    		numPrayers-=1;
        	setPrayerCounter();
    	}
    }
    public void setPrayerCounter() {
    	if(prayerCounter!=null) {
    		prayerCounter.setText("Prayers: "+numPrayers);
    	}
    }
}