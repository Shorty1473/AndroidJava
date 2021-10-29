package teamroot.OGL.Shaders;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import teamroot.OGL.Base.DisplayBuffer;

public class ShaderHandles
{
	private List<VertexShaderInput> mVSInput;
	private List<FragmentShaderInput> mFSInput;
	
	public ShaderHandles()
	{
		mVSInput = new ArrayList<>();
		mFSInput = new ArrayList<>();
	}
	
	public void AddVertexHandle(final String Name, final int BufferOffset)
	{
		mVSInput.add(new VertexShaderInput(Name, BufferOffset));
	}
	
	public void AddFragmentHandle(final String Name)
	{
		mFSInput.add(new FragmentShaderInput(Name));
	}
	
	public void GetHandles(final int ShaderID)
	{
		final int vssize = mVSInput.size();
		final int fssize = mFSInput.size();
		
		for(int i = 0; i < vssize; ++i)
		{
			final int VSH = mVSInput.get(i).GetHandle(ShaderID);
			//Log.d("System.Graphics", "" + mVSInput.get(i).mName + " has handle: " + VSH);
		}
		
		for(int i = 0; i < fssize; ++i)
		{
			final int FSH = mFSInput.get(i).GetHandle(ShaderID);
			//Log.d("System.Graphics", "" + mFSInput.get(i).mName + " has handle: " + FSH);
		}
	}
	
	public void EnableVertexHandles(DisplayBuffer displayBuffer)
	{
		final int vssize = mVSInput.size();
		
		for(int i = 0; i < vssize; ++i)
		{
			mVSInput.get(i).Activate(displayBuffer);
		}
	}
	
	public void DisableVertexHandles()
	{
		final int vssize = mVSInput.size();
		
		for(int i = 0; i < vssize; ++i)
			mVSInput.get(i).Deactivate();
	}
	
	public int GetFragmentHandle(final String Name)
	{
		final int fssize = mFSInput.size();
		
		for(int i = 0; i < fssize; ++i)
		{
			if(mFSInput.get(i).mName.equals(Name))
				return mFSInput.get(i).mHandle;
		}
		
		return -2;
	}
}
