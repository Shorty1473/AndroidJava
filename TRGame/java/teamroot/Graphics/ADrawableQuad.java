package teamroot.Graphics;

import teamroot.Game.Camera;
import teamroot.Game.Base.ITouchable;
import teamroot.Math.Float3;
import teamroot.OGL.Shaders.ShaderHandles;
import teamroot.OGL.Vertices.IndexArray;
import teamroot.OGL.Vertices.UIVertice;
import teamroot.OGL.Vertices.VertexArray;

public abstract class ADrawableQuad extends ADrawable implements ITouchable
{
	protected float FFSize = 0.5f;
	protected float[] mColor;
	
	@Override
	public void SetPosition(final float X, final float Y)
	{
		GetWorld().GetPosition().mValues[0] = X;
		GetWorld().GetPosition().mValues[1] = Y;
	}
	
	@Override
	public void SetSize(final float Width, final float Height)
	{
		GetWorld().GetScale().mValues[0] = Width;
		GetWorld().GetScale().mValues[1] = Height;
	}
	
	@Override
	public boolean IsTouched(final float X, final float Y)
	{
		final Float3 pos = GetWorld().GetPosition();
		final Float3 siz = GetWorld().GetScale();
		
		final boolean OnX = ((X >= (pos.mValues[0] - (siz.mValues[0] / 2.0f))) && (X <= (pos.mValues[0] + (siz.mValues[0] / 2.0f))));
		final boolean OnY = ((Y >= (pos.mValues[1] - (siz.mValues[1] / 2.0f))) && (Y <= (pos.mValues[1] + (siz.mValues[1] / 2.0f))));
		
		//Log.d("Game.Graphics", "Pos(" + pos.mValues[0] + ", " + pos.mValues[1] + ")");
		//Log.d("Game.Graphics", "Siz(" + siz.mValues[0] + ", " + siz.mValues[1] + ")");
		//Log.d("Game.Graphics", "Touch(" + X + ", " + Y + ") = " + (OnX && OnY));
		
		return (OnX && OnY);
	}
	
	public ADrawableQuad()
	{
		super();
		
		mBuffersRef = new DrawBuffers();
		
		mColor = new float[4];
		mColor[0] = 1.0f;
		mColor[1] = 0.5f;
		mColor[2] = 0.2f;
		mColor[3] = 1.0f;
	}
	
	public void SetColor(final float Red, final float Green, final float Blue, final float Alpha)
	{
		mColor[0] = Red;
		mColor[1] = Green;
		mColor[2] = Blue;
		mColor[3] = Alpha;
	}
	
	@Override
	protected abstract void SetUniformValues(ShaderHandles Handles, Camera camera);
	
	public void CreateQuad()
	{
		VertexArray varr = new VertexArray();
		UIVertice[] vertices = new UIVertice[4];
		
		final float TXRep = Math.max((FFSize / 10.0f), 1.0f);
		
		vertices[0] = new UIVertice();
		vertices[0].SetVertexPosition(-FFSize, FFSize, 0.0f);
		vertices[0].SetUVCoordinates(TXRep, 0.0f);
		
		vertices[1] = new UIVertice();
		vertices[1].SetVertexPosition(-FFSize, -FFSize, 0.0f);
		vertices[1].SetUVCoordinates(TXRep, TXRep);
		
		vertices[2] = new UIVertice();
		vertices[2].SetVertexPosition(FFSize, -FFSize, 0.0f);
		vertices[2].SetUVCoordinates(0.0f, TXRep);
		
		vertices[3] = new UIVertice();
		vertices[3].SetVertexPosition(FFSize, FFSize, 0.0f);
		vertices[3].SetUVCoordinates(0.0f, 0.0f);
		
		varr.AddValue(vertices[0]);
		varr.AddValue(vertices[1]);
		varr.AddValue(vertices[2]);
		varr.AddValue(vertices[3]);
		
		mBuffersRef.CreateVertexBuffer(varr);
		
		IndexArray iarr = new IndexArray();
		iarr.AddValue((short)0);
		iarr.AddValue((short)1);
		iarr.AddValue((short)2);
		iarr.AddValue((short)0);
		iarr.AddValue((short)2);
		iarr.AddValue((short)3);
		
		mBuffersRef.CreateIndexBuffer(iarr);
	}
	
	/*@Override
	public void CreateVertexBuffer()
	{
		VertexArray varr = new VertexArray();
		UIVertice[] vertices = new UIVertice[4];
		
		final float TXRep = Math.max((FFSize / 10.0f), 1.0f);
		
		vertices[0] = new UIVertice();
		vertices[0].SetVertexPosition(-FFSize, FFSize, 0.0f);
		vertices[0].SetUVCoordinates(TXRep, 0.0f);
		
		vertices[1] = new UIVertice();
		vertices[1].SetVertexPosition(-FFSize, -FFSize, 0.0f);
		vertices[1].SetUVCoordinates(TXRep, TXRep);
		
		vertices[2] = new UIVertice();
		vertices[2].SetVertexPosition(FFSize, -FFSize, 0.0f);
		vertices[2].SetUVCoordinates(0.0f, TXRep);
		
		vertices[3] = new UIVertice();
		vertices[3].SetVertexPosition(FFSize, FFSize, 0.0f);
		vertices[3].SetUVCoordinates(0.0f, 0.0f);
		
		varr.AddValue(vertices[0]);
		varr.AddValue(vertices[1]);
		varr.AddValue(vertices[2]);
		varr.AddValue(vertices[3]);
		
		mBuffersRef.CreateVertexBuffer(varr);
	}
	
	@Override
	public void CreateIndexBuffer()
	{
		IndexArray iarr = new IndexArray();
		iarr.AddValue((short)0);
		iarr.AddValue((short)1);
		iarr.AddValue((short)2);
		iarr.AddValue((short)0);
		iarr.AddValue((short)2);
		iarr.AddValue((short)3);
		
		mBuffersRef.CreateIndexBuffer(iarr);
	}*/
}
