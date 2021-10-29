package teamroot.Game.Sounds;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundSet
{
	private String mSetName;
	private List<Sound> mSounds;
	private int mActiveIndex;
	private Context mContext;
	
	public SoundSet(final Context context, final String SetName)
	{
		mSetName = SetName;
		mContext = context;
		
		mActiveIndex = 0;
		mSounds = new ArrayList<>();
	}
	
	public void AddSound(final String fileName, final String soundName)
	{
		Sound nsound_ = new Sound(soundName);
		nsound_.LoadSound(mContext, fileName);
		nsound_.SetParent(this);
		
		mSounds.add(nsound_);
	}
	
	public boolean CompareName(final String name)
	{
		return (mSetName.compareTo(name) == 0);
	}
	
	public void PlayNext()
	{
		//Log.d("System.Sound", "PlayNext()");
		mActiveIndex = (++mActiveIndex) % mSounds.size();
		mSounds.get(mActiveIndex).Play();
	}
	
	public void PlayRandom(final Random random)
	{
		if(mSounds.size() < 2)
			return;
		
		int tempIndex = random.nextInt(mSounds.size());
		
		while(tempIndex == mActiveIndex)
			tempIndex = random.nextInt(mSounds.size());
		
		mActiveIndex = tempIndex;
		mSounds.get(mActiveIndex).Play();
	}
	
	public void PlaySound(final String soundName)
	{
		final int sz_ = mSounds.size();
		//Log.d("System.Sound", "PlaySound()");
		
		for(int i = 0; i < sz_; ++i)
		{
			Sound tmp = mSounds.get(i);
			
			if(tmp.Compare(soundName))
			{
				tmp.Play();
				return;
			}
		}
	}
	
	public void PauseAll()
	{
		final int sz_ = mSounds.size();
		Log.d("System.Sound", "PauseAll()");
		
		for(int i = 0; i < sz_; ++i)
			mSounds.get(i).Pause();
	}
	
	public void Resume()
	{
		final int sz_ = mSounds.size();
		Log.d("System.Sound", "Resume()");
		
		for(int i = 0; i < sz_; ++i)
			mSounds.get(i).Resume();
	}
	
	public void StopAll()
	{
		final int sz_ = mSounds.size();
		Log.d("System.Sound", "StopAll()");
		
		for(int i = 0; i < sz_; ++i)
			mSounds.get(i).Stop();
	}
	
	public void OnDestroy()
	{
		final int sz_ = mSounds.size();
		Log.d("System.Sound", "OnDestroy()");
		
		for(int i = 0; i < sz_; ++i)
			mSounds.get(i).Free();
	}
}
