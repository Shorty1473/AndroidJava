package teamroot.OGL.IDisplay;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

import teamroot.Android.TouchScreen;

public interface IRenderer extends GLSurfaceView.Renderer
{
	int OnTouchEvent(MotionEvent e);
	void AssignTouchScreen(TouchScreen touchScreen);
}
