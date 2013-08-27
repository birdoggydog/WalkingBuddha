package nwpi.buddha;

import java.io.File;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;

public class StupaController {
	int curStupa;
	String[] allStupas;
	public static final int GPS =0;
	public static final int TIME =1;
	public String stupa_name;
	private int stupa_index;
	private Integer[] stupa_files;
	ScreenController sc;
	Context ctx;
	private static int curMode = TIME;
	TimeRunnable runningThread;
	int stupaIndex = 0;
	
	public StupaController(Context ctx, ScreenController sc) throws IOException {
		this.sc = sc;
		this.ctx = ctx;
		allStupas = InputUtils.listFiles(ctx, "stupas");
	}
	public void setMode(int mode) {
		curMode = mode;
	}
	public void restartStupa() {
		switch(curMode){
		case(GPS):
			// don't have a gps listener thread set up yet...
			break;
		case(TIME):
			runningThread =new TimeRunnable();
			runningThread.start();
			break;
		}
	}
	public void setToStart() {
		stupaIndex = 0;
		sc.setCurrentStupaFrame(stupa_files[stupa_index]);
	}
	public void goToNextFrame() {
		stupaIndex++;
		if(stupaIndex>=stupa_files.length) {
			stupaIndex = 0;
		}
		sc.setCurrentStupaFrame(stupa_files[stupa_index]);
	}
	public Integer[] loadStupa(String new_name) throws IOException {
		stupa_name = new_name;
		stupa_name.replaceAll(" ", "_");
		stupa_name.toLowerCase();
		String[] stupa =InputUtils.loadTxtFileFromAssets(ctx, stupa_name+".txt","stupas");
		return InputUtils.loadResIdFromAssets(ctx, stupa);
	}
	
	private class TimeRunnable extends  Thread {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				goToNextFrame();
			}
			
		}
		
	}

	public String[] getAllStupas() {
		return allStupas;
	}

}
