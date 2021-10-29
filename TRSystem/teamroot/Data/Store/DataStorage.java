package teamroot.Data.Store;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class DataStorage
{
	private List<AStorable> mData;
	
	public DataStorage()
	{
		mData = new ArrayList<>();
	}
	
	public void AddObject(AStorable Object)
	{
		mData.add(Object);
	}
	
	public void WriteStorageToFile(OutputStream outputStream)
	{
		final int Length = mData.size();
		ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
		
		try
		{
			outputStream.write(sizeBuffer.putInt(Length).array());
			
			for(int i = 0; i < Length; ++i)
			{
				AStorable temp = mData.get(i);
				
				sizeBuffer.position(0);
				sizeBuffer.putInt(temp.Length());
				
				outputStream.write(temp.WrapData().array());
			}
		}
		catch (Exception e)
		{
			Log.d("Data.Store", "WriteStorage.Exception(" + e.getMessage() + ")");
		}
	}
	
	public void ReadStorageFromFile(InputStream inputStream)
	{
		try
		{
		
		}
		catch (Exception e)
		{
		
		}
	}
}
