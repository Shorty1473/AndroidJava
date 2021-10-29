package teamroot.Data.Store;

import java.nio.ByteBuffer;

public class FloatStore extends AStorable<Float>
{
	public FloatStore()
	{
		super(TYPE_FLOAT);
	}
	
	@Override
	public ByteBuffer WrapData()
	{
		final int l_ = Length() * TypeToBytes();
		
		ByteBuffer outBuffer = ByteBuffer.allocate(l_);
		
		for(int i = 0; i < Length(); ++i)
			outBuffer.putFloat(mData.get(i));
		
		return outBuffer;
	}
}
