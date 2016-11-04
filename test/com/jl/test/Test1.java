package com.jl.test;

import java.awt.Polygon;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
	public static void main(String[] args) {
		Point2D.Double point = new Point2D.Double(1, 9);
		List<Point2D.Double> polygon = new ArrayList<Point2D.Double>();
		Point2D.Double point1 = new Point2D.Double(0, 10);
		Point2D.Double point2 = new Point2D.Double(10, 5);
		Point2D.Double point3 = new Point2D.Double(0, 5);
		Point2D.Double point4 = new Point2D.Double(0, 0);
		polygon.add(point1);
		polygon.add(point2);
		polygon.add(point3);
		polygon.add(point4);
		System.out.println(new Test1().checkWithJdkGeneralPath(point,polygon));
		System.out.println(new Test1().checkWithJdkPolygon(point,polygon));
	}
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

	// /**
	// * 检查多边形是否包含了某点~
	// * @param point
	// * @return
	// */
	// public boolean containsPoint(BYPoint point) {
	// int verticesCount = vertices.size();
	// int nCross = 0;
	// for (int i = 0; i < verticesCount; ++ i) {
	// BYPoint p1 = vertices.get(i);
	// BYPoint p2 = vertices.get((i + 1) % verticesCount);
	//
	// // 求解 y=p.y 与 p1 p2 的交点
	// if ( p1.getY() == p2.getY() ) { // p1p2 与 y=p0.y平行
	// continue;
	// }
	// if ( point.getY() < Math.min(p1.getY(), p2.getY()) ) { // 交点在p1p2延长线上
	// continue;
	// }
	// if ( point.getY() >= Math.max(p1.getY(), p2.getY()) ) { // 交点在p1p2延长线上
	// continue;
	// }
	// // 求交点的 X 坐标
	// float x = (point.getY() - p1.getY()) * (p2.getX() - p1.getX())
	// / (p2.getY() - p1.getY()) + p1.getX();
	// if ( x > point.getX() ) { // 只统计单边交点
	// nCross++;
	// }
	// }
	// // 单边交点为偶数，点在多边形之外
	// return (nCross%2==1);
	// }
}
