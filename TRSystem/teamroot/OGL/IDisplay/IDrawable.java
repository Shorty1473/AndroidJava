package teamroot.OGL.IDisplay;

import teamroot.Game.Camera;
import teamroot.OGL.Shaders.ShaderHandles;

public interface IDrawable
{
	IDrawBuffers GetBuffer();
	
	void StartDraw(ShaderHandles Handles, Camera camera);
	void EndDraw();
}
