package teamroot.OGL.Graphics;

import teamroot.OGL.Buffers.IndexBuffer;
import teamroot.OGL.Buffers.VertexBuffer;

public class Model
{
	public class Vertice
	{
	
	}
	
	private VertexBuffer mVertexBuffer;
	private IndexBuffer mIndexBuffer;
	
	public Model(final int VBID, final int IBID)
	{
		mVertexBuffer = new VertexBuffer(VBID);
		mIndexBuffer = new IndexBuffer(IBID);
	}
	
	public void PushVertice()
	{
	
	}
	
	public void PushIndice(final short Index)
	{
	
	}
}
