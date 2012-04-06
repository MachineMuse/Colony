package render3d;

public class Point3f {
	public static final Point3f up = new Point3f(0, 1, 0);

	private float x;
	private float y;
	private float z;

	public Point3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float x() {
		return x;
	}

	public float y() {
		return y;
	}

	public float z() {
		return z;
	}

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public void normalize() {
		double denominator = magnitude();
		this.x /= denominator;
		this.y /= denominator;
		this.z /= denominator;
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public float[] getArray() {
		float[] a = { x, y, z };
		return a;
	}

	public Point3f subtract(Point3f p) {
		return new Point3f(x - p.x, y - p.y, z - p.z);
	}

	public Point3f cross(Point3f p) {
		return new Point3f(y * p.z - z * p.y, z * p.x - x * p.z, x * p.y - y
				* p.x);
	}

	public void add(Point3f v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
	}

	public void scale(double mag) {
		this.x *= mag;
		this.y *= mag;
		this.z *= mag;
	}

	public void add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
	}

	public void rotatePYR(double pitch, double yaw, double roll) {
		if (pitch != 0) {
			pitch = Math.toRadians(pitch);
			double y1 = y * Math.cos(pitch) - z * Math.sin(pitch);
			double z1 = z * Math.cos(pitch) + y * Math.sin(pitch);
			this.y = (float) y1;
			this.z = (float) z1;
		}
		if (yaw != 0) {
			yaw = Math.toRadians(yaw);
			double x1 = x * Math.cos(yaw) + z * Math.sin(yaw);
			double z1 = z * Math.cos(yaw) - x * Math.sin(yaw);
			this.x = (float) x1;
			this.z = (float) z1;
		}
		if (roll != 0) {
			roll = Math.toRadians(roll);
			double x1 = x * Math.cos(roll) - y * Math.sin(roll);
			double y1 = y * Math.cos(roll) + x * Math.sin(roll);
			this.x = (float) x1;
			this.y = (float) y1;
		}
	}
}
