package teamroot.Graphics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

import teamroot.Math.Float3;
import teamroot.OGL.Vertices.IndexArray;
import teamroot.OGL.Vertices.ModelVertice;
import teamroot.OGL.Vertices.UIVertice;
import teamroot.OGL.Vertices.VertexArray;

public class BufferFactory
{
	public static void CreateQuadY(DrawBuffers drawBuffers, final float FFSize)
	{
		VertexArray varr = new VertexArray();
		ModelVertice[] vertices = new ModelVertice[4];
		
		final float TXRep = Math.max((FFSize / 10.0f), 1.0f);
		
		vertices[0] = new ModelVertice();
		vertices[0].SetVertexPosition(-FFSize, FFSize, 0.0f);
		vertices[0].SetUVCoordinates(TXRep, 0.0f);
		vertices[0].SetNormalCoordinates(0, 0, 0);
		
		vertices[1] = new ModelVertice();
		vertices[1].SetVertexPosition(-FFSize, -FFSize, 0.0f);
		vertices[1].SetUVCoordinates(TXRep, TXRep);
		vertices[1].SetNormalCoordinates(0, 0, 0);
		
		vertices[2] = new ModelVertice();
		vertices[2].SetVertexPosition(FFSize, -FFSize, 0.0f);
		vertices[2].SetUVCoordinates(0.0f, TXRep);
		vertices[2].SetNormalCoordinates(0, 0, 0);
		
		vertices[3] = new ModelVertice();
		vertices[3].SetVertexPosition(FFSize, FFSize, 0.0f);
		vertices[3].SetUVCoordinates(0.0f, 0.0f);
		vertices[3].SetNormalCoordinates(0, 0, 0);
		
		varr.AddValue(vertices[0]);
		varr.AddValue(vertices[1]);
		varr.AddValue(vertices[2]);
		varr.AddValue(vertices[3]);
		
		drawBuffers.CreateVertexBuffer(varr);
		
		IndexArray iarr = new IndexArray();
		iarr.AddValue((short)0);
		iarr.AddValue((short)1);
		iarr.AddValue((short)2);
		iarr.AddValue((short)0);
		iarr.AddValue((short)2);
		iarr.AddValue((short)3);
		
		drawBuffers.CreateIndexBuffer(iarr);
	}
	
	public static void CreateQuadZ(DrawBuffers drawBuffers, final float FFSize)
	{
		VertexArray varr = new VertexArray();
		ModelVertice[] vertices = new ModelVertice[4];
		
		final float TXRep = Math.max((FFSize / 10.0f), 1.0f);
		
		vertices[0] = new ModelVertice();
		vertices[0].SetVertexPosition(-FFSize, 0.0f, FFSize);
		vertices[0].SetUVCoordinates(TXRep, 0.0f);
		vertices[0].SetNormalCoordinates(0, 0, 0);
		
		vertices[1] = new ModelVertice();
		vertices[1].SetVertexPosition(-FFSize, 0.0f, -FFSize);
		vertices[1].SetUVCoordinates(TXRep, TXRep);
		vertices[1].SetNormalCoordinates(0, 0, 0);
		
		vertices[2] = new ModelVertice();
		vertices[2].SetVertexPosition(FFSize, 0.0f, -FFSize);
		vertices[2].SetUVCoordinates(0.0f, TXRep);
		vertices[2].SetNormalCoordinates(0, 0, 0);
		
		vertices[3] = new ModelVertice();
		vertices[3].SetVertexPosition(FFSize, 0.0f, FFSize);
		vertices[3].SetUVCoordinates(0.0f, 0.0f);
		vertices[3].SetNormalCoordinates(0, 0, 0);
		
		varr.AddValue(vertices[0]);
		varr.AddValue(vertices[1]);
		varr.AddValue(vertices[2]);
		varr.AddValue(vertices[3]);
		
		drawBuffers.CreateVertexBuffer(varr);
		
		IndexArray iarr = new IndexArray();
		iarr.AddValue((short)0);
		iarr.AddValue((short)1);
		iarr.AddValue((short)2);
		iarr.AddValue((short)0);
		iarr.AddValue((short)2);
		iarr.AddValue((short)3);
		
		drawBuffers.CreateIndexBuffer(iarr);
	}
	
	private static float GetHeight(int inColor)
	{
		int R = 0, G = 0, B = 0, A = 0;
		
		A = Color.alpha(inColor);
		R = Color.red(inColor);
		G = Color.green(inColor);
		B = Color.blue(inColor);
		
		return ((((float)(R + G + B)) / 3.0f) - 128.0f) / 12.8f;
	}
	
	private static class VertexWeights
	{
		private int mSumCount;
		private Float3 mNormalSum;
		
		public VertexWeights()
		{
			mSumCount = 0;
			mNormalSum = new Float3();
		}
		
		public void AddNormal(final Float3 in)
		{
			++mSumCount;
			mNormalSum = mNormalSum.Add(in);
		}
		
		public Float3 GetCalculated()
		{
			mNormalSum = mNormalSum.Div((float)mSumCount);
			mNormalSum.Normalize();
			
			return mNormalSum;
		}
	}
	
	private static void CalculateNormals(ModelVertice[] vertices, int[] indices, final int RowSize)
	{
		final int IndexCount = indices.length;
		int IOffset = 0;
		
		VertexWeights[] weights = new VertexWeights[vertices.length];
		
		for(int i = 0; i < vertices.length; ++i)
			weights[i] = new VertexWeights();
		
		for(int i = 0; i < (IndexCount / 6); ++i)
		{
			IOffset = (i * 6);
			
			int i1 = indices[IOffset + 0];
			int i2 = indices[IOffset + 1];
			int i3 = indices[IOffset + 2];
			int i4 = indices[IOffset + 5];
			
			Float3 in1 = new Float3(vertices[i1].GetPosition());
			Float3 in2 = new Float3(vertices[i2].GetPosition());
			Float3 in3 = new Float3(vertices[i3].GetPosition());
			Float3 in4 = new Float3(vertices[i4].GetPosition());
			
			Float3 U1 = new Float3(in2), V1 = new Float3(in3);
			U1 = U1.Sub(in1);
			V1 = V1.Sub(in1);
			
			Float3 U2 = new Float3(in2), V2 = new Float3(in3);
			U2 = U2.Sub(in4);
			V2 = V2.Sub(in4);
			
			weights[i1].AddNormal(new Float3(U1).Cross(V1));
			
			weights[i2].AddNormal(new Float3(U1).Cross(V1));
			//weights[i2].AddNormal(new Float3(U2).Cross(V2));
			
			weights[i3].AddNormal(new Float3(U1).Cross(V1));
			//weights[i3].AddNormal(new Float3(U2).Cross(V2));
			
			weights[i4].AddNormal(new Float3(U1).Cross(V1));
			
			/*Float3[] PVals = new Float3[3];
			PVals[0] = in2.Sub(in1);
			PVals[1] = in3.Sub(in1);
			PVals[2] = in4.Sub(in1);
			
			Float3 T1 = new Float3(PVals[0]), T2 = new Float3(PVals[1]);
			T1 = T1.Cross(PVals[1]);
			T2 = T2.Cross(PVals[2]);
			
			Float3 sum_ = new Float3(T1);
			sum_ = sum_.Add(T2).Mul(0.5f);
			
			weights[i1].AddNormal(T1);
			weights[i2].AddNormal(sum_);
			weights[i3].AddNormal(sum_);
			weights[i4].AddNormal(T2);*/
		}
		
		for(int i = 0; i < vertices.length; ++i)
		{
			Float3 ccw = weights[i].GetCalculated();
			//ccw.mValues[1] *= -1.0f;
			
			vertices[i].SetNormalCoordinates(ccw.mValues[0], ccw.mValues[1], ccw.mValues[2]);
		}
	}
	
	public static void GenerateHeightmap(DrawBuffers drawBuffers, Bitmap bmp, final float Scaling, final float TextureScaling)
	{
		int W = bmp.getWidth();
		int H = bmp.getHeight();
		
		final int VertexCount =  (W * H);
		final int IndexCount = ((W - 1) * (H - 1)) * 6;
		
		ModelVertice[] vertices = new ModelVertice[VertexCount];
		int[] indices = new int[IndexCount];
		
		int IOffset = 0;
		
		VertexArray varr = new VertexArray();
		IndexArray iarr = new IndexArray();
		
		float X_ = 0, Y_ = 0, Z_ = 0;
		float U_ = 0, V_ = 0;
		
		int[] colors = new int[W * H];
		bmp.getPixels(colors, 0, W, 0, 0, W, H);
		
		for(int i = 0; i < VertexCount; ++i)
		{
			X_ = ((float)(i % W)) * Scaling;
			Y_ = GetHeight(colors[i]);
			Z_ = ((float)H - (float)(i / H)) * Scaling;
			
			U_ = (((float)(i % W)) / W) * TextureScaling;
			V_ = (((float)(i / H)) / H) * TextureScaling;
			
			vertices[i] = new ModelVertice();
			vertices[i].SetVertexPosition(X_, Y_, Z_);
			vertices[i].SetUVCoordinates(U_, V_);
			
			Float3 temp = new Float3(X_, Y_, Z_);
			temp.Normalize();
			
			vertices[i].SetNormalCoordinates(temp.mValues[0], temp.mValues[1], temp.mValues[2]);
		}
		
		int VOffset = 0;
		final int RowSize = W;
		
		for(int i = 0; i < (IndexCount / 6); ++i)
		{
			VOffset = (i % (W - 1)) + ((i / (W - 1)) * W);
			IOffset = (i * 6);
			
			indices[IOffset + 0] = (VOffset);
			indices[IOffset + 1] = (VOffset + 1);
			indices[IOffset + 2] = (VOffset + RowSize);
			
			indices[IOffset + 3] = (VOffset + 1);
			indices[IOffset + 4] = (VOffset + RowSize);
			indices[IOffset + 5] = (VOffset + RowSize + 1);
		}
		
		CalculateNormals(vertices, indices, RowSize);
		
		varr.AddArray(vertices);
		iarr.AddArray(indices);
		
		drawBuffers.CreateVertexBuffer(varr);
		drawBuffers.CreateIndexBuffer(iarr);
	}
}
