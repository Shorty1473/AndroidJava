package teamroot.OGL.Graphics;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import teamroot.OGL.Streams.TextureStream;

public class TextureCollection
{
	private List<Texture> mTextures;
	
	public TextureCollection()
	{
		mTextures = new ArrayList<>();
	}
	
	public void LoadTextures(TextureStream textureStream)
	{
		final int len_ = textureStream.GetStreamCount();
		
		for(int i = 0; i < len_; ++i)
		{
			Texture nTexture = new Texture(textureStream.GetStreamName(i));
			nTexture.SetTexture(textureStream.GetStream(i));
			
			mTextures.add(nTexture);
			//Log.d("System.Graphics", "LoadTexture(" + i + ")");
		}
	}
	
	public Texture GetTexture(final String Name)
	{
		final int size_ = mTextures.size();
		
		for(int i = 0; i < size_; ++i)
		{
			if(mTextures.get(i).IsTexture(Name))
				return mTextures.get(i);
		}
		
		return null;
	}
}
