package teamroot.Graphics;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.util.Log;

import teamroot.Game.Camera;
import teamroot.GameObjects.ObjectWorld;
import teamroot.OGL.Graphics.Texture;
import teamroot.OGL.IDisplay.IDrawBuffers;
import teamroot.OGL.IDisplay.IDrawable;
import teamroot.OGL.Shaders.ShaderHandles;

public abstract class ADrawable implements IDrawable
{
	private Texture[] mTextures;
	protected DrawBuffers mBuffersRef;
	
	private ObjectWorld mWorld;
	protected float[]mFinalMatrix;
	
	@Override
	public IDrawBuffers GetBuffer()
	{
		return mBuffersRef;
	}
	
	public ObjectWorld GetWorld()
	{
		return mWorld;
	}
	
	public ADrawable()
	{
		mBuffersRef = null;
		
		mTextures = null;
		mTextures = new Texture[3];
		mTextures[0] = null;
		mTextures[1] = null;
		mTextures[2] = null;
		
		mWorld = new ObjectWorld();
		
		mFinalMatrix = new float[16];
	}
	
	public void SetBufferReference(DrawBuffers buffersRef)
	{
		mBuffersRef = null;
		mBuffersRef = buffersRef;
	}
	
	public void SetTexture(Texture textureRef, final int Offset)
	{
		mTextures[Offset] = textureRef;
		
		//Log.d("System.Graphics", "Texture set " + mTextures[Offset] + " at offset " + Offset);
	}
	
	protected abstract void SetUniformValues(ShaderHandles Handles, Camera camera);
	
	public void StartDraw(ShaderHandles Handles, Camera camera)
	{
		if(mBuffersRef == null)
			return;
		
		mBuffersRef.StartDrawing();
		Matrix.setIdentityM(mFinalMatrix, 0);
		
		GetWorld().BuildWorld();
		SetUniformValues(Handles, camera);
		
		if(mTextures != null)
		{
			if(mTextures[0] != null)
				mTextures[0].BindTexture(Handles.GetFragmentHandle("inTexture0"), 0);
			
			if(mTextures[1] != null)
				mTextures[1].BindTexture(Handles.GetFragmentHandle("inTexture1"), 1);
			
			if(mTextures[2] != null)
				mTextures[2].BindTexture(Handles.GetFragmentHandle("inTexture2"), 2);
		}
	}
	
	public void EndDraw()
	{
		if(mBuffersRef == null)
			return;
		
		mBuffersRef.EndDrawing();
		
		if(mTextures != null)
		{
			if(mTextures[0] != null)
				mTextures[0].UnbindTexture();
			
			if(mTextures[1] != null)
				mTextures[1].UnbindTexture();
			
			if(mTextures[2] != null)
				mTextures[2].UnbindTexture();
		}
	}
}
