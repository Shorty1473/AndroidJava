package teamroot.OGL.Shaders;

import android.opengl.GLES20;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import teamroot.Game.Camera;
import teamroot.OGL.IDisplay.IDrawable;
import teamroot.OGL.IDisplay.IShader;
import teamroot.OGL.Streams.ShaderStream;

public class Shader implements IShader
{
	private String mVertexCode;
	private String mFragmentCode;
	
	private int mVertexID;
	private int mFragmentID;
	private int mProgramID;
	
	private ShaderHandles mHandles;
	
	public Shader()
	{
		mVertexID = -1;
		mFragmentID = -1;
		mProgramID = -1;
		
		mVertexID = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
		mFragmentID = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
		
		mHandles = new ShaderHandles();
	}
	
	@Override
	public void CompileVertexShader(ShaderStream ss)
	{
		InputStream is = ss.GetStream("VertexCode");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int nRead = 0;
		
		try
		{
			while ((nRead = is.read()) != -1)
				baos.write(nRead);
			
		}catch (Exception e)
		{
			Log.d("System.Graphics", "CompileVertexShader failed: " + e.getMessage());
		}
		
		mVertexCode = baos.toString();
		//Log.d("OWG", "VSCode\n" + mVertexCode);
		
		GLES20.glShaderSource(mVertexID, mVertexCode);
		GLES20.glCompileShader(mVertexID);
		Log.d("System.Graphics", "VSCompile status: " + GLES20.glGetShaderInfoLog(mVertexID));
	}
	
	@Override
	public void CompileFragmentShader(ShaderStream ss)
	{
		InputStream is = ss.GetStream("FragmentCode");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int nRead = 0;
		
		try
		{
			while ((nRead = is.read()) != -1)
				baos.write(nRead);
			
		}catch (Exception e)
		{
			Log.d("System.Graphics", "CompileFragmentShader failed: " + e.getMessage());
		}
		
		mFragmentCode = baos.toString();
		//Log.d("OWG", "FSCode\n" + mFragmentCode);
		
		GLES20.glShaderSource(mFragmentID, mFragmentCode);
		GLES20.glCompileShader(mFragmentID);
		Log.d("System.Graphics", "FSCompile status: " + GLES20.glGetShaderInfoLog(mFragmentID));
	}
	
	public void UseShader()
	{
		if(mProgramID == -1)
		{
			mProgramID = GLES20.glCreateProgram();
			GLES20.glAttachShader(mProgramID, mVertexID);
			GLES20.glAttachShader(mProgramID, mFragmentID);
			GLES20.glLinkProgram(mProgramID);
		}
		
		GLES20.glUseProgram(mProgramID);
		mHandles.GetHandles(mProgramID);
	}
	
	public void DrawObject(IDrawable Object, Camera camera)
	{
		if(mProgramID == -1)
			UseShader();
		
		Object.StartDraw(mHandles, camera);
		mHandles.EnableVertexHandles(Object.GetBuffer().GetBuffers());
		
		Object.EndDraw();
		mHandles.DisableVertexHandles();
	}
	
	public void AddVertexHandle(final String name, final int StrideOffset)
	{
		mHandles.AddVertexHandle(name,StrideOffset);
	}
	
	public void AddFragmentHandle(final String name)
	{
		mHandles.AddFragmentHandle(name);
	}
	
	public ShaderHandles GetHandleRef()
	{
		return mHandles;
	}
}
