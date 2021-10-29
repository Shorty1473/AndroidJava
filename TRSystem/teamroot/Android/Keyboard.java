package teamroot.Android;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class Keyboard
{
	private final static int WindowFlags =
			View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
					| View.SYSTEM_UI_FLAG_FULLSCREEN
					| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
	
	private static View mShownRef = null;
	private static View mOwnerRef = null;
	private static InputMethodManager mIMM = null;
	private static boolean mShown = false;
	private static Window mOwnerWindow = null;
	
	public static void KeyboardSetup(Context context, Window owner)
	{
		if(mIMM == null)
			mIMM = (InputMethodManager)context.getSystemService(INPUT_METHOD_SERVICE);
		
		mOwnerWindow = owner;
	}
	
	public static void ShowKeyboard(View v, View owner)
	{
		if(v == null)
			return;
		
		if(owner == null)
			return;
		
		if(mOwnerRef == null)
			mOwnerRef = owner;
		
		final boolean vrf = v.requestFocus();
		
		mShownRef = v;
		mShown = true;
		
		//Log.d("OWG", "Request Focus: " + vrf);
		//Log.d("OWG", "isAcceptingText: " + imm.isAcceptingText());
		
		if (vrf)
		{
			//mWindow.getDecorView().setSystemUiVisibility(WindowFlags);
			//Log.d("OWG" , "ShowKeyboard.focus: " + v.findFocus());
			final boolean ssi = mIMM.showSoftInput(mShownRef, InputMethodManager.SHOW_IMPLICIT);
			//Log.d("OWG", "showSoftInput = " + ssi);
		}
	}
	
	public static void HideKeyboard()
	{
		if(mShownRef != null)
		{
			mIMM.hideSoftInputFromWindow(mShownRef.getWindowToken(), InputMethodManager.HIDE_IMPLICIT_ONLY);
			mShownRef.clearFocus();
			mShownRef = null;
			//Log.d("OWG", "mShownRef = " + mShownRef);
		}
		
		if(mOwnerRef == null)
			return;
		
		mShown = false;
		final boolean vrf = mOwnerRef.requestFocus();
		//Log.d("OWG", "vrf = " + vrf + " " + "isFocusable." + mOwnerRef.isFocusable());
		//Log.d("OWG" , "HideKeyboard.focus: " + mOwnerRef.findFocus());
		mOwnerWindow.getDecorView().setSystemUiVisibility(WindowFlags);
	}
	
	public static boolean IsShown()
	{
		return mShown;
	}
}
