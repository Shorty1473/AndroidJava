package teamroot.OGL.Vertices;

import teamroot.Math.Float3;

public class UIVertice extends AVertex
{
	public UIVertice()
	{
		mElements = new float[5];
	}
	
	public void SetVertexPosition(final float X, final float Y, final float Z)
	{
		mElements[0] = X;
		mElements[1] = Y;
		mElements[2] = Z;
	}
	
	public void SetVertexPosition(final Float3 pos)
	{
		mElements[0] = pos.mValues[0];
		mElements[1] = pos.mValues[1];
		mElements[2] = pos.mValues[2];
	}
	
	public void SetUVCoordinates(final float U, final float V)
	{
		mElements[3] = U;
		mElements[4] = V;
	}
}
