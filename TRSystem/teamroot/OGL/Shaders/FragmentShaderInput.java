package teamroot.OGL.Shaders;

import android.opengl.GLES20;

public class FragmentShaderInput extends ShaderInputBase
{
	public FragmentShaderInput(String InputName)
	{
		super(InputName);
	}
	
	@Override
	public int GetHandle(int ShaderID)
	{
		mHandle = GLES20.glGetUniformLocation(ShaderID, mName);
		return mHandle;
	}
}
