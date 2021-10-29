package teamroot.Graphics;

import android.opengl.GLES20;

import teamroot.OGL.Shaders.Shader;
import teamroot.OGL.Shaders.ShaderHandles;

public class ShaderLight
{
	public static final int MAX_LIGHT_COUNT = 8;
	private Light[] mLights;
	
	public ShaderLight(Shader shader)
	{
		String PosHandle = "LightPosition";
		String DirHandle = "LightDirection";
		String ColHandle = "LightColor";
		String ActHandle = "LightActive";
		String RadHandle = "LightRadius";
		String TypHandle = "LightType";
		String FalHandle = "LightFalloff";
		
		mLights = new Light[MAX_LIGHT_COUNT];
		for(int i = 0; i < MAX_LIGHT_COUNT; ++i)
		{
			mLights[i] = new Light();
			
			PosHandle = "LightPosition[" + i + "]";
			DirHandle = "LightDirection[" + i + "]";
			ColHandle = "LightColor[" + i + "]";
			ActHandle = "LightActive[" + i + "]";
			RadHandle = "LightRadius[" + i + "]";
			TypHandle = "LightType[" + i + "]";
			FalHandle = "LightFalloff[" + i + "]";
			
			shader.AddFragmentHandle(PosHandle);
			shader.AddFragmentHandle(DirHandle);
			shader.AddFragmentHandle(ColHandle);
			shader.AddFragmentHandle(ActHandle);
			shader.AddFragmentHandle(RadHandle);
			shader.AddFragmentHandle(TypHandle);
			shader.AddFragmentHandle(FalHandle);
		}
	}
	
	public Light GetLight(final int LightID)
	{
		return mLights[LightID];
	}
	
	public void OnDrawFrame(ShaderHandles shaderHandles)
	{
		for(int i = 0; i < MAX_LIGHT_COUNT; ++i)
		{
			final int lp_ = shaderHandles.GetFragmentHandle("LightPosition[" + i + "]");
			final int ld_ = shaderHandles.GetFragmentHandle("LightDirection[" + i + "]");
			final int lc_ = shaderHandles.GetFragmentHandle("LightColor[" + i + "]");
			final int la_ = shaderHandles.GetFragmentHandle("LightActive[" + i + "]");
			final int lr_ = shaderHandles.GetFragmentHandle("LightRadius[" + i + "]");
			final int lt_ = shaderHandles.GetFragmentHandle("LightType[" + i + "]");
			final int lf_ = shaderHandles.GetFragmentHandle("LightFalloff[" + i + "]");
			
			GLES20.glUniform1i(la_, mLights[i].GetActiveStatus());
			GLES20.glUniform1i(lt_, mLights[i].GetType());
			GLES20.glUniform3fv(lp_, 1, mLights[i].GetPosition(), 0);
			GLES20.glUniform3fv(ld_, 1, mLights[i].GetDirection(), 0);
			GLES20.glUniform4fv(lc_, 1, mLights[i].GetColor(), 0);
			GLES20.glUniform1f(lr_, mLights[i].GetRadius());
			GLES20.glUniform1f(lf_, mLights[i].GetFalloff());
		}
	}
}
