package teamroot.OGL.Shaders;

public abstract class ShaderInputBase
{
	protected int mHandle;
	protected String mName;
	
	public ShaderInputBase(final String InputName)
	{
		mName = InputName;
		mHandle = -1;
	}
	
	public abstract int GetHandle(final int ShaderID);
}
