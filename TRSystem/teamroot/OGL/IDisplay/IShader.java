package teamroot.OGL.IDisplay;

import teamroot.OGL.Streams.ShaderStream;

public interface IShader
{
	void CompileVertexShader(ShaderStream ss);
	void CompileFragmentShader(ShaderStream ss);
}
