package com.slopez.swingy;

public class Vector2 {
	public double x;
	public double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double dot(Vector2 u) {
		return (this.x * u.x + this.y * u.y);
	}

	public void add(Vector2 u) {
		this.x += u.x;
		this.y += u.y;
	}

	public int hash() {
		return (int) x << 16 | (int) y;
	}
}
