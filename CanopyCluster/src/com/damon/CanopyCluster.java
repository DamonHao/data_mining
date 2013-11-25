package com.damon;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class CanopyCluster {
	private List<DataVector> data_;
	private double big_threshold_;
	private double small_threshold_;
	private DistanceMeasure distance_measure_;
	
	public CanopyCluster(List<DataVector> points, DistanceMeasure measure, double big_threshold, double small_threshold){
		data_ = points;
		big_threshold_ = big_threshold;
		small_threshold_ = small_threshold;
		distance_measure_ = measure;
	}
	
	public List<Canopy> createCanopies(){
		List<Canopy> canopies = new ArrayList<Canopy>();
		int id = 0;
		while(!data_.isEmpty()){
			DataVector cur_center = data_.get(0);
			Canopy canopy = new Canopy(id,cur_center);
			data_.remove(0);
			Iterator<DataVector> it = data_.iterator();
			List<DataVector> remove_data = new ArrayList<DataVector>();
			while(it.hasNext()){
				DataVector point = it.next();
				double dist = distance_measure_.getDist(cur_center, point);
				if(dist <= small_threshold_){
					canopy.addMember(point);
					//data_.remove(point);
					remove_data.add(point);
				}else if(dist <= big_threshold_){
					canopy.addMember(point);
				}
			}
			canopies.add(canopy);
			data_.removeAll(remove_data);
			id++;
		}
		return canopies;
	}
	
	public void PrintCanopies(List<Canopy> canopies){
		int size = canopies.size();
		System.out.println("Clustering result:");
		for(int i = 0; i < size; i++){
			System.out.println(canopies.get(i));
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//double [][] points = {{1,1,1},{2,1,1},{8,8,1},{8,9,1},{9,8,1},{9,9,1},{1,2,1},{2,2,1}};
		double [][] points = {{1,1},{2,1},{8,8},{8,9},{9,8},{9,9},{1,2},{2,2}};
		ArrayList<DataVector> data_vectors = new ArrayList<DataVector>();
		for(int i = 0; i < points.length; i++){
			data_vectors.add(new DataVector(points[i]));
		}
		CanopyCluster canopy_cluster = new CanopyCluster(data_vectors,new EuclideanDistanceMeasure(),4,1);
		canopy_cluster.PrintCanopies(canopy_cluster.createCanopies());
	}

}
