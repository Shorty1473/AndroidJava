package teamroot.Game.Base;

public interface ITouchable
{
	void SetPosition(final float X, final float Y);
	void SetSize(final float Width, final float Height);
	boolean IsTouched(final float X, final float Y);
}
