package teamroot.OGL.ADisplay;

import android.content.Context;
import android.text.method.Touch;

import teamroot.Android.TouchScreen;
import teamroot.OGL.IDisplay.IRenderer;

public abstract class ARenderer implements IRenderer
{
	protected Context mContext;
	protected TouchScreen mTouchScreen;
	
	public ARenderer(Context context)
	{
		mContext = context;
		mTouchScreen = null;
	}
	
	@Override
	public void AssignTouchScreen(TouchScreen touchScreen)
	{
		mTouchScreen = touchScreen;
	}
	
	//public abstract boolean onTouchEvent(MotionEvent e);
}
