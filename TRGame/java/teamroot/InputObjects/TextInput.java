package teamroot.InputObjects;

import android.opengl.GLES20;

import teamroot.Game.Camera;
import teamroot.Graphics.ADrawableQuad;
import teamroot.Math.Matrices;
import teamroot.OGL.Shaders.ShaderHandles;

public class TextInput extends ADrawableQuad
{
	public TextInput()
	{
		super();
	}
	
	@Override
	protected void SetUniformValues(ShaderHandles shaderHandles, Camera camera)
	{
		final int World = shaderHandles.GetFragmentHandle("WVPMatrix");
		final int Color = shaderHandles.GetFragmentHandle("inColor");
		
		Matrices.Multiply(mFinalMatrix, camera.GetVPMatrix(), GetWorld().GetWorldMatrix());
		
		GLES20.glUniformMatrix4fv(World, 1, false, mFinalMatrix, 0);
		GLES20.glUniform4fv(Color, 1, mColor, 0);
	}
}
