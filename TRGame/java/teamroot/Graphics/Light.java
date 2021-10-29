package teamroot.Graphics;

import teamroot.Math.Float3;
import teamroot.Math.Float4;

public class Light
{
	public static final int LIGHT_TYPE_DIRECTION = 0;
	public static final int LIGHT_TYPE_POINT = 1;
	public static final int LIGHT_TYPE_SPOT = 2;
	
	private Float3 mPosition;
	private Float3 mDirection;
	private Float4 mColor;
	private float mRadius;
	private float mFalloff;
	
	private int mType;
	private int mIsActive;
	
	public Light()
	{
		mPosition = new Float3(0.0f, 10.0f, 20.0f);
		mDirection = new Float3(0.0f, -0.5f, -1.0f);
		mColor = new Float4(0.2f, 0.4f, 0.4f, 1.0f);
		mRadius = 16.0f;
		mFalloff = 0.6f;
		
		mType = LIGHT_TYPE_POINT;
		mIsActive = 1;
	}
	
	public void SetPosition(final float x, final float y, final float z)
	{
		mPosition.mValues[0] = x;
		mPosition.mValues[1] = y;
		mPosition.mValues[2] = z;
	}
	
	public void SetDirection(final float x, final float y, final float z)
	{
		mDirection.mValues[0] = x;
		mDirection.mValues[1] = y;
		mDirection.mValues[2] = z;
	}
	
	public void SetColor(final float Red, final float Green, final float Blue, final float Strength)
	{
		mColor.mValues[0] = Red;
		mColor.mValues[1] = Green;
		mColor.mValues[2] = Blue;
		mColor.mValues[3] = Strength;
	}
	
	public void SetRadius(final float R)
	{
		mRadius = R;
	}
	
	public void SetType(final int Type)
	{
		mType = Type;
	}
	
	public void SetFalloff(final float Falloff)
	{
		mFalloff = Falloff;
	}
	
	public float[] GetPosition()
	{
		return mPosition.mValues;
	}
	
	public Float3 GetPositionRef()
	{
		return mPosition;
	}
	
	public float[] GetDirection()
	{
		return mDirection.mValues;
	}
	
	public Float3 GetDirectionRef()
	{
		return mDirection;
	}
	
	public float[] GetColor()
	{
		return mColor.mValues;
	}
	
	public Float4 GetColorRef()
	{
		return mColor;
	}
	
	public float GetRadius()
	{
		return mRadius;
	}
	
	public int GetType()
	{
		return mType;
	}
	
	public float GetFalloff()
	{
		return mFalloff;
	}
	
	public void SetState(final boolean IsActive)
	{
		if (IsActive)
			mIsActive = 1;
		else
			mIsActive = 0;
	}
	
	public int GetActiveStatus()
	{
		return mIsActive;
	}
}
