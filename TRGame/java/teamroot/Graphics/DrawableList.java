package teamroot.Graphics;

import java.util.ArrayList;
import java.util.List;

import teamroot.Game.Camera;
import teamroot.GameObjects.ObjectWorld;
import teamroot.OGL.Base.DisplayBuffer;
import teamroot.OGL.Graphics.Texture;
import teamroot.OGL.IDisplay.IDrawBuffers;
import teamroot.OGL.IDisplay.IDrawable;
import teamroot.OGL.Shaders.Shader;
import teamroot.OGL.Shaders.ShaderHandles;

public class DrawableList
{
	private DisplayBuffer mBuffer;
	protected float[]mFinalMatrix;
	
	public static class DrawableItem implements IDrawable
	{
		private ObjectWorld mWorld;
		private Texture[] mTextures;
		
		public DrawableItem()
		{
			mWorld = new ObjectWorld();
			mTextures = new Texture[3];
			mTextures[0] = null;
			mTextures[1] = null;
			mTextures[2] = null;
		}
		
		@Override
		public IDrawBuffers GetBuffer()
		{
			return null;
		}
		
		@Override
		public void StartDraw(ShaderHandles Handles, Camera camera)
		{
		
		}
		
		@Override
		public void EndDraw()
		{
		
		}
	}
	
	private List<DrawableItem> mItems;
	
	public DrawableList()
	{
		mItems = new ArrayList<>();
	}
	
	public void DrawList(Shader shader, Camera camera)
	{
	
	}
}
