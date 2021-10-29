package teamroot.Math;

import android.opengl.Matrix;

public class Matrices
{
	public static void Translate(float[]M, Float3 Value)
	{
		Matrix.translateM(M, 0, Value.mValues[0], Value.mValues[1], Value.mValues[2]);
	}
	
	public static void RotateX(float[]M, float Angle)
	{
		Matrix.rotateM(M, 0, (float)Math.toDegrees(Angle), 1.0f, 0.0f, 0.0f);
	}
	
	public static void RotateY(float[]M, float Angle)
	{
		Matrix.rotateM(M, 0, (float)Math.toDegrees(Angle), 0.0f, 1.0f, 0.0f);
	}
	
	public static void RotateZ(float[]M, float Angle)
	{
		Matrix.rotateM(M, 0, (float)Math.toDegrees(Angle), 0.0f, 0.0f, 1.0f);
	}
	
	public static void RotateXA(float[]M, float Angle)
	{
		Matrix.rotateM(M, 0, Angle, 1.0f, 0.0f, 0.0f);
	}
	
	public static void RotateYA(float[]M, float Angle)
	{
		Matrix.rotateM(M, 0, Angle, 0.0f, 1.0f, 0.0f);
	}
	
	public static void RotateZA(float[]M, float Angle)
	{
		Matrix.rotateM(M, 0, Angle, 0.0f, 0.0f, 1.0f);
	}
	
	public static void Scale(float[]M, Float3 Scale)
	{
		Matrix.scaleM(M, 0, Scale.mValues[0], Scale.mValues[1], Scale.mValues[2]);
	}
	
	public static void SetLookAt(float[]M, Float3 Position, Float3 Look)
	{
		Matrix.setLookAtM(
				M, 0,
				Position.mValues[0], Position.mValues[1], Position.mValues[2],
				Look.mValues[0], Look.mValues[1], Look.mValues[2],
				0.0f, 1.0f, 0.0f);
	}
	
	public static void Multiply(float[]Result, float[]Left, float[]Right)
	{
		Matrix.multiplyMM(Result, 0, Left,0, Right, 0);
	}
}
