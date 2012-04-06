package test;

import render3d.Point3f;

public class Test {
	public static void main(String[] args) {
		for (int i = 0; i < 91; i += 15) {
			Point3f p = new Point3f(0, 0.1f, 0);
			p.rotatePYR(i, -i, 0);
			System.out.print(p.x() + " " + p.y() + " " + p.z() + " // ");
			p = new Point3f(0.1f, 0, 0);
			p.rotatePYR(i, -i, 0);
			System.out.print(p.x() + " " + p.y() + " " + p.z() + "\n");
		}
	}
}
