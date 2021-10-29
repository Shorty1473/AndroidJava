package teamroot.Math;

import java.nio.ByteBuffer;

public class Float3
{
	public static final int BYTES = (3 * 4);
	
	public float[] mValues;
	
	public Float3()
	{
		mValues = new float[3];
	}
	
	public Float3(final float v)
	{
		mValues = new float[3];
		mValues[0] = mValues[1] = mValues[2] = v;
	}
	
	public Float3(final float x, final float y, final float z)
	{
		mValues = new float[3];
		Set(x, y, z);
	}
	
	public Float3(Float3 v)
	{
		mValues = new float[3];
		mValues[0] = v.mValues[0];
		mValues[1] = v.mValues[1];
		mValues[2] = v.mValues[2];
	}
	
	public void Set(final float x, final float y, final float z)
	{
		mValues[0] = x;
		mValues[1] = y;
		mValues[2] = z;
	}
	
	public void Set(Float3 rhs)
	{
		mValues[0] = rhs.mValues[0];
		mValues[1] = rhs.mValues[1];
		mValues[2] = rhs.mValues[2];
	}
	
	public Float3 Add(Float3 rhs)
	{
		mValues[0] += rhs.mValues[0];
		mValues[1] += rhs.mValues[1];
		mValues[2] += rhs.mValues[2];
		
		return this;
	}
	
	public Float3 Sub(Float3 rhs)
	{
		mValues[0] -= rhs.mValues[0];
		mValues[1] -= rhs.mValues[1];
		mValues[2] -= rhs.mValues[2];
		
		return this;
	}
	
	public Float3 Div(Float3 rhs)
	{
		mValues[0] /= rhs.mValues[0];
		mValues[1] /= rhs.mValues[1];
		mValues[2] /= rhs.mValues[2];
		
		return this;
	}
	
	public Float3 Mul(float rhs)
	{
		mValues[0] *= rhs;
		mValues[1] *= rhs;
		mValues[2] *= rhs;
		
		return this;
	}
	
	public Float3 Mul(Float3 rhs)
	{
		mValues[0] *= rhs.mValues[0];
		mValues[1] *= rhs.mValues[1];
		mValues[2] *= rhs.mValues[2];
		
		return this;
	}
	
	public Float3 Div(final float rhs)
	{
		mValues[0] /= rhs;
		mValues[1] /= rhs;
		mValues[2] /= rhs;
		
		return this;
	}
	
	public Float3 Cross(Float3 rhs)
	{
		float temp1 = (mValues[1] * rhs.mValues[2]) - (mValues[2] * rhs.mValues[1]);
		float temp2 = (mValues[2] * rhs.mValues[0]) - (mValues[0] * rhs.mValues[2]);
		float temp3 = (mValues[0] * rhs.mValues[1]) - (mValues[1] * rhs.mValues[0]);
		
		mValues[0] = temp1;
		mValues[1] = temp2;
		mValues[2] = temp3;
		
		return this;
	}
	
	public void Normalize()
	{
		if(mValues[0] == 0 && mValues[1] == 0 && mValues[2] == 0)
			return;
		
		final float x = mValues[0];
		final float y = mValues[1];
		final float z = mValues[2];
		
		float l_ = (float)Math.sqrt((x * x) + (y * y) + (z * z));
		
		mValues[0] = x / l_;
		mValues[1] = y / l_;
		mValues[2] = z / l_;
	}
	
	public float Distance(Float3 rhs)
	{
		final float x = (mValues[0] - rhs.mValues[0]);
		final float y = (mValues[1] - rhs.mValues[1]);
		final float z = (mValues[2] - rhs.mValues[2]);
		
		return (float)Math.sqrt((x * x) + (y * y) + (z * z));
	}
	
	public void PushToBuffer(ByteBuffer buffer)
	{
		buffer.putFloat(mValues[0]);
		buffer.putFloat(mValues[1]);
		buffer.putFloat(mValues[2]);
	}
	
	public void ReadFromBuffer(ByteBuffer buffer)
	{
		mValues[0] = buffer.getFloat();
		mValues[1] = buffer.getFloat();
		mValues[2] = buffer.getFloat();
	}
}
