package teamroot.Game.Sounds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SoundPlayer
{
	private Random mRandom;
	private List<SoundSet> mSoundSets;
	
	public SoundPlayer()
	{
		mRandom = new Random();
		mSoundSets = new ArrayList<>();
	}
	
	public void AddSoundSet(SoundSet soundSet)
	{
		mSoundSets.add(soundSet);
	}
	
	public SoundSet GetSoundSet(final String setName)
	{
		final int sz_ = mSoundSets.size();
		
		for(int i = 0; i < sz_; ++i)
		{
			if(mSoundSets.get(i).CompareName(setName))
				return mSoundSets.get(i);
		}
		
		return null;
	}
	
	public void PlayRandom(final String setName)
	{
		final int sz_ = mSoundSets.size();
		
		for(int i = 0; i < sz_; ++i)
		{
			if(mSoundSets.get(i).CompareName(setName))
			{
				mSoundSets.get(i).PlayRandom(mRandom);
				return;
			}
		}
	}
	
	public void OnPaused()
	{
		final int sz_ = mSoundSets.size();
		for(int i = 0; i < sz_; ++i)
			mSoundSets.get(i).PauseAll();
	}
	
	public void OnResumed()
	{
		final int sz_ = mSoundSets.size();
		for(int i = 0; i < sz_; ++i)
			mSoundSets.get(i).Resume();
	}
	
	public void OnDestroy()
	{
		final int sz_ = mSoundSets.size();
		for(int i = 0; i < sz_; ++i)
			mSoundSets.get(i).OnDestroy();
	}
}
