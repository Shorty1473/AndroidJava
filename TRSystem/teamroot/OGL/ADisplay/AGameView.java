package teamroot.OGL.ADisplay;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import teamroot.Android.TouchScreen;
import teamroot.OGL.IDisplay.IRenderer;

public abstract class AGameView extends GLSurfaceView
{
	protected IRenderer mRenderer; //Holds reference to GLSurfaceView.Renderer
	protected TouchScreen mTouchScreen;
	
	public AGameView(Context context, TouchScreen touchScreen)
	{
		super(context);
		
		mRenderer = null;
		mTouchScreen = touchScreen;
	}
	
	public AGameView(Context context, TouchScreen touchScreen, AttributeSet attrs)
	{
		super(context, attrs);
		
		mRenderer = null;
		mTouchScreen = touchScreen;
	}
	
	public void SetRenderer(IRenderer renderer)
	{
		mRenderer = renderer;
		mRenderer.AssignTouchScreen(mTouchScreen);
		
		setEGLContextClientVersion(2);
		setRenderer(mRenderer);
		setRenderMode(RENDERMODE_CONTINUOUSLY);
	}
	
	public void onResume()
	{
		super.onResume();
	}
	
	public IRenderer GetRenderer()
	{
		return mRenderer;
	}
}
