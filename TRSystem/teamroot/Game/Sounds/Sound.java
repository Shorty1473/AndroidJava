package teamroot.Game.Sounds;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.util.Log;

public class Sound
{
	public final static int STATE_STOPPED = 0;
	public final static int STATE_PLAYING = 1;
	public final static int STATE_FINISHED = 2;
	public final static int STATE_PAUSED = 3;
	public final static int STATE_WAITING = 4;
	
	private static class MediaExt extends android.media.MediaPlayer
	{
		private int mState;
		
		@Override
		public void stop()
		{
			mState = STATE_STOPPED;
			super.stop();
		}
		
		@Override
		public void pause()
		{
			if(mState == STATE_FINISHED || mState == STATE_STOPPED)
				return;
			
			mState = STATE_PAUSED;
			super.pause();
		}
		
		@Override
		public void start()
		{
			mState = STATE_PLAYING;
			super.start();
		}
		
		@Override
		public void reset()
		{
			mState = STATE_WAITING;
			super.reset();
		}
		
		public int GetState()
		{
			return mState;
		}
	}
	
	private MediaExt mSound;
	private String mSoundName;
	private SoundSet mParent;
	
	public Sound(final String SoundName)
	{
		mSoundName = SoundName;
		mSound = null;
		mParent = null;
	}
	
	public void LoadSound(final Context context, final String fileName)
	{
		mSound = new MediaExt();
		//mListener = new CompleteListener();
		
		AssetFileDescriptor afd = null;
		
		try
		{
			afd = context.getAssets().openFd(fileName);
			
			mSound.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
			
			mSound.prepare();
			//mSound.setOnCompletionListener(mListener);
			
		}catch (Exception e)
		{
			Log.d("System.Sound", "Unable to add sound: " + e.getMessage());
		}
	}
	
	public void SetParent(final SoundSet Parent)
	{
		mParent = Parent;
	}
	
	public void Play()
	{
		if(mSound.mState == STATE_FINISHED || mSound.mState == STATE_PAUSED)
			mSound.seekTo(0);
		
		if(mSound.mState == STATE_STOPPED)
		{
			try
			{
				mSound.prepare();
			}catch (Exception e)
			{
			
			}
			
			mSound.seekTo(0);
		}
		
		mSound.start();
	}
	
	public void Resume()
	{
		if(mSound.mState != STATE_PAUSED)
			return;
		
		//Log.d("Runner", "State: " + mPlayer.mState);
		mSound.start();
	}
	
	public void Pause()
	{
		mSound.pause();
	}
	
	public void Stop()
	{
		mSound.stop();
	}
	
	public boolean Compare(final String name)
	{
		return (mSoundName.compareTo(name) == 0);
	}
	
	public void Free()
	{
		mSound.release();
	}
}
