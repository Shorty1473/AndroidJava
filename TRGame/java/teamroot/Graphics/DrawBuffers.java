package teamroot.Graphics;

import android.opengl.GLES20;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import teamroot.Math.Float2;
import teamroot.Math.Float3;
import teamroot.Math.Int3;
import teamroot.OGL.Base.DisplayBuffer;
import teamroot.OGL.IDisplay.IDrawBuffers;
import teamroot.OGL.Vertices.IndexArray;
import teamroot.OGL.Vertices.ModelVertice;
import teamroot.OGL.Vertices.VertexArray;

public class DrawBuffers implements IDrawBuffers
{
	protected DisplayBuffer mBuffer;
	
	public DrawBuffers()
	{
		mBuffer = null;
		mBuffer = new DisplayBuffer();
	}
	
	@Override
	public void LoadOBJModel(InputStream inputStream)
	{
		BufferedReader scn = new BufferedReader(new InputStreamReader(inputStream));
		
		String ln = null;
		String[] nfo = null;
		String[] fcs = null;
		
		VertexArray varr = new VertexArray();
		IndexArray iarr = new IndexArray();
		
		ArrayList<Float3> vertices = new ArrayList<>();
		ArrayList<Float2> textures = new ArrayList<>();
		ArrayList<Float3> normals = new ArrayList<>();
		ArrayList<Int3> faces = new ArrayList<>();
		
		ModelVertice[] modelVertices = null;
		
		try
		{
			while ((ln = scn.readLine()) != null)
			{
				nfo = ln.split(" ");
				
				if (nfo[0].compareTo("v") == 0)
				{
					Float3 vv = new Float3();
					
					vv.mValues[0] = Float.parseFloat(nfo[1]);
					vv.mValues[1] = Float.parseFloat(nfo[2]);
					vv.mValues[2] = Float.parseFloat(nfo[3]);
					
					vertices.add(vv);
				}
				
				if (nfo[0].compareTo("vt") == 0)
				{
					Float2 vv = new Float2();
					
					vv.mValues[0] = Float.parseFloat(nfo[1]);
					vv.mValues[1] = Float.parseFloat(nfo[2]);
					
					textures.add(vv);
				}
				
				if (nfo[0].compareTo("vn") == 0)
				{
					Float3 vv = new Float3();
					
					vv.mValues[0] = Float.parseFloat(nfo[1]);
					vv.mValues[1] = Float.parseFloat(nfo[2]);
					vv.mValues[2] = Float.parseFloat(nfo[3]);
					
					normals.add(vv);
				}
				
				if (nfo[0].compareTo("f") == 0)
				{
					for (int i = 0; i < (nfo.length - 1); ++i)
					{
						fcs = nfo[i + 1].split("/");
						//Log.d("System.Graphics", "nfo_" + (i + 1) + " = " + nfo[i + 1]);
						
						Int3 i3 = new Int3();
						
						i3.mValues[0] = Integer.parseInt(fcs[0]) - 1;
						i3.mValues[1] = Integer.parseInt(fcs[1]) - 1;
						i3.mValues[2] = Integer.parseInt(fcs[2]) - 1;
						
						faces.add(i3);
					}
				}
			}
		}
		catch (Exception e)
		{
		
		}
		
		modelVertices = new ModelVertice[faces.size()];
		
		for(int i = 0; i < faces.size(); ++i)
		{
			Int3 i3 = faces.get(i);
			Float3 v_ = vertices.get(i3.mValues[0]);
			Float2 vt_ = textures.get(i3.mValues[1]);
			Float3 vn_ = normals.get(i3.mValues[2]);
			
			modelVertices[i] = new ModelVertice();
			modelVertices[i].SetVertexPosition(v_.mValues[0], v_.mValues[1], (v_.mValues[2] * (-1.0f)));
			modelVertices[i].SetUVCoordinates(vt_.mValues[0], 1.0f - vt_.mValues[1]);
			modelVertices[i].SetNormalCoordinates(vn_.mValues[0], vn_.mValues[1], (vn_.mValues[2] * (-1.0f)));
		}
		
		for(int i = 0; i < (faces.size() / 3); ++i)
		{
			final int offset = (i * 3);
			
			iarr.AddValue((short)(offset));
			iarr.AddValue((short)(offset + 1));
			iarr.AddValue((short)(offset + 2));
			
			/*iarr.AddValue((short)(faces.get(offset).mValues[0]));
			iarr.AddValue((short)(faces.get(offset + 1).mValues[0]));
			iarr.AddValue((short)(faces.get(offset + 2).mValues[0]));*/
		}
		
		varr.AddArray(modelVertices);
		
		CreateVertexBuffer(varr);
		CreateIndexBuffer(iarr);
	}
	
	@Override
	public void CreateVertexBuffer(VertexArray vertices)
	{
		mBuffer.BuildVertexBuffer(vertices);
	}
	
	@Override
	public void CreateIndexBuffer(IndexArray indices)
	{
		mBuffer.BuildIndexBuffer(indices);
	}
	
	@Override
	public DisplayBuffer GetBuffers()
	{
		return mBuffer;
	}
	
	@Override
	public void StartDrawing()
	{
		mBuffer.BindBuffers();
	}
	
	@Override
	public void EndDrawing()
	{
		GLES20.glDrawElements(GLES20.GL_TRIANGLES, mBuffer.GetIndexCount(), GLES20.GL_UNSIGNED_INT, 0);
		mBuffer.UnbindBuffers();
	}
}
