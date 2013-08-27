package nwpi.buddha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;

public class PrayerController {

	Context ctx;
	ArrayList<String> prayerText;
	ArrayList<String> prayerDesc;
	int curPrayer;
	ScreenController callback;
	String[] list_of_avail_prayers;
	
	public PrayerController(Context ctx, ScreenController callback) {
		this.ctx = ctx;
		this.callback = callback;
		this.callback.setPrayerController(this);
	}
	
	public String[] getListOfPrayers() throws IOException {
		InputUtils.listFiles(ctx,"prayer_text");
		return list_of_avail_prayers;
	}
	
	public void loadPrayer(String prayerName) {
		int index = -1;
		for(int i = 0; i< list_of_avail_prayers.length;i++) {
			if(prayerName.equals(list_of_avail_prayers[i])){
				index = i;
				setCurrentPrayer(index);
				return;
			}
		}
		Toast.makeText(ctx,"Failed to find the selected prayer",Toast.LENGTH_LONG).show();
	}
	public void setCurrentPrayer(int prayer) {
		curPrayer = prayer;
		try {
			loadPrayer();
		} catch (IOException e) {
			Toast.makeText(ctx, "Failed to Load Selected Prayer", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	private void loadPrayer() throws IOException {
		String filename = prepare(list_of_avail_prayers[curPrayer]);
		InputStream is = ctx.getAssets().open("prayer_text/"+filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			prayerText.add(line);
		}
		br.close();
		filename= prepare(list_of_avail_prayers[curPrayer]+" desc");
		is = ctx.getAssets().open("prayer_desc_text/"+filename);
		br = new BufferedReader(new InputStreamReader(is));
		line = null;
		while ((line = br.readLine()) != null) {
			prayerDesc.add(line);
		}
		callback.setPrayerText(prayerText);
		callback.setPrayerDesc(prayerDesc);
	}
	private String prepare(String filename) {
		String newName = filename.replaceAll(" ", "_");
		newName= newName.replaceAll("(","");
		newName= newName.replaceAll(")","");
		return newName.toLowerCase()+".txt";
	}

}
