package com.damon;
import java.util.*;

public class Cluster {
	private int id_;
	private Point centroid_;
	private ArrayList<Point> members_ = new ArrayList<Point>();
	
	public Cluster(int id, Point centroid){
		id_ = id;
		centroid_ = new Point(centroid.getX(),centroid.getY());
	}
	public void addPoint(Point p){
		members_.add(p);
	}
	
	public Point getCentroid(){
		return centroid_;
	}
	public List<Point> getMembers(){
		return members_;
	}
	public int getID(){
		return id_;
	}
	
    @Override  
    public String toString() {  
        return "Cluster{" +  
                "id=" + id_ +  
                ", center=" + centroid_ +  
                ", members=" + members_ +  
                "}";  
    } 
	/*
	public Cluster(Point p){
		centroid_ = new Point(p.x_,p.y_);
		points.add(p);
	}
	
	public double centroidDist(Point point){
		return Math.sqrt( (centroid_.x_-point.x_)*(centroid_.x_-point.x_) + 
				(centroid_.y_-point.y_)*(centroid_.y_-point.y_));
	}	
	public void addPoint(Point point){
		points.add(point);
		updateCentroid();
	}	
	public void updateCentroid(){
		double x_sum = 0;
		double y_sum = 0;
		int size = points.size();
		for(int i = 0; i < size; i++){
			x_sum +=  points.get(i).x_;
			y_sum += points.get(i).y_;
		}
		centroid_.x_ = x_sum/size;
		centroid_.y_ = y_sum/size;
	}
	public void printPoints(){
		 for(int i = 0; i < points.size();i++){
			 System.out.print("("+ points.get(i).x_+","+points.get(i).y_ +") ");
		 }
		 System.out.println();
	}
	*/
}
