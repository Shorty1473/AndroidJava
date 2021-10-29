package teamroot.Game;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import teamroot.Game.Base.IObjectState;

public class StateCollection
{
	List<IObjectState> mList;
	
	public StateCollection()
	{
		mList = new ArrayList<>();
	}
	
	public void AddState(IObjectState objectState)
	{
		mList.add(objectState);
	}
	
	public void WriteStates(OutputStream os)
	{
		try
		{
			final int len_ = mList.size();
			int totalSize = 0;
			
			for(int i = 0; i < len_; ++i)
				totalSize += mList.get(i).GetSizeInBytes();
			
			ByteBuffer buffer = ByteBuffer.allocate(totalSize);
			
			for(int i = 0; i < len_; ++i)
				mList.get(i).PushToBuffer(buffer);
			
			os.write(buffer.array());
		}
		catch (Exception e)
		{
			Log.d("System.Game.State", "WriteStates failed: " + e.getMessage());
			
			for(StackTraceElement ste: e.getStackTrace())
				Log.d("System.Game.State", "Trace: " + ste.toString());
		}
		finally
		{
		
		}
	}
	
	public void ReadStates(InputStream is)
	{
		try
		{
			final int len_ = mList.size();
			int totalSize = 0;
			
			for(int i = 0; i < len_; ++i)
				totalSize += mList.get(i).GetSizeInBytes();
			
			ByteBuffer buffer = ByteBuffer.allocate(totalSize);
			
			is.read(buffer.array());
			
			for(int i = 0; i < len_; ++i)
				mList.get(i).ReadFromBuffer(buffer);
			
		}
		catch (Exception e)
		{
			Log.d("System.Game.State", "ReadStates failed: " + e.getMessage());
			
			for(StackTraceElement ste: e.getStackTrace())
				Log.d("System.Game.State", "Trace: " + ste.toString());
		}
		finally
		{
		
		}
	}
}
