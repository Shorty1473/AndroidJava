package teamroot.OGL.Graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TextResources
{
	private static class Resource
	{
		private final float TextSize = 16.0f;
		private final int Width = 8;
		
		private Bitmap mImage;
		private Canvas mCanvas;
		private String mName;
		
		private Texture mOGLTexture;
		
		public Resource(final String Name)
		{
			mName = Name;
			
			mOGLTexture = new Texture(mName);
		}
		
		public void CreateResource(final Paint pnt, final String Text)
		{
			final int ww_ = Math.max((Text.length() / 2), Width);
			final int w_ = (int)(TextSize * (float)ww_);
			mImage = Bitmap.createBitmap(w_, (int)(TextSize * 2.0f), Bitmap.Config.ARGB_8888);
			mImage.eraseColor(Color.argb(0, 255, 255, 255));
			
			final float cvs_x = (mImage.getWidth() / 2.0f);
			final float csv_y = (mImage.getHeight() / 2.0f) - (pnt.descent() + (pnt.ascent() / 2.0f));
			
			Canvas cvs = new Canvas(mImage);
			cvs.drawText(Text, cvs_x, csv_y, pnt);
			
			mOGLTexture.SetTexture(mImage);
		}
		
		public void OverwriteResource(final Paint pnt, final String Text)
		{
			CreateResource(pnt, Text);
		}
		
		public void Recycle()
		{
			if(mImage != null)
				mImage.recycle();
			
			mCanvas = null;
		}
	}
	
	private Paint mPainter;
	private List<Resource> mResources;
	
	public TextResources()
	{
		mPainter = new Paint();
		mResources = new ArrayList<>();
		
		mPainter.setTextSize(TextSize);
		mPainter.setTextAlign(Paint.Align.CENTER);
		mPainter.setARGB(255, 255, 255, 255);
		mPainter.setAntiAlias(true);
	}
	
	protected boolean ResourceExists(final String Name, int[]v)
	{
		final int count_ = mResources.size();
		for(int i = 0;i < count_; ++i)
		{
			if(mResources.get(i).mName.equals(Name))
			{
				if(v != null)
					v[0] = i;
				
				return true;
			}
		}
		
		return false;
	}
	
	private final float TextSize = 16.0f;
	private final int Width = 8;
	
	protected void OverwriteTexture(final String Text, final int ID)
	{
		final int w_ = (int)(TextSize * (float)Width);
		
		mResources.get(ID).OverwriteResource(mPainter, Text);
	}
	
	protected void CreateNew(final String Text, final String Name)
	{
		Resource nres = new Resource(Name);
		nres.CreateResource(mPainter, Text);
		
		mResources.add(nres);
	}
	
	public void AddTexture(final String Text, final String Name)
	{
		int[]TexID = new int[1];
		TexID[0] = -1;
		
		if(ResourceExists(Name, TexID))
		{
			mResources.get(TexID[0]).Recycle();
			OverwriteTexture(Text, TexID[0]);
			//Log.d("OWG", "ResourceExists()");
			return;
		}
		
		CreateNew(Text, Name);
	}
	
	public Bitmap GetResource(final String Name)
	{
		int[]TexID = new int[1];
		if(!ResourceExists(Name, TexID))
		{
			Log.d("System.Graphics", "No Bitmap with name: " + Name + " exists!");
			return null;
		}
		
		return mResources.get(TexID[0]).mImage;
	}
	
	public Texture GetTexture(final String Name)
	{
		int[]TexID = new int[1];
		if(!ResourceExists(Name, TexID))
		{
			Log.d("System.Graphics", "No Texture with name: " + Name + " exists!");
			return null;
		}
		
		return mResources.get(TexID[0]).mOGLTexture;
	}
	
	public boolean BindToTexture(Texture inTexture, final String Name)
	{
		int[]TexID = new int[1];
		
		if(!ResourceExists(Name, TexID))
		{
			Log.d("System.Graphics", "No resource with name: " + Name + " exists!");
			return false;
		}
		
		inTexture.SetTexture(mResources.get(TexID[0]).mImage);
		//Log.d("OWG", "BindToTexture()");
		
		return true;
	}
	
	public boolean BindToOpenGL(final String Name, int[]TextureID, final int IDOffset)
	{
		int[]TexID = new int[1];
		
		if(!ResourceExists(Name, TexID))
		{
			Log.d("System.Graphics", "No resource with name: " + Name + " exists!");
			return false;
		}
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, TextureID[IDOffset]);
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mResources.get(TexID[0]).mImage, 0);
		
		GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		
		return true;
	}
}
