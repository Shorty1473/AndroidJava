package teamroot.OGL.Streams;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class ADataStream
{
	private static class Stream
	{
		private InputStream mStream;
		private String mStreamName;
		
		public Stream(InputStream is, final String s)
		{
			mStream = is;
			mStream.mark(0);
			
			mStreamName = s;
		}
		
		public void Overwrite(InputStream is)
		{
			try
			{
				mStream.close();
			}
			catch (Exception e)
			{
			
			}
			
			mStream = is;
			mStream.mark(0);
		}
		
		public InputStream GetStream()
		{
			try
			{
				mStream.reset();
				
			}catch (Exception e)
			{
			
			}
			
			return mStream;
		}
		
		public boolean IsStream(final String name)
		{
			return mStreamName.equals(name);
		}
	}
	
	protected List<Stream> mStream;
	
	public ADataStream()
	{
		mStream = new ArrayList<>();
	}
	
	private int FindStream(final String Name)
	{
		final int count_ = mStream.size();
		for(int i = 0; i < count_; ++i)
		{
			ADataStream.Stream str = mStream.get(i);
			
			if(str.IsStream(Name))
				return i;
		}
		
		return -1;
	}
	
	protected void AddStream(InputStream inputData, final String inputName)
	{
		int id_ = -1;
		if((id_ = FindStream(inputName)) != -1)
		{
			mStream.get(id_).Overwrite(inputData);
			return;
		}
		
		Stream nStream = new Stream(inputData, inputName);
		mStream.add(nStream);
	}
	
	public int GetStreamCount()
	{
		return mStream.size();
	}
	
	public InputStream GetStream(final int ID)
	{
		return mStream.get(ID).GetStream();
	}
	
	public String GetStreamName(final int ID)
	{
		return mStream.get(ID).mStreamName;
	}
	
	public InputStream GetStream(final String name)
	{
		final int id_ = FindStream(name);
		
		if(id_ != -1)
			return mStream.get(id_).GetStream();
		
		return null;
	}
}
