package com.damon;
import java.util.*;
import java.text.DecimalFormat;

public class Cluster {
	private int id_;
	private Point centroid_;
	private ArrayList<Point> members_ = new ArrayList<Point>();
	private static DecimalFormat precision = new DecimalFormat("0.####");
	private ComputeMethod compute_method_;
	
	public Cluster(int id, Point centroid,ComputeMethod compute_method){
		id_ = id;
		centroid_ = new Point(centroid.getX(),centroid.getY());
		compute_method_ = compute_method;
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
	
	public double getAverageRadius(){
		if(compute_method_ == null){
			System.out.println("getAverageRadius Error");
			System.exit(1);
		}
		int members_size = members_.size();
		double radius_sum = 0;
		for(int i = 0; i < members_size; i++){
			radius_sum +=compute_method_.getDist(centroid_, members_.get(i));
		}
		return radius_sum/members_size;
	}
	
    @Override  
    public String toString() { 
        return "Cluster{" +  
                "id=" + id_ +  
                ", centroid=" + "("+ precision.format(centroid_.getX())+","
                + precision.format(centroid_.getY())+")"+  
                ", average radius="+precision.format(getAverageRadius())+
                ", members=" + members_ +  
                "}";  
    } 
}
