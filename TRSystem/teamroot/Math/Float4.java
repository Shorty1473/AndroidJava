package teamroot.Math;

import java.nio.ByteBuffer;

public class Float4
{
	public static final int BYTES = (4 * 4);
	
	public float[] mValues;
	
	public Float4()
	{
		mValues = new float[4];
	}
	
	public Float4(final float v)
	{
		mValues = new float[4];
		mValues[0] = mValues[1] = mValues[2] = mValues[3] = v;
	}
	
	public Float4(final float x, final float y, final float z, final float w)
	{
		mValues = new float[4];
		mValues[0] = x;
		mValues[1] = y;
		mValues[2] = z;
		mValues[3] = w;
	}
	
	public Float4(Float4 v)
	{
		mValues = new float[4];
		mValues[0] = v.mValues[0];
		mValues[1] = v.mValues[1];
		mValues[2] = v.mValues[2];
		mValues[3] = v.mValues[3];
	}
	
	public void Set(final float x, final float y, final float z, final float w)
	{
		mValues[0] = x;
		mValues[1] = y;
		mValues[2] = z;
		mValues[3] = w;
	}
	
	public void Normalize()
	{
		if(mValues[0] == 0 && mValues[1] == 0 && mValues[2] == 0 && mValues[3] == 0)
			return;
		
		final float x = mValues[0];
		final float y = mValues[1];
		final float z = mValues[2];
		final float w = mValues[3];
		
		float l_ = (float)Math.sqrt((x * x) + (y * y) + (z * z) + (w * w));
		
		mValues[0] = x / l_;
		mValues[1] = y / l_;
		mValues[2] = z / l_;
		mValues[3] = w / l_;
	}
	
	public void PushToBuffer(ByteBuffer buffer)
	{
		buffer.putFloat(mValues[0]);
		buffer.putFloat(mValues[1]);
		buffer.putFloat(mValues[2]);
		buffer.putFloat(mValues[3]);
	}
	
	public void ReadFromBuffer(ByteBuffer buffer)
	{
		mValues[0] = buffer.getFloat();
		mValues[1] = buffer.getFloat();
		mValues[2] = buffer.getFloat();
		mValues[3] = buffer.getFloat();
	}
}
