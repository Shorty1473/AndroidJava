package teamroot.Data.Store;


import java.nio.ByteBuffer;

public class IntegerStore extends AStorable<Integer>
{
	public IntegerStore()
	{
		super(TYPE_INTEGER);
	}
	
	@Override
	public ByteBuffer WrapData()
	{
		final int l_ = Length() * TypeToBytes();
		
		ByteBuffer outBuffer = ByteBuffer.allocate(l_);
		
		for(int i = 0; i < Length(); ++i)
			outBuffer.putInt(mData.get(i));
		
		return outBuffer;
	}
}
