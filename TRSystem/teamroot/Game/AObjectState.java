package teamroot.Game;

import java.nio.ByteBuffer;

import teamroot.Game.Base.IObjectState;

public abstract class AObjectState implements IObjectState
{
	private String mID;
	
	public AObjectState(String ID)
	{
		mID = ID;
	}
	
	//public abstract void S
	
	@Override
	public void PushToBuffer(ByteBuffer buffer)
	{
	
	}
	
	@Override
	public void ReadFromBuffer(ByteBuffer buffer)
	{
	
	}
}
