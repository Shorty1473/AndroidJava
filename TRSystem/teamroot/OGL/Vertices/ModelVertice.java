package teamroot.OGL.Vertices;

import teamroot.Math.Float3;

public class ModelVertice extends AVertex
{
	public ModelVertice()
	{
		mElements = new float[8];
	}
	
	public void SetVertexPosition(final float X, final float Y, final float Z)
	{
		mElements[0] = X;
		mElements[1] = Y;
		mElements[2] = Z;
	}
	
	public void SetUVCoordinates(final float U, final float V)
	{
		mElements[3] = U;
		mElements[4] = V;
	}
	
	public void SetNormalCoordinates(final float X, final float Y, final float Z)
	{
		mElements[5] = X;
		mElements[6] = Y;
		mElements[7] = Z;
	}
	
	public Float3 GetPosition()
	{
		return new Float3(mElements[0], mElements[1], mElements[2]);
	}
	
	public Float3 GetNormals()
	{
		return new Float3(mElements[5], mElements[6], mElements[7]);
	}
}
