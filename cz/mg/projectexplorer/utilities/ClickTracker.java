package cz.mg.projectexplorer.utilities;

import cz.mg.collections.chainlist.ChainList;


public class ClickTracker {
	private static final long CLICK_TIME = 250; // milliseconds
	private final ChainList<Long> clicks = new ChainList<>();
	
	public int click(){
		long currentClickTime = System.currentTimeMillis();
		clicks.addLast(currentClickTime);
		int clickCount = 0;
		for(Long click : clicks){
			if(currentClickTime - click < CLICK_TIME) clickCount++;
		}
		return clickCount;
	}
	
	public void reset(){
		clicks.clear();
	}
}
