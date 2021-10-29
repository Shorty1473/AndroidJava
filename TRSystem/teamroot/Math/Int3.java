package teamroot.Math;

import java.nio.ByteBuffer;

public class Int3
{
	public static final int BYTES = (3 * 4);
	
	public int[] mValues;
	
	public Int3()
	{
		mValues = new int[3];
	}
	
	public Int3(final int v)
	{
		mValues = new int[3];
		mValues[0] = mValues[1] = mValues[2] = v;
	}
	
	public Int3(final int x, final int y, final int z)
	{
		mValues = new int[3];
		mValues[0] = x;
		mValues[1] = y;
		mValues[2] = z;
	}
	
	public Int3(Int3 v)
	{
		mValues = new int[3];
		mValues[0] = v.mValues[0];
		mValues[1] = v.mValues[1];
		mValues[2] = v.mValues[2];
	}
	
	public void PushToBuffer(ByteBuffer buffer)
	{
		buffer.putInt(mValues[0]);
		buffer.putInt(mValues[1]);
		buffer.putInt(mValues[2]);
	}
	
	public void ReadFromBuffer(ByteBuffer buffer)
	{
		mValues[0] = buffer.getInt();
		mValues[1] = buffer.getInt();
		mValues[2] = buffer.getInt();
	}
}
