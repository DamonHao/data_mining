package com.damon;

public class Point {
	private double x_;
	private double y_;
	private boolean is_sample_;
	public Point(double x,double y){
		x_ = x; y_ = y; is_sample_ = true;
	}
	public Point(double x,double y,boolean is_sample){
		x_ = x; y_ = y; is_sample_ = is_sample;
	}
	public double getX(){return x_;}
	public double getY(){return y_;}
	public boolean isSample(){
		return is_sample_;
	}
    @Override  
    public boolean equals(Object o) {  
        if (this == o) return true;  
        if (o == null || getClass() != o.getClass()) return false;  
  
        Point point = (Point) o;  
  
        if (Double.compare(point.getX(), x_) != 0) return false;  
        if (Double.compare(point.getY(), y_) != 0) return false;  
  
        return true;  
    } 
    @Override  
    public String toString() {  
        return "("+x_+","+y_+")";  
    } 
	/*
	public boolean equalTo(Point p){
		if(this == p) return true;
		else return false;
	}
	*/
}
