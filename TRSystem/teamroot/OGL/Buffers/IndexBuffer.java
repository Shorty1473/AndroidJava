package teamroot.OGL.Buffers;

import android.opengl.GLES20;

import teamroot.OGL.Vertices.IndexArray;

public class IndexBuffer extends ABuffer
{
	private final int SizeOfShort = 2;
	private int mBufferID;
	
	public IndexBuffer(final int BufferID)
	{
		mBuffer = null;
		mBufferID = BufferID;
	}
	
	public void BuildBuffer(IndexArray indices)
	{
		mBuffer = indices.BuildIndexBuffer();
		mElementCount = indices.GetElementCount();
		
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mBufferID);
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, mBuffer.capacity(), mBuffer, GLES20.GL_STATIC_DRAW);
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	@Override
	public void BindBuffer()
	{
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, mBufferID);
	}
	
	@Override
	public void UnbindBuffer()
	{
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
}
