package teamroot.Android.Base;

import android.content.Context;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import teamroot.OGL.ADisplay.AGameView;

public class GraphicsLayout extends FrameLayout
{
	private LinearLayout mBaseContainer;
	private List<TRTextInput> mInputs; //Holds references to views added
	
	private AGameView mGameView; //Holds reference to GLSurfaceView
	
	public GraphicsLayout(Context context)
	{
		super(context);
		
		mGameView = null;
		
		mInputs = new ArrayList<>();
		mBaseContainer = new LinearLayout(context);
		
		addView(mBaseContainer);
	}
	
	public void SetGameView(AGameView view)
	{
		if(mGameView != null)
		{
			removeView(mGameView);
			mGameView = null;
		}
		
		mGameView = view;
		addView(mGameView);
	}
	
	public AGameView GetGameView()
	{
		return mGameView;
	}
	
	public TRTextInput GetTextInput(final String InputID)
	{
		final int sz_ = mInputs.size();
		
		for(int i = 0; i < sz_; ++i)
		{
			if(mInputs.get(i).IsView(InputID))
				return mInputs.get(i);
		}
		
		return null;
	}
	
	public void AddInput(TRTextInput input)
	{
		mInputs.add(input);
		mBaseContainer.addView(input);
	}
}
