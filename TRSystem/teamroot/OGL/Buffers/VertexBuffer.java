package teamroot.OGL.Buffers;

import android.opengl.GLES20;
import android.util.Log;

import teamroot.OGL.Vertices.VertexArray;

public class VertexBuffer extends ABuffer
{
	private final int SizefOfFloat = 4;
	private int mBufferID;
	private int mStride;
	
	public VertexBuffer(final int BufferID)
	{
		mBuffer = null;
		mBufferID = BufferID;
		mStride = 0;
	}
	
	public int GetBufferCount()
	{
		return mBuffer.capacity();
	}
	
	public int GetStride()
	{
		return mStride;
	}
	
	public void BuildBuffer(VertexArray vertices)
	{
		mBuffer = vertices.BuildFloatBuffer();
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBufferID);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, mBuffer.capacity(), mBuffer, GLES20.GL_STATIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
		
		mStride = vertices.GetStride();
	}
	
	@Override
	public void BindBuffer()
	{
		//Log.d("OWG", "BindVertexBuffer: " + mBufferID);
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, mBufferID);
	}
	
	@Override
	public void UnbindBuffer()
	{
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
	}
}
