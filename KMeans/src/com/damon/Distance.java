package com.damon;

public class Distance implements Comparable<Distance> {
	private DataVector source_;
	private DataVector dest_;
	private double dist_;
	private DistanceMeasure distance_measure_ = null;
	public Distance(DataVector source, DataVector dest,DistanceMeasure distance_measure){
		source_ = source;
		dest_ = dest;
		distance_measure_ = distance_measure;
		dist_ = distance_measure_.getDist(source_,dest_);
	}
	
	public double getDist(){
		return dist_;
	}
	
	public DataVector getSource(){
		return source_;
	}
	
	public DataVector getDest(){
		return dest_;
	}
	
	@Override
	public int compareTo(Distance x){
		if(dist_ < x.getDist())
			return -1;
		else return 1;

	}
};


