package render3d;

public class Matrix16f {
	private float values[];

	public Matrix16f() {
		setValues(new float[16]);
	}

	public float[] getValues() {
		return values;
	}

	public void setValues(float values[]) {
		this.values = values;
	}

}
