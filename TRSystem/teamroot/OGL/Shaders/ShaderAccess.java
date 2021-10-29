package teamroot.OGL.Shaders;

import android.opengl.GLES20;

public class ShaderAccess
{
	public static void SetMatrices(final int MatrixHandle, float[]Matrix)
	{
		GLES20.glUniformMatrix4fv(MatrixHandle, 1, false, Matrix, 0);
	}
	
	public static void Float4Access(final int f4Handle, float[]Value)
	{
		GLES20.glUniform4fv(f4Handle, 1, Value, 0);
	}
}
