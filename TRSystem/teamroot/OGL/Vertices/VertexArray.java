package teamroot.OGL.Vertices;

import android.util.Log;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VertexArray
{
	private List<AVertex> mVertices;
	
	public VertexArray()
	{
		mVertices = new ArrayList<>();
	}
	
	public void AddValue(AVertex vtx)
	{
		mVertices.add(vtx);
	}
	
	public void AddArray(AVertex[]vtx)
	{
		final int len_ = vtx.length;
		
		mVertices.addAll(Arrays.asList(vtx).subList(0, len_));
	}
	
	private float[] GetArray()
	{
		final int count_ = mVertices.size();
		final int size_ = mVertices.get(0).GetElementCount();
		
		float[]out = new float[count_ * size_];
		for(int i = 0; i <count_; ++i)
		{
			final int offset = (i * size_);
			
			for(int j = 0; j < size_; ++j)
			{
				out[offset + j] = mVertices.get(i).mElements[j];
				//Log.d("OWG")
			}
		}
		
		return out;
	}
	
	public FloatBuffer BuildFloatBuffer()
	{
		FloatBuffer nBuffer = FloatBuffer.allocate(mVertices.size() * mVertices.get(0).GetStride());
		nBuffer.put(GetArray());
		nBuffer.position(0);
		
		//Log.d("OWG", "Size: " + mVertices.size() + " | Stride: " + mVertices.get(0).GetStride());
		
		return nBuffer;
	}
	
	public int GetStride()
	{
		return mVertices.get(0).GetStride();
	}
}
