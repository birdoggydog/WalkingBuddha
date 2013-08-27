package nwpi.buddha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;

public class InputUtils {

	/**Assumes file exists
	 * @throws IOException 
	 * 
	 */
	public static String[] loadTxtFileFromAssets(Context ctx, String filename,String assets_dir) throws IOException {
		ArrayList<String> ret = new ArrayList<String>();
		InputStream is = ctx.getAssets().open("assets_dir/"+filename);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String line = null;
		while ((line = br.readLine()) != null) {
			ret.add(line);
		}
		br.close();
		return ret.toArray(new String[ret.size()]);
	}
	public static Integer[] loadResIdFromAssets(Context ctx, String[] assets) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String asset: assets) {
			int resID = ctx.getResources().getIdentifier(asset, "drawable",  ctx.getPackageName());
			ret.add(resID);
		}
		return ret.toArray(new Integer[ret.size()]);
	}
	public static String[] listFiles(Context ctx, String folder) throws IOException {
		String[] fileNames =ctx.getAssets().list(folder);
		return fileNames;
		
	}
}
