package teamroot.Math;

import java.nio.ByteBuffer;

public class Float2
{
	public static final int BYTES = (2 * 4);
	
	public float[] mValues;
	
	public Float2()
	{
		mValues = new float[2];
	}
	
	public Float2(final float v)
	{
		mValues = new float[3];
		mValues[0] = mValues[1] = v;
	}
	
	public Float2(final float x, final float y)
	{
		mValues = new float[2];
		mValues[0] = x;
		mValues[1] = y;
	}
	
	public Float2(Float2 v)
	{
		mValues = new float[2];
		mValues[0] = v.mValues[0];
		mValues[1] = v.mValues[1];
	}
	
	public void Normalize()
	{
		if(mValues[0] == 0 && mValues[1] == 0)
			return;
		
		final float x = mValues[0];
		final float y = mValues[1];
		
		float l_ = (float)Math.sqrt((x * x) + (y * y));
		
		mValues[0] = x / l_;
		mValues[1] = y / l_;
	}
	
	public void PushToBuffer(ByteBuffer buffer)
	{
		buffer.putFloat(mValues[0]);
		buffer.putFloat(mValues[1]);
	}
	
	public void ReadFromBuffer(ByteBuffer buffer)
	{
		mValues[0] = buffer.getFloat();
		mValues[1] = buffer.getFloat();
	}
}
