package teamroot.OGL.Buffers;

import java.nio.Buffer;

public abstract class ABuffer
{
	protected Buffer mBuffer;
	protected int mElementCount;
	
	public ABuffer()
	{
		mBuffer = null;
		mElementCount = 0;
	}
	
	public int GetBufferCount()
	{
		return mElementCount;
	}
	
	public abstract void BindBuffer();
	public abstract void UnbindBuffer();
}
