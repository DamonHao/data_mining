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
	//Override the equals because if can be used for Set.contain() or Set.containAll()
    @Override  
    public boolean equals(Object o) {  
        if (this == o) return true;  
        if (o == null || getClass() != o.getClass()) return false;  
  
        Point point = (Point) o;  
  
        if (Double.compare(point.getX(), x_) != 0) return false;  
        if (Double.compare(point.getY(), y_) != 0) return false;  
  
        return true;  
    } 
    
	/* 
	public boolean equalTo(Point p){
		if(this == p) return true;
		else return false;
	}
	*/
    @Override  
    public String toString() {  
        return "("+x_+","+y_+")";  
    } 
    
    /*
    @Override  
    public int hashCode() {  
        int result;  
        long temp;  
        temp = x_ != +0.0d ? Double.doubleToLongBits(x_) : 0L;  
        result = (int) (temp ^ (temp >>> 32));  
        temp = y_ != +0.0d ? Double.doubleToLongBits(y_) : 0L;  
        result = 31 * result + (int) (temp ^ (temp >>> 32));  
        return result;  
    } 
    */
}
