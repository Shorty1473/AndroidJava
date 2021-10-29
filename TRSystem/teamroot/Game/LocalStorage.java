package teamroot.Game;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import teamroot.Game.Base.IGameState;

public class LocalStorage
{
	private static List<File> mSaveData = null;
	private static String mDataPath;
	private static boolean mHasChange;
	
	public static void SetDataPath(final String Path)
	{
		if(Path.equals(mDataPath))
			return;
		
		mDataPath = Path;
		mHasChange = true;
	}
	
	public static void RetrieveGameState()
	{
		if(mSaveData == null)
			mSaveData = new ArrayList<>();
		
		if(mHasChange)
		{
			final String path = mDataPath + "/";
			
			File tPath = new File(path);
			Collections.addAll(mSaveData, tPath.listFiles());
			
			mHasChange = false;
		}
	}
	
	public static void LoadGameState(IGameState state)
	{
		if(mSaveData == null)
			return;
		
		File temp = new File(mDataPath + "/" + state.GetFileName() + ".txt");
		mSaveData.add(temp);
		
		try
		{
			InputStream is = new FileInputStream(temp);
			
			state.LoadGameState(is);
			is.close();
			
		}catch (Exception e)
		{
		
		}
	}
	
	public static void SaveGameState(IGameState state)
	{
		if(mSaveData == null)
			mSaveData = new ArrayList<>();
		
		File temp = new File(mDataPath + "/" + state.GetFileName() + ".txt");
		mSaveData.add(temp);
		
		//Log.d("OWG", "FileName: " + state.GetFileName());
		
		try
		{
			OutputStream os = new FileOutputStream(temp.getAbsoluteFile());;
			state.StoreGameState(os);
			
			os.close();
			
			mHasChange = true;
			
		}catch (Exception e)
		{
			Log.d("OWG", "Exception: " + e.getMessage());
		}
	}
}
