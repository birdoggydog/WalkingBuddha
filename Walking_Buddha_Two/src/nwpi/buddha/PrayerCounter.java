package nwpi.buddha;

import android.view.View;

public class PrayerCounter {

	private ScreenController screenCont;
	int numPrayers = 0;
	boolean minusEnabled = false;
	public PrayerCounter(ScreenController screenCont) {
		this.screenCont = screenCont;
	}
    public void plusClicked() {
    	setNum(1);
    	screenCont.setPrayerCounter(numPrayers);
    }

    public void minusClicked() {
    	if(minusEnabled) {
    		setNum(-1);
        	screenCont.setPrayerCounter(numPrayers);
    	} 
    }
    private void setNum(int toAdd) {
    	numPrayers+=toAdd;
    	if(numPrayers<=0) {
    		minusEnabled= false;
    	}
    	else {
    		minusEnabled =true;
    	}
    }
    public boolean getMinusEanabled() {
    	return minusEnabled;
    }

}
