package teamroot.OGL.Streams;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.InputStream;

public class TextureStream extends ADataStream
{
	public TextureStream()
	{
		super();
	}
	
	public void AddTexture(AssetManager am, final String path, final String Name)
	{
		try
		{
			InputStream is = am.open(path);
			AddStream(is, Name);
			
		}catch (Exception e)
		{
			Log.d("System.Graphics", "AddTexture: " + e.getMessage());
		}
	}
}
