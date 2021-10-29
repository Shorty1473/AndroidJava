package teamroot.Game;

import android.opengl.Matrix;

import teamroot.Math.Float3;
import teamroot.Math.Matrices;

public class Camera
{
	private Float3 mPosition;
	private Float3 mLookAt;
	
	private Float3 mRotationOrigin;
	private Float3 mRotation;
	
	private float[] mViewMatrix;
	protected float[] mProjectionMatrix;
	private float[] mOutputMatrix;
	
	private void UpdateViewMatrix()
	{
		Matrix.setIdentityM(mViewMatrix, 0);
		
		while(mRotation.mValues[1] > 6.28f)
			mRotation.mValues[1] -= 6.28f;
		
		while(mRotation.mValues[1] < -6.28f)
			mRotation.mValues[1] += 6.28f;
		
		Float3 itp_pos = new Float3(mPosition);
		itp_pos.mValues[0] += (float)Math.sin(mRotation.mValues[1]) * mRotationOrigin.mValues[0];
		itp_pos.mValues[2] += (float)Math.cos(mRotation.mValues[1]) * mRotationOrigin.mValues[2];
		
		Matrices.SetLookAt(mViewMatrix, itp_pos, mLookAt);
	}
	
	public Camera()
	{
		mPosition = new Float3();
		mLookAt = new Float3();
		mRotation = new Float3();
		mRotationOrigin = new Float3(0.0f, 0.0f, 0.0f);
		
		mOutputMatrix = new float[16];
		mViewMatrix = new float[16];
		mProjectionMatrix = new float[16];
		
		final float Ratio = 1.0f;
		
		Matrix.frustumM(mProjectionMatrix, 0, -Ratio, Ratio, -1.0f, 1.0f, 1.0f, 1000.0f);
		Matrices.SetLookAt(mViewMatrix, mPosition, mLookAt);
	}
	
	public void OnScreenChange(final float ScreenWidth, final float ScreenHeight)
	{
		final float Ratio = ScreenWidth / ScreenHeight;
		
		Matrix.frustumM(mProjectionMatrix, 0, -Ratio, Ratio, -1.0f, 1.0f, 1.0f, 1000.0f);
	}
	
	public void OnScreenChangeOrtho(final float Width, final float Height)
	{
		Matrix.orthoM(mProjectionMatrix, 0, -Width, Width, -Height, Height, 1.0f, 1000.0f);
	}
	
	public void SetPosition(final float X, final float Y, final float Z)
	{
		mPosition.mValues[0] = X;
		mPosition.mValues[1] = Y;
		mPosition.mValues[2] = Z;
		
		UpdateViewMatrix();
	}
	
	public void SetRotationOrigin(final float X, final float Y, final float Z)
	{
		mRotationOrigin.mValues[0] = X;
		mRotationOrigin.mValues[1] = Y;
		mRotationOrigin.mValues[2] = Z;
		
		UpdateViewMatrix();
	}
	
	public void SetLookAt(final float X, final float Y, final float Z)
	{
		mLookAt.mValues[0] = X;
		mLookAt.mValues[1] = Y;
		mLookAt.mValues[2] = Z;
		
		UpdateViewMatrix();
	}
	
	public void UpdateCamera()
	{
		UpdateViewMatrix();
	}
	
	public void SetPosition(Float3 f3)
	{
		SetPosition(f3.mValues[0], f3.mValues[1], f3.mValues[2]);
	}
	
	public void SetLookAt(Float3 f3)
	{
		SetLookAt(f3.mValues[0], f3.mValues[1], f3.mValues[2]);
	}
	
	public Float3 GetPosition()
	{
		Float3 out_ = new Float3(mPosition);
		
		out_.mValues[0] += (float)Math.sin(mRotation.mValues[1]) * mRotationOrigin.mValues[0];
		out_.mValues[2] += (float)Math.cos(mRotation.mValues[1]) * mRotationOrigin.mValues[2];
		
		return (out_);
	}
	
	public Float3 GetPositionRef()
	{
		return mPosition;
	}
	
	public Float3 GetLookAt()
	{
		return (mLookAt);
	}
	
	public Float3 GetRotation()
	{
		return mRotation;
	}
	
	public Float3 GetRotationOrigin()
	{
		return mRotationOrigin;
	}
	
	public float[] GetViewMatrix()
	{
		return mViewMatrix;
	}
	
	public float[] GetProjectionMatrix()
	{
		return mProjectionMatrix;
	}
	
	public float[] GetVPMatrix()
	{
		UpdateViewMatrix();
		Matrix.setIdentityM(mOutputMatrix,0);
		Matrix.multiplyMM(mOutputMatrix,0, mProjectionMatrix,0, mViewMatrix,0);
		return mOutputMatrix;
	}
}
