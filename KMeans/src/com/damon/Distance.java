package com.damon;

public class Distance implements Comparable<Distance> {
	private Point source_;
	private Point dest_;
	private double dist_;
	private ComputeMethod compute_method_ = null;
	public Distance(Point source, Point dest,ComputeMethod compute_method){
		source_ = source;
		dest_ = dest;
		compute_method_ = compute_method;
		dist_ = compute_method_.getDist(source_,dest_);
	}
	
	public double getDist(){
		return dist_;
	}
	
	public Point getSource(){
		return source_;
	}
	
	public Point getDest(){
		return dest_;
	}
	
	@Override
	public int compareTo(Distance x){
		if(dist_ < x.getDist())
			return -1;
		else return 1;

	}
};


