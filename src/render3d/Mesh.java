package render3d;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;

public class Mesh {
	private float[] data;
	private int[] indices;

	private int vstride;
	private int cstride;
	private int nstride;
	private int tstride;

	private int mode;

	public Mesh() {
		mode = GL11.GL_TRIANGLES;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void translate(Point3f translation) {
		int stride = vstride + nstride + cstride + tstride;
		for (int i = 0; i < data.length; i += stride) {
			data[i] += translation.x();
			data[i + 1] += translation.y();
			data[i + 2] += translation.z();
		}
	}

	//
	// public void MakeSphere() { // TODO
	// final int ratio = 10;
	// for (int z = 0; z < ratio; z++) {
	// for (int r = 0; r < ratio; r++) {
	// float angle = (float) (Math.PI * 2 * r / ratio);
	// float width = (float) Math.sqrt(25 - (5 - z) * (5 - z));
	// Point3f p = new Point3f((float) Math.sin(angle) * width,
	// (float) Math.cos(angle) * width, z);
	// // points.add(p);
	// if (z > 1) {
	// // indices.add(r + z * 10);
	// }
	// }
	// }
	// }

	public void makePointsColoursIndices() {
	}

	public void MakeBox() {
		mode = GL11.GL_QUADS;
		vstride = 3;
		nstride = 0;
		cstride = 4;
		tstride = 0;

		float[] vertices = {
				-100, -100, -100, 1, 1, 0, .1f,
				-100, -100, 100, 1, 1, 1, .1f,
				-100, 100, -100, 1, 0, 0, .1f,
				-100, 100, 100, 1, 0, 1, .1f,
				100, -100, -100, 0, 1, 0, 1,
				100, -100, 100, 0, 1, 1, 1,
				100, 100, -100, 0, 0, 0, 1,
				100, 100, 100, 0, 0, 1, 1
		};
		data = vertices;

		int ix[] = {
				0, 1, 3, 2,
				2, 3, 7, 6,
				6, 7, 5, 4,
				4, 5, 1, 0,
				0, 2, 6, 4,
				5, 7, 3, 1
		};
		indices = ix;

	}

	//
	// public void MakeQuad() {
	// mode = GL11.GL_QUADS;
	// makePointsColoursIndices();
	//
	// colours.add(new Point3f(0.5f, 0.5f, 1.0f));
	//
	// points.add(new Point3f(100, 100, 0f));
	// points.add(new Point3f(300, 100, 0f));
	// points.add(new Point3f(300, 300, 0f));
	// points.add(new Point3f(100, 300, 0f));
	//
	// indices = new ArrayList<Integer>();
	// for (int i = 0; i < 4; i++) {
	// indices.add(i);
	// }
	//
	// }

	public void MakeOctahedron() {
		mode = GL11.GL_TRIANGLES;
		vstride = 3;
		nstride = 0;
		cstride = 4;
		tstride = 0;

		float vertices[] = {
				0f, 0f, -5f, 0f, 0f, 5f, 1.0f,
				-5f, 0f, 0f, 5f, 0f, 0f, 1.0f,
				0f, -5f, 0f, 0f, 5f, 0f, 1.0f,
				5f, 0f, 0f, 5f, 0f, 0f, 1.0f,
				0f, 5f, 0f, 0f, 5f, 0f, 1.0f,
				0f, 0f, 5f, 0f, 0f, 5f, 0.5f };
		data = vertices;

		int indexes[] = {
				0, 1, 2,
				0, 2, 3,
				0, 3, 4,
				0, 4, 1,
				5, 2, 1,
				5, 3, 2,
				5, 4, 3,
				5, 1, 4
		};
		indices = indexes;
	}

	public void draw() {
		FloatBuffer databuffer = BufferUtils.createFloatBuffer(data.length);
		databuffer.put(data);
		databuffer.rewind();

		IntBuffer indexbuffer = BufferUtils.createIntBuffer(indices.length);
		indexbuffer.put(indices);
		indexbuffer.rewind();

		int stridebytes = (vstride + nstride + cstride + tstride) * 4;

		int offset = 0;
		if (vstride > 0) {
			GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
			GL11.glVertexPointer(vstride, stridebytes, databuffer);
			offset += vstride;
		} else {
			GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
		}
		if (nstride > 0) {
			databuffer.position(offset);
			GL11.glEnableClientState(GL11.GL_NORMAL_ARRAY);
			GL11.glNormalPointer(stridebytes, databuffer);
			offset += nstride;
		} else {
			GL11.glDisableClientState(GL11.GL_NORMAL_ARRAY);

		}
		if (cstride > 0) {
			databuffer.position(offset);
			GL11.glEnableClientState(GL11.GL_COLOR_ARRAY);
			GL11.glColorPointer(cstride, stridebytes, databuffer);
			offset += cstride;
		} else {
			GL11.glDisableClientState(GL11.GL_COLOR_ARRAY);

		}
		if (tstride > 0) {
			databuffer.position(offset);
			GL11.glEnableClientState(GL11.GL_TEXTURE_COORD_ARRAY);
			GL11.glTexCoordPointer(tstride, stridebytes, databuffer);
			offset += tstride;
		} else {
			GL11.glDisableClientState(GL11.GL_TEXTURE_COORD_ARRAY);

		}
		databuffer.rewind();

		GL11.glDrawElements(mode, indexbuffer);
	}
}
