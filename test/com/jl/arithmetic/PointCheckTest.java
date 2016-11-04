package com.jl.arithmetic;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PointCheckTest {
	// checkWithJdkPolygon
	// checkWithJdkGeneralPath
	@Test
	public void checkWithJdkPolygon() {
		PointCheck pointCheck = new PointCheck();
		Point2D.Double point = new Point2D.Double(5, 5);
		Point2D.Double point1 = new Point2D.Double(20, 5);
		List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
		polygon.add(new Point2D.Double(0, 0));
		polygon.add(new Point2D.Double(10, 0));
		polygon.add(new Point2D.Double(15, 3));
		polygon.add(new Point2D.Double(10, 8));
		polygon.add(new Point2D.Double(0, 10));

		boolean sign = pointCheck.checkWithJdkGeneralPath(point, polygon);
		boolean sign1 = pointCheck.checkWithJdkGeneralPath(point1, polygon);
		assert sign;
		assert !sign1;
		System.out.println(sign);
		System.out.println(sign1);
	}

	public void checkWithJdkGeneralPath() {
		PointCheck pointCheck = new PointCheck();
		Point2D.Double point = new Point2D.Double(5, 5);
		Point2D.Double point1 = new Point2D.Double(20, 5);
		List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
		polygon.add(new Point2D.Double(0, 0));
		polygon.add(new Point2D.Double(10, 0));
		polygon.add(new Point2D.Double(15, 3));
		polygon.add(new Point2D.Double(10, 8));
		polygon.add(new Point2D.Double(0, 10));

		boolean sign = pointCheck.checkWithJdkPolygon(point, polygon);
		boolean sign1 = pointCheck.checkWithJdkPolygon(point1, polygon);
		assert sign;
		assert !sign1;
		System.out.println(sign1);
	}

}
