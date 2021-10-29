package teamroot.Data.Store;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public abstract class AStorable<Type>
{
	protected static final int TYPE_UNKNOWN = -1;
	protected static final int TYPE_INTEGER = 10;
	protected static final int TYPE_FLOAT = 11;
	protected static final int TYPE_SHORT = 12;
	protected static final int TYPE_CUSTOM = 100;
	
	protected int mByteSize;
	
	protected int TypeToBytes()
	{
		switch (mType)
		{
			case TYPE_FLOAT:
			case TYPE_INTEGER:
				mByteSize = 4;
				return 4;
				
			case TYPE_SHORT:
				mByteSize = 2;
				return 2;
				
			case TYPE_UNKNOWN:
				return 0;
				
			case TYPE_CUSTOM:
				return mByteSize;
		}
		
		return 0;
	}
	
	protected List<Type> mData;
	private int mPointer;
	private int mType;
	
	public AStorable(final int StoreType)
	{
		mData = new ArrayList<>();
		mType = StoreType;
	}
	
	public abstract ByteBuffer WrapData();
	
	public void Reset()
	{
		mPointer = 0;
	}
	
	public int Length()
	{
		return mData.size();
	}
	
	public int GetBytes()
	{
		return mByteSize;
	}
	
	public void Push(Type var)
	{
		mData.add(var);
	}
	
	public Type Get(boolean ResetOnEnd)
	{
		if(mPointer == mData.size())
		{
			if(ResetOnEnd)
				Reset();
			else
				return null;
		}
		
		return mData.get(mPointer++);
	}
	
	public Type Get()
	{
		return Get(false);
	}
}
