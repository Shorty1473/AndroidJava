package teamroot.OGL.Vertices;

import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IndexArray
{
	public final static int ElementSize = 4;
	
	private List<Integer> mElements;
	
	public IndexArray()
	{
		mElements = new ArrayList<>();
	}
	
	public IntBuffer BuildIndexBuffer()
	{
		IntBuffer nBuffer = IntBuffer.allocate(mElements.size() * ElementSize);
		final int count_ = mElements.size();
		
		for(int i = 0; i < count_; ++i)
			nBuffer.put(mElements.get(i));
		
		nBuffer.position(0);
		
		return nBuffer;
	}
	
	public int GetElementCount()
	{
		return mElements.size();
	}
	
	public void AddValue(final int Value)
	{
		mElements.add(Value);
	}
	
	public void AddArray(final int[] Values)
	{
		final int len_ = Values.length;
		
		for(int i = 0; i < len_; ++i)
			mElements.add(Values[i]);
	}
}
