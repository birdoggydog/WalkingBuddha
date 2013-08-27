package nwpi.buddha;

import java.io.IOException;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ScreenController {
	final int GREETING = 0;
	final int PRAY= 3;
	final int PRAYER_SELECTION = 1;
	final int STUPA_SELECTION = 2;
	private PrayerController pc;
	private StupaController sc;
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
	Context ctx;

	public ScreenController(Context ctx, ViewGroup vr) {
		this.ctx = ctx;
		viewRoot = vr;
		sliderRoot = (ViewGroup) viewRoot.findViewById(R.id.sliderView);
		slidingDrawer = (RelativeLayout) LayoutInflater.from(ctx).inflate(R.layout.m_sliding_drawer,null);
		sliderRoot.addView(slidingDrawer);
	}
	public void setScreen(int screen) {
		ArrayAdapter<String> spinAdapt=null;
		
		if(curView!=null || slidingDrawer!= null) {
			viewRoot.removeView(curView);
			viewRoot.removeView(sliderRoot);
			stupaView =  null;
		}
		switch(screen) {
		case GREETING:
			curView = LayoutInflater.from(ctx).inflate(R.layout.greeting_page, null);
			viewRoot.addView(curView);
			break;
		case PRAYER_SELECTION:
			curView = LayoutInflater.from(ctx).inflate(R.layout.select_prayer_page, null);
			viewRoot.addView(curView);
			try {
				spinAdapt = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, pc.getListOfPrayers());
			} catch (IOException e) {
				Toast.makeText(ctx, "Failed to load Stupa's from directory. Try reinstalling?", Toast.LENGTH_LONG).show();
				e.printStackTrace();
			} 
			final Spinner prayerSpinner = (Spinner) curView.findViewById(R.id.prayer_selection_spinner);
			pc.setCurrentPrayer(0);
			
			prayerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String imc_met=prayerSpinner.getSelectedItem().toString();
					pc.loadPrayer(imc_met);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			prayerSpinner.setAdapter(spinAdapt);
			break;
		case STUPA_SELECTION:
			curView = LayoutInflater.from(ctx).inflate(R.layout.select_stupa_page, null);
			stupaView = (ImageView)curView.findViewById(R.id.select_stupa_top_cur_stupa_holder);
			stupaLabel = (TextView)curView.findViewById(R.id.prayStupaTitle);
			spinAdapt = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, sc.getAllStupas());
			final Spinner stupaSpinner = (Spinner) curView.findViewById(R.id.stupa_selection_spinner);
			pc.setCurrentPrayer(0);
			
			stupaSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String imc_met=stupaSpinner.getSelectedItem().toString();
					try {
						sc.loadStupa(imc_met);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			stupaSpinner.setAdapter(spinAdapt);
			setStupaView();
			viewRoot.addView(curView);
			break;
		case PRAY:
			curView = LayoutInflater.from(ctx).inflate(R.layout.pray_page , null);
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
 
    public void setPrayerCounter(int num) {
    	if(prayerCounter!=null) {
    		prayerCounter.setText("Prayers: "+numPrayers);
    	}
    }
	public void setPrayerText(ArrayList<String> prayerText) {
	}
	public void setPrayerDesc(ArrayList<String> prayerDesc) {
		TextView prayerDescField = (TextView) curView.findViewById(R.id.prayer_description_text);
		String txt="";
		for(String v : prayerDesc) {
			txt = v+"\n";
		}
		prayerDescField.setText(txt);
	}
	
	public void setPrayerController(PrayerController prayerController) {
		this.pc = prayerController;
	}
	
	//called from the StupaController //
	public void setCurrentStupaFrame(Integer resourceId) {
		stupaView.setImageResource(resourceId);
	}
}
