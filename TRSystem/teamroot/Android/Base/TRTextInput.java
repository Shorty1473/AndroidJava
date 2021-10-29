package teamroot.Android.Base;

import android.content.Context;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import teamroot.Android.Keyboard;

class DoneListener implements TextView.OnEditorActionListener
{
	@Override
	public boolean onEditorAction(TextView textView, int keyCode, KeyEvent keyEvent)
	{
		if(keyCode == EditorInfo.IME_ACTION_DONE)
		{
			Keyboard.HideKeyboard();
			return true;
		}
		
		return false;
	}
}

public class TRTextInput extends EditText
{
	private TextType mType;
	private String mSID;
	
	private DoneListener mOnDone;
	
	public TRTextInput(Context context, final String inputID)
	{
		super(context);
		SetInput(TextType.TEXT);
		mSID = inputID;
		
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		mOnDone = new DoneListener();
		setOnEditorActionListener(mOnDone);
	}
	
	public String GetID()
	{
		return mSID;
	}
	
	public boolean IsView(TRTextInput compare)
	{
		return mSID.equals(compare.mSID);
	}
	
	public boolean IsView(final String ID)
	{
		return mSID.equals(ID);
	}
	
	public void SetInput(final TextType tt)
	{
		mType = tt;
		
		switch (mType)
		{
			case TEXT:
				setInputType(InputType.TYPE_CLASS_TEXT);
				return;
			
			case EMAIL:
				setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
				return;
			
			case NUMBERS:
				setInputType(InputType.TYPE_CLASS_NUMBER);
				return;
			
			case PASSWORD:
				setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
				return;
		}
		
		return;
	}
	
	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event)
	{
		if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
		{
			if (hasFocus())
				Keyboard.HideKeyboard();
		}
		
		return super.onKeyPreIme(keyCode, event);
	}
}
