package teamroot.OGL.Base;

import android.opengl.GLES20;
import android.util.Log;

import teamroot.OGL.Buffers.IndexBuffer;
import teamroot.OGL.Buffers.VertexBuffer;
import teamroot.OGL.Vertices.IndexArray;
import teamroot.OGL.Vertices.VertexArray;

public class DisplayBuffer
{
	private VertexBuffer m_vertexBuffer;
	private IndexBuffer m_indexBuffer;
	
	private int[] m_bufferID;
	
	public DisplayBuffer()
	{
		m_vertexBuffer = null;
		m_indexBuffer = null;
		
		m_bufferID = new int[2]; //Storage: [0] - VertexBuffer | [1] - IndexBuffer
		GLES20.glGenBuffers(2, m_bufferID,0);
		
		//Log.d("OWG", "Buffer[0]: "+ m_bufferID[0] + " | Buffer[1]: " + m_bufferID[1]);
	}
	
	public int GetStride()
	{
		if(m_vertexBuffer == null)
			return 0;
		
		return m_vertexBuffer.GetStride();
	}
	
	public void BuildVertexBuffer(VertexArray vertices)
	{
		m_vertexBuffer = new VertexBuffer(m_bufferID[0]);
		m_vertexBuffer.BuildBuffer(vertices);
	}
	
	public void BuildIndexBuffer(IndexArray indices)
	{
		m_indexBuffer = new IndexBuffer(m_bufferID[1]);
		m_indexBuffer.BuildBuffer(indices);
	}
	
	public void BindBuffers()
	{
		if(m_vertexBuffer != null)
			m_vertexBuffer.BindBuffer();
		
		if(m_indexBuffer != null)
			m_indexBuffer.BindBuffer();
	}
	
	public void UnbindBuffers()
	{
		if(m_vertexBuffer != null)
			m_vertexBuffer.UnbindBuffer();
		
		if(m_indexBuffer != null)
			m_indexBuffer.UnbindBuffer();
	}
	
	public int GetVertexCount()
	{
		return m_vertexBuffer.GetBufferCount();
	}
	
	public int GetIndexCount()
	{
		if(m_indexBuffer == null)
			return 0;
		
		return m_indexBuffer.GetBufferCount();
	}
}
