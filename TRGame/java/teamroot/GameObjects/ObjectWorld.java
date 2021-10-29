package teamroot.GameObjects;

import android.opengl.Matrix;

import java.nio.ByteBuffer;

import teamroot.Game.Base.IObjectState;
import teamroot.Math.Float3;
import teamroot.Math.Matrices;

public class ObjectWorld implements IObjectState
{
	protected float[] mWorldMatrix;
	
	protected Float3 mPosition;
	protected Float3 mRotation;
	protected Float3 mScale;
	
	public ObjectWorld()
	{
		mWorldMatrix = new float[16];
		
		mPosition = new Float3(0.0f);
		mRotation = new Float3(0.0f);
		mScale = new Float3(1.0f);
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
	
	public void BuildWorld()
	{
		Matrix.setIdentityM(mWorldMatrix, 0);
		
		Matrices.Translate(mWorldMatrix, mPosition);
		
		Matrices.RotateXA(mWorldMatrix, mRotation.mValues[0]);
		Matrices.RotateYA(mWorldMatrix, mRotation.mValues[1]);
		Matrices.RotateZA(mWorldMatrix, mRotation.mValues[2]);
		
		Matrices.Scale(mWorldMatrix, mScale);
	}
	
	public float[] GetWorldMatrix()
	{
		return mWorldMatrix;
	}
	
	@Override
	public void PushToBuffer(ByteBuffer buffer)
	{
		buffer.putFloat(mPosition.mValues[0]);
		buffer.putFloat(mPosition.mValues[1]);
		buffer.putFloat(mPosition.mValues[2]);
		
		buffer.putFloat(mRotation.mValues[0]);
		buffer.putFloat(mRotation.mValues[1]);
		buffer.putFloat(mRotation.mValues[2]);
		
		buffer.putFloat(mScale.mValues[0]);
		buffer.putFloat(mScale.mValues[1]);
		buffer.putFloat(mScale.mValues[2]);
	}
	
	@Override
	public void ReadFromBuffer(ByteBuffer buffer)
	{
		mPosition.mValues[0] = buffer.getFloat();
		mPosition.mValues[1] = buffer.getFloat();
		mPosition.mValues[2] = buffer.getFloat();
		
		mRotation.mValues[0] = buffer.getFloat();
		mRotation.mValues[1] = buffer.getFloat();
		mRotation.mValues[2] = buffer.getFloat();
		
		mScale.mValues[0] = buffer.getFloat();
		mScale.mValues[1] = buffer.getFloat();
		mScale.mValues[2] = buffer.getFloat();
	}
	
	@Override
	public int GetSizeInBytes()
	{
		return (Float3.BYTES * 3);
	}
}
