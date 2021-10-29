package teamroot.Game.Base;

import java.io.InputStream;
import java.io.OutputStream;

public interface IGameState
{
	void StoreGameState(OutputStream os);
	void LoadGameState(InputStream is);
	
	String GetFileName();
}
