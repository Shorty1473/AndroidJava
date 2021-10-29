package teamroot.OGL.Base;

import android.content.Context;
import android.opengl.GLES20;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import teamroot.Game.Camera;
import teamroot.Game.Base.IGameState;
import teamroot.Game.GameState;
import teamroot.Game.LocalStorage;
import teamroot.OGL.ADisplay.ARenderer;
import teamroot.OGL.Graphics.Texture;
import teamroot.OGL.Graphics.TextureCollection;
import teamroot.OGL.Shaders.Shader;
import teamroot.OGL.Streams.ShaderStream;
import teamroot.OGL.Streams.TextureStream;

public abstract class AGameRenderer extends ARenderer
{
	private TextureStream mRawTextures;
	private ShaderStream mShaderCode;
	
	protected TextureCollection mOGLTextures;
	protected GameState mGameState;
	
	protected int mScreenWidth;
	protected int mScreenHeight;
	
	protected abstract void OGLCreateResources();
	
	protected void AddTexture(final String assetPath, final String assetName)
	{
		mRawTextures.AddTexture(mContext.getAssets(), assetPath, assetName);
	}
	
	protected Texture GetTexture(final String assetName)
	{
		return mOGLTextures.GetTexture(assetName);
	}
	
	protected void ClearSurface(final boolean clearColor, final boolean clearDepth)
	{
		int ClearValue = 0;
		
		if(clearColor) ClearValue |= GLES20.GL_COLOR_BUFFER_BIT;
		if(clearDepth) ClearValue |= GLES20.GL_DEPTH_BUFFER_BIT;
		
		GLES20.glClearColor(0.4f, 0.3f, 0.4f, 1.0f);
		GLES20.glClear(ClearValue);
	}
	
	protected void SetDepthTest(final boolean useDepth)
	{
		if(useDepth) GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		else GLES20.glDisable(GLES20.GL_DEPTH_TEST);
		
		GLES20.glDepthFunc(GLES20.GL_LEQUAL);
		GLES20.glDepthMask(true);
	}
	
	protected void SetBlending(final boolean useBlending)
	{
		if(useBlending) GLES20.glEnable(GLES20.GL_BLEND);
		else GLES20.glDisable(GLES20.GL_BLEND);
		
		GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	protected Shader LoadShaderCode(final String vertexShaderPath, final String fragmentShaderPath)
	{
		mShaderCode.OpenVertexShader(mContext.getAssets(), vertexShaderPath);
		mShaderCode.OpenFragmentShader(mContext.getAssets(), fragmentShaderPath);
		
		Shader out_ = new Shader();
		out_.CompileVertexShader(mShaderCode);
		out_.CompileFragmentShader(mShaderCode);
		
		return out_;
	}
	
	public AGameRenderer(final Context context, final IGameState gameState)
	{
		super(context);
		mGameState = (GameState)gameState;
		
		mRawTextures = new TextureStream();
		mOGLTextures = new TextureCollection();
		
		mShaderCode = new ShaderStream();
	}
	
	public void LoadGame()
	{
		LocalStorage.LoadGameState(mGameState);
	}
	
	public void SaveGame()
	{
		LocalStorage.SaveGameState(mGameState);
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig)
	{
		SetDepthTest(true);
		SetBlending(true);
		ClearSurface(true, true);
		
		mOGLTextures.LoadTextures(mRawTextures);
		OGLCreateResources();
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl10, int i, int i1)
	{
		mScreenWidth = i;
		mScreenHeight = i1;
		
		//Log.d("tc_app", "W: " + i + " | H: " + i1);
		GLES20.glViewport(0, 0, i, i1);
		mTouchScreen.OnScreenChange(mScreenWidth, mScreenHeight);
	}
	
	public abstract void onDrawFrame(GL10 gl10);
}
