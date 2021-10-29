package teamroot.OGL.Graphics;

import android.opengl.GLES20;
import android.util.Log;

public class RenderTexture extends Texture
{
	private int mWidth = 128;
	private int mHeight = 128;
	
	private int[] mFrameHandle;
	private int[] mRenderHandle;
	
	private void GenerateOGLHandles()
	{
		mFrameHandle = new int[1];
		mRenderHandle = new int[1];
		
		GLES20.glGenFramebuffers(1, mFrameHandle, 0);
		GLES20.glGenRenderbuffers(1, mRenderHandle, 0);
	}
	
	private void BindOGLFrameHandles()
	{
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFrameHandle[0]);
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandle[0]);
	}
	
	private void SetTextureDescription()
	{
		GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, GLES20.GL_RGBA, mWidth, mHeight, 0, GLES20.GL_RGBA, GLES20.GL_UNSIGNED_BYTE, null);
		
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE);
		
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
		GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR);
	}
	
	private void BindOGLRenderHandles()
	{
		GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, mRenderHandle[0]);
		GLES20.glRenderbufferStorage(GLES20.GL_RENDERBUFFER, GLES20.GL_DEPTH_COMPONENT16, mWidth, mHeight);
		
		GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, mHandle[0], 0);
		GLES20.glFramebufferRenderbuffer(GLES20.GL_FRAMEBUFFER, GLES20.GL_DEPTH_ATTACHMENT, GLES20.GL_RENDERBUFFER, mRenderHandle[0]);
	}
	
	private void Cleanup()
	{
		GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0);
		GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, 0);
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
	}
	
	public RenderTexture(final String Name, final int Width, final int Height)
	{
		super(Name);
		
		mWidth = Width;
		mHeight = Height;
		
		GenerateOGLHandles();
		BindOGLFrameHandles();
		SetTextureDescription();
		BindOGLRenderHandles();
		Cleanup();
	}
	
	final public float GetWidth()
	{
		return mWidth;
	}
	
	final public float GetHeight()
	{
		return mHeight;
	}
	
	final public void ActivateTarget()
	{
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFrameHandle[0]);
		//GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, mRenderHandle[0]);
		GLES20.glViewport(0, 0, mWidth, mHeight);
		
		GLES20.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
	}
	
	final public void ActivateTarget(final boolean UseClear)
	{
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFrameHandle[0]);
		//GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, mRenderHandle[0]);
		GLES20.glViewport(0, 0, mWidth, mHeight);
		
		if(UseClear)
		{
			GLES20.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
			GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		}
	}
	
	final public void ActivateTarget(final float Red, final float Green, final float Blue, final float Alpha)
	{
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, mFrameHandle[0]);
		//GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, mRenderHandle[0]);
		GLES20.glViewport(0, 0, mWidth, mHeight);
		
		GLES20.glClearColor(Red, Green, Blue, Alpha);
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
	}
	
	final public void FinishTarget()
	{
		GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0);
		//GLES20.glBindRenderbuffer(GLES20.GL_RENDERBUFFER, 0);
	}
}
