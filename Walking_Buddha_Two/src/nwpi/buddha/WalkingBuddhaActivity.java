package nwpi.buddha;

import android.app.Activity;
import android.os.Bundle;

public class WalkingBuddhaActivity extends Activity {
    final int GREETING = 0;
    final int PRAYER_SELECTION = 1;
    final int STUPA_SELECTION = 2;
    final int p3 = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setScreen(GREETING);
    }
    public void setScreen(int screen) {
    	switch(screen) {
    	case GREETING:
    		setContentView(R.layout.greeting_page);
    		break;
    	case PRAYER_SELECTION:
    		setContentView(R.layout.greeting_page);
    		break;
    	 case STUPA_SELECTION:
    		 break;
    	}
    	
    }
}