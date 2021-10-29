package teamroot.OGL.Base;

import android.content.Context;
import android.view.MotionEvent;

import teamroot.Android.TouchScreen;
import teamroot.OGL.ADisplay.AGameView;

public class GameView extends AGameView
{
	public GameView(Context context, TouchScreen touchScreen)
	{
		super(context, touchScreen);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		mRenderer.OnTouchEvent(e);
		return true;
	}
}
