package teamroot.Game.Base;

import java.nio.ByteBuffer;

public interface IObjectState
{
	void PushToBuffer(ByteBuffer buffer);
	void ReadFromBuffer(ByteBuffer buffer);
	
	int GetSizeInBytes();
}
