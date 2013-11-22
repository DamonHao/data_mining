package com.damon;
import java.util.*;
import java.text.DecimalFormat;

public class Cluster {
	private int id_;
	private DataVector centroid_;
	private ArrayList<DataVector> members_ = new ArrayList<DataVector>();
	private static DecimalFormat precision = new DecimalFormat("0.####");
	private DistanceMeasure compute_method_;
	
	public Cluster(int id, DataVector centroid,DistanceMeasure compute_method){
		id_ = id;
		centroid_ = new DataVector(centroid.getAllCoordiante(),false);
		compute_method_ = compute_method;
	}
	public void addDataVector(DataVector v){
		members_.add(v);
	}
	
	public DataVector getCentroid(){
		return centroid_;
	}
	public List<DataVector> getMembers(){
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
    	String str = "(";
    	int len = centroid_.getLenght();
    	for(int i = 0; i < len-1; i++)
    		str += precision.format(centroid_.getCoordiante(i))+",";
    	if(len > 0)
    		str += precision.format(centroid_.getCoordiante(len-1));
    	str += ")";
        return "Cluster{" +  
                "id=" + id_ +  
                ", centroid=" + str+  
                ", average radius="+precision.format(getAverageRadius())+
                ", members=" + members_ +  
                "}";  
    } 
}
