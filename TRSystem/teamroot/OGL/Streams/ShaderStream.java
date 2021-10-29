package teamroot.OGL.Streams;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;

public class ShaderStream extends ADataStream
{
	public ShaderStream()
	{
		super();
	}
	
	public void OpenVertexShader(AssetManager am, final String path)
	{
		try
		{
			InputStream is = am.open(path);
			AddStream(is, "VertexCode");
			
		}catch (Exception e)
		{
			Log.d("System.Graphics.Stream", "OpenVertexShader: " + e.getMessage());
		}
	}
	
	public void OpenFragmentShader(AssetManager am, final String path)
	{
		try
		{
			InputStream is = am.open(path);
			AddStream(is, "FragmentCode");
			
		}catch (Exception e)
		{
			Log.d("System.Graphics.Stream", "OpenFragmentShader: " + e.getMessage());
		}
	}
}
