package teamroot.Game;

import android.util.Log;

import java.io.InputStream;
import java.io.OutputStream;

import teamroot.Game.Base.IGameState;
import teamroot.Game.Base.IObjectState;

public class GameState implements IGameState
{
	private String mFileName;
	private StateCollection mStates;
	
	public GameState(final String name)
	{
		mFileName = name;
		mStates = new StateCollection();
	}
	
	public void AddToState(IObjectState NewObject)
	{
		mStates.AddState(NewObject);
	}
	
	@Override
	public void StoreGameState(OutputStream outputStream)
	{
		try
		{
			mStates.WriteStates(outputStream);
		}catch (Exception e)
		{
			Log.d("OWG", "StoreGameState(" + e.getMessage() + ")");
		}
	}
	
	@Override
	public void LoadGameState(InputStream inputStream)
	{
		try
		{
			mStates.ReadStates(inputStream);
		}catch (Exception e)
		{
			Log.d("OWG", "LoadGameState(" + e.getMessage() + ")");
		}
	}
	
	@Override
	public String GetFileName()
	{
		return mFileName;
	}
}
