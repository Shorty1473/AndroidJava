package teamroot.OGL.IDisplay;

import java.io.InputStream;

import teamroot.OGL.Base.DisplayBuffer;
import teamroot.OGL.Vertices.IndexArray;
import teamroot.OGL.Vertices.VertexArray;

public interface IDrawBuffers
{
	void LoadOBJModel(InputStream inputStream);
	void CreateVertexBuffer(VertexArray vertices);
	void CreateIndexBuffer(IndexArray indices);
	
	DisplayBuffer GetBuffers();
	
	void StartDrawing();
	void EndDrawing();
}
