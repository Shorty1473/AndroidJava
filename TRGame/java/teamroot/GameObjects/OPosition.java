package teamroot.GameObjects;

import java.nio.ByteBuffer;

import teamroot.Math.Float3;

public class OPosition
{
	public static final int BYTES = (Float3.BYTES * 3);
	
	private Float3 mPosition;
	private Float3 mRotation;
	private Float3 mScale;
	
	public OPosition()
	{
		mPosition = new Float3();
		mRotation = new Float3();
		mScale = new Float3();
	}
	
	public void PushToByteBuffer(ByteBuffer buffer)
	{
		mPosition.PushToBuffer(buffer);
		mRotation.PushToBuffer(buffer);
		mScale.PushToBuffer(buffer);
	}
	
	public void ReadFromByteBuffer(ByteBuffer buffer)
	{
		mPosition.ReadFromBuffer(buffer);
		mRotation.ReadFromBuffer(buffer);
		mScale.ReadFromBuffer(buffer);
	}
	
	public Float3 GetPosition()
	{
		return mPosition;
	}
	
	public Float3 GetRotation()
	{
		return mRotation;
	}
	
	public Float3 GetScale()
	{
		return mScale;
	}
}
