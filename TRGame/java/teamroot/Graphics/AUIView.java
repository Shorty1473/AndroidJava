package teamroot.Graphics;

import android.content.Context;
import android.view.MotionEvent;

import teamroot.Android.Base.GraphicsLayout;
import teamroot.OGL.Graphics.TextResources;
import teamroot.OGL.Graphics.TextureCollection;

public abstract class AUIView
{
	protected Context mContext;
	protected TextResources mText;
	protected TextureCollection mTextures;
	
	public AUIView(Context context, TextResources textResources, TextureCollection textureCollection)
	{
		mContext = context;
		mText = textResources;
		mTextures = textureCollection;
	}
	
	public abstract void OnCreateView();
	public abstract void OnDrawView();
	public abstract int OnTouchView(GraphicsLayout owner, MotionEvent event);
	
	protected int mWidth;
	protected int mHeight;
	
	protected abstract void OnSurfaceChanged();
	public void SurfaceChanged(final int Width, final int Height)
	{
		mWidth = Width;
		mHeight = Height;
		
		OnSurfaceChanged();
	}
}
