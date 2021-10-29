package teamroot.Android;

import android.view.MotionEvent;

public class TouchPointer
{
	public static final int UNKNOWN_GESTURE = 0;
	public static final int GESTURE_SWIPE_UP = 1;
	public static final int GESTURE_SWIPE_DOWN = 2;
	public static final int GESTURE_SWIPE_LEFT = 4;
	public static final int GESTURE_SWIPE_RIGHT = 8;
	public static final int GESTURE_NONE = 16;
	
	private float mX, mY;
	private float mPrevX, mPrevY;
	private float mChangeX, mChangeY;
	
	private float mGestureX, mGestureY;
	private float mGestureXD, mGestureYD;
	
	private int mAction;
	private int mGesture;
	
	private final static float GestureThreshold = 0.075f;
	
	private void DetectGesture()
	{
		final float xc_ = (mGestureX - mGestureXD);
		final float yc_ = (mGestureY - mGestureYD);
		
		if((Math.abs(xc_) < GestureThreshold) && (Math.abs(yc_) < GestureThreshold))
			mGesture = GESTURE_NONE;
		
		//Log.d("System.Touch", "Detect Gesture(" + xc_ + ", " + yc_ + ")");
		
		if(xc_ < -GestureThreshold)
			mGesture |= GESTURE_SWIPE_RIGHT;
		
		if(xc_ > GestureThreshold)
			mGesture |= GESTURE_SWIPE_LEFT;
		
		if(yc_ < -GestureThreshold)
			mGesture |= GESTURE_SWIPE_DOWN;
		
		if(yc_ > GestureThreshold)
			mGesture |= GESTURE_SWIPE_UP;
	}
	
	public TouchPointer()
	{
		mX = mY = 0;
		mPrevX = mPrevY = 0;
		mChangeX = mChangeY = 0;
		
		mAction = -1;
		mGesture = UNKNOWN_GESTURE;
	}
	
	public void UpdatePointer(MotionEvent event, final int PointerIndex, final float ScreenWidth, final float ScreenHeight)
	{
		mAction = event.getActionMasked();
		mPrevX = event.getX(PointerIndex) / ScreenWidth;
		mPrevY = event.getY(PointerIndex) / ScreenHeight;
		
		if(mAction == MotionEvent.ACTION_DOWN || mAction == MotionEvent.ACTION_POINTER_DOWN)
		{
			mGesture = UNKNOWN_GESTURE;
			
			mGestureX = mPrevX;
			mGestureY = mPrevY;
		}
		
		if(mAction == MotionEvent.ACTION_UP || mAction == MotionEvent.ACTION_POINTER_UP)
		{
			mGestureXD = mPrevX;
			mGestureYD = mPrevY;
			
			DetectGesture();
		}
		
		mX = mPrevX;
		mY = mPrevY;
	}
	
	public float GetX()
	{
		return mX;
	}
	
	public float GetY()
	{
		return mY;
	}
	
	public float GetPreviousX()
	{
		return mPrevX;
	}
	
	public float GetPreviousY()
	{
		return mPrevY;
	}
	
	public int GetAction()
	{
		return mAction;
	}
	
	public int GetGesture()
	{
		return mGesture;
	}
}
