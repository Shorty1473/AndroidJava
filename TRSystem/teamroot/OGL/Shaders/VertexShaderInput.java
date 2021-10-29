package teamroot.OGL.Shaders;

import android.opengl.GLES20;
import android.util.Log;

import teamroot.OGL.Base.DisplayBuffer;

public class VertexShaderInput extends ShaderInputBase
{
	private int mInputOffset;
	
	public VertexShaderInput(String InputName, final int BufferOffset)
	{
		super(InputName);
		mInputOffset = BufferOffset;
		
		Log.d("System.Graphics", "InputName: " + InputName + " | Offset: " + mInputOffset);
	}
	
	@Override
	public int GetHandle(int ShaderID)
	{
		mHandle = GLES20.glGetAttribLocation(ShaderID, mName);
		return mHandle;
	}
	
	public void Activate(DisplayBuffer displayBuffer)
	{
		//Log.d("System.Graphics", "Stride: " + displayBuffer.GetStride());
		//Log.d("System.Graphics", "Handle: " + mHandle + " | Name: " + mName);
		
		if(mHandle == -1)
			return;
		
		GLES20.glVertexAttribPointer(mHandle, 3, GLES20.GL_FLOAT, false, displayBuffer.GetStride(), mInputOffset);
		GLES20.glEnableVertexAttribArray(mHandle);
	}
	
	public void Deactivate()
	{
		GLES20.glDisableVertexAttribArray(mHandle);
	}
}
