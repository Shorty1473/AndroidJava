package teamroot.Android;

import android.util.Log;
import android.view.MotionEvent;

public class TouchScreen
{
	public static final int MAX_POINTERS = 4;
	
	private TouchPointer[] mPointers;
	private int[] mActivePointers;
	
	private float mWidth;
	private float mHeight;
	
	public TouchScreen(final float ScreenWidth, final float ScreenHeight)
	{
		mPointers = new TouchPointer[MAX_POINTERS];
		mActivePointers = new int[MAX_POINTERS];
		
		for(int i = 0; i < MAX_POINTERS; ++i)
		{
			mPointers[i] = new TouchPointer();
			mActivePointers[i] = MotionEvent.INVALID_POINTER_ID;
		}
		
		mWidth = ScreenWidth;
		mHeight = ScreenHeight;
	}
	
	public void OnScreenChange(final float ScreenWidth, final float ScreenHeight)
	{
		mWidth = ScreenWidth;
		mHeight = ScreenHeight;
	}
	
	public void OnTouchProcess(MotionEvent event)
	{
		int pid = -1;
		
		for(int i = 0; i < MAX_POINTERS; ++i)
		{
			pid = MotionEvent.INVALID_POINTER_ID;
			mActivePointers[i] = 0;
			
			try
			{
				pid = event.getPointerId(i);
			}
			catch(IllegalArgumentException e)
			{
				continue;
			}
			
			mActivePointers[i] = 1;
			mPointers[pid].UpdatePointer(event, i, mWidth, mHeight);
		}
	}
	
	public int[] GetPointerState()
	{
		return mActivePointers;
	}
	
	public TouchPointer GetPointer(final int PointerID)
	{
		return mPointers[PointerID];
	}
}
