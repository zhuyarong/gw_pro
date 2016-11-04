package com.jl.arithmetic;

import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.List;

public class PointCheck {
	// 方法一
	public boolean checkWithJdkGeneralPath(Point2D.Double point, List<Point2D.Double> polygon){
		
		GeneralPath p = new java.awt.geom.GeneralPath();
	   Point2D.Double first = polygon.get(0);
	   p.moveTo(first.x, first.y);
	   for (Point2D.Double d : polygon) {
	      p.lineTo(d.x, d.y);
	   }
	   p.lineTo(first.x, first.y);
	   p.closePath();
	   return p.contains(point);
	}

	// 方法二
	public boolean checkWithJdkPolygon(Point2D.Double point,
			List<Point2D.Double> polygon) {
		java.awt.Polygon p = new Polygon();
		// java.awt.geom.GeneralPath
		final int TIMES = 1000;
		for (Point2D.Double d : polygon) {
			int x = (int) d.x * TIMES;
			int y = (int) d.y * TIMES;
			p.addPoint(x, y);
		}
		int x = (int) point.x * TIMES;
		int y = (int) point.y * TIMES;
		return p.contains(x, y);
	}
}
