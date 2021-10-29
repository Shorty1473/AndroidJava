package teamroot.OGL.Vertices;

public abstract class AVertex
{
	protected float[]mElements;
	
	public int GetStride()
	{
		return (mElements.length * 4);
	}
	
	public int GetElementCount()
	{
		return mElements.length;
	}
	
	public float[] GetElements()
	{
		return mElements;
	}
}
