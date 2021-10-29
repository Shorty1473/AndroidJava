package teamroot.Android;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import teamroot.Android.Base.GraphicsLayout;
import teamroot.Game.Base.IGameState;
import teamroot.Game.LocalStorage;
import teamroot.Game.Sounds.SoundPlayer;
import teamroot.OGL.Base.AGameRenderer;
import teamroot.OGL.Base.GameView;

public abstract class AMainActivity extends Activity
{
	public static final int WindowFlags =
			View.SYSTEM_UI_FLAG_LAYOUT_STABLE
					| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
					| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	
	private Bundle mCacheRef;
	private GraphicsLayout mLayout;
	private GameView mOGLView;
	private TouchScreen mTouchScreen;
	
	private boolean mIsDestroyed;
	
	protected AGameRenderer mOGLRenderer;
	protected IGameState mGameState;
	protected SoundPlayer mSoundPlayer;
	
	public AMainActivity()
	{
		mCacheRef = null;
		mLayout = null;
		mOGLView = null;
		mOGLRenderer = null;
		mGameState = null;
		mTouchScreen = null;
		mSoundPlayer = null;
		
		mIsDestroyed = false;
	}
	
	protected abstract void CreateOGL();
	
	@Override
	protected void onCreate(Bundle savedState)
	{
		super.onCreate(savedState);
		mCacheRef = savedState;
		
		LocalStorage.SetDataPath(getExternalFilesDirs(null)[0].getAbsolutePath());
		LocalStorage.RetrieveGameState();
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		DisplayMetrics screenRect_ = new DisplayMetrics();
		//getWindow().getDecorView().getDisplay().getRealMetrics(screenRect_);
		
		mTouchScreen = new TouchScreen(screenRect_.widthPixels, screenRect_.heightPixels);
		mSoundPlayer = new SoundPlayer();
		
		mLayout = new GraphicsLayout(this);
		mOGLView = new GameView(this, mTouchScreen);
		
		CreateOGL();
		
		mLayout.SetGameView(mOGLView);
		mLayout.GetGameView().SetRenderer(mOGLRenderer);
		setContentView(mLayout);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus)
	{
		super.onWindowFocusChanged(hasFocus);
		Log.d("System.Android.Main", "onWindowFocusChanged()");
		
		if(hasFocus) getWindow().getDecorView().setSystemUiVisibility(WindowFlags);
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d("System.Android.Main", "onResume()");
		
		getWindow().getDecorView().setSystemUiVisibility(WindowFlags);
		
		if(mIsDestroyed)
		{
			mIsDestroyed = false;
			
			DisplayMetrics screenRect_ = new DisplayMetrics();
			getWindow().getDecorView().getDisplay().getRealMetrics(screenRect_);
			
			mTouchScreen = new TouchScreen(screenRect_.widthPixels, screenRect_.heightPixels);
			mSoundPlayer = new SoundPlayer();
			
			CreateOGL();
			
			mLayout.SetGameView(mOGLView);
			mLayout.GetGameView().SetRenderer(mOGLRenderer);
			setContentView(mLayout);
			
			return;
		}
		
		mSoundPlayer.OnResumed();
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		Log.d("System.Android.Main", "onPause()");
		
		mOGLRenderer.SaveGame();
		mSoundPlayer.OnPaused();
		//m_gameView.onPause();
	}
	
	@Override
	public void onStop()
	{
		super.onStop();
		Log.d("System.Android.Main", "onStop()");
	}
	
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		Log.d("System.Android.Main", "onDestroy()");
		
		Keyboard.HideKeyboard();
		
		mLayout = null;
		mOGLView = null;
		
		mOGLRenderer.SaveGame();
		mOGLRenderer = null;
		mGameState = null;
		
		mSoundPlayer.OnDestroy();
		mSoundPlayer = null;
		
		mIsDestroyed = true;
	}
	
	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		mOGLRenderer.SaveGame();
	}
}
