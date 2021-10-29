package teamroot.OGL.Graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import java.io.InputStream;

public class Texture
{
	protected int[] mHandle;
	private String mName;
	
	private boolean mIsText;
	private Bitmap mBitmapRef;
	
	/*public Texture(final String Name)
	{
		mHandle = new int[1];
		GLES20.glGenTextures(1, mHandle,0);
		
		mGLOffset = 0;
		mName = Name;
	}*/
	
	public Texture(final String Name)
	{
		mHandle = new int[1];
		GLES20.glGenTextures(1, mHandle,0);
		
		mName = Name;
		
		mIsText = false;
		mBitmapRef = null;
	}
	
	final public void Destroy()
	{
		if(mBitmapRef != null && !mIsText)
			mBitmapRef.recycle();
	}
	
	final public boolean IsTexture(final String Name)
	{
		return mName.equals(Name);
	}
	
	final public void BindTexture(final int ShaderHandle, final int GLOffset)
	{
		GLES20.glActiveTexture(GLES20.GL_TEXTURE0 + GLOffset);
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandle[0]);
		GLES20.glUniform1i(ShaderHandle, GLOffset);
	}
	
	final public void UnbindTexture()
	{
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}
	
	final public void SetTexture(InputStream Texture)
	{
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inScaled = false;
		
		if(mBitmapRef != null && !mIsText)
			mBitmapRef.recycle();
			
		mIsText = false;
		mBitmapRef = BitmapFactory.decodeStream(Texture);
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandle[0]);
		
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
		
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mBitmapRef, 0);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}
	
	final public Bitmap GetBitmapRef()
	{
		return mBitmapRef;
	}
	
	final public void SetTexture(Bitmap bitmapInput)
	{
		if(!mIsText && mBitmapRef != null)
			mBitmapRef.recycle();
		
		mIsText = true;
		mBitmapRef = bitmapInput;
		
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandle[0]);
		GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, mBitmapRef, 0);
		
		GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
	}
}
