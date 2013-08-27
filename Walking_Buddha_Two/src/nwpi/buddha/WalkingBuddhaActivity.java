package nwpi.buddha;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/** Liscense:
 * 		Released undert The GPL 3.0 liscense, contents of which may be found at:
 * 		http://opensource.org/licenses/GPL-3.0
 * 
 * 		
 * @author Nathaniel Waggoner
 *
 *
 *TODO:
 *	We need a proper exception management system.  Right now exceptions are not propogating to any single location
 *  and they're scattered all throughout the code.  This means I'm not entirely sure atm where all exceptions terminate
 *  
 *  We need to upgrade from pics to video
 *  
 *  We need to add a GPS functionality
 *  
 *  We need the networking to work...
 *  
 *  We have:
 *  
 *  Dynamic listing of prayers/descriptions, and stupas/images associated based off of file names/contents;
 */
public class WalkingBuddhaActivity extends Activity {
	final int GREETING = 0;
	final int PRAYER_SELECTION = 1;
	final int STUPA_SELECTION = 2;
	final int PRAY= 3;
	ViewGroup viewRoot;    
	PrayerCounter prayerCounter;
	ScreenController screenCont;
	PrayerController prayerCont;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewRoot = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.main,null);
		setContentView(viewRoot);
		screenCont = new ScreenController(this, viewRoot);
		prayerCont = new PrayerController(this,screenCont);
		prayerCounter = new PrayerCounter(screenCont);
		screenCont.setScreen(GREETING);
	}

	public void prayers(View v) {screenCont.setScreen(PRAYER_SELECTION);}
	public void stupas(View v) {screenCont.setScreen(STUPA_SELECTION);}
	public void pray(View v) {screenCont.setScreen(PRAY);}
	public void about(View v) {}
	public void plusClicked(View v) {prayerCounter.plusClicked();}

	public void minusClicked(View v) {prayerCounter.minusClicked();}
}