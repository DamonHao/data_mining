package com.damon;
import java.util.*;
public class KMeans {
	private int cluster_num_;
	private List<DataVector> data_;
	private int iteration_num_;
	private DistanceMeasure distance_measure_ = new DistanceMeasure(){	
		@Override
		public double getDist(DataVector v1, DataVector v2){
			double dist_square_sum = 0;
			int len = v1.getLenght();
			if(len != v2.getLenght()){
				System.out.println("Wrong data dimension");
				System.exit(1);
			}
			for(int i = 0; i < len; i++){
				dist_square_sum += Math.pow(v1.getCoordiante(i)-v2.getCoordiante(i),2); 
			}
			return Math.sqrt(dist_square_sum);
		}
	};
	
	public KMeans(int cluster_num, List<DataVector> data,int iteration_num){
		cluster_num_ = cluster_num;
		data_ = data;
		iteration_num_= iteration_num;
	}
	
	public KMeans(int cluster_num, List<DataVector> data,int iteration_num,DistanceMeasure distance_measure){
		cluster_num_ = cluster_num;
		data_ = data;
		iteration_num_= iteration_num;
		distance_measure_ = distance_measure;
	}
	
	//Assign centroids to each cluster;
	private List<Cluster> preprocessCluster(Set<DataVector> centroids){
		List<Cluster> clusters = new ArrayList<Cluster>();
		Iterator<DataVector> it_p = centroids.iterator();
		int id = 0;
		while(it_p.hasNext()){
			DataVector p = it_p.next();
			if(p.isSample()){//if p is sample data, need to add it to cluster member;
				Cluster c = new Cluster(id++,p,distance_measure_);
				c.addDataVector(p);
				clusters.add(c);
			}else{
				clusters.add(new Cluster(id++,p,distance_measure_));
			}
		}
		return clusters;
	}
	//Cluster data by centroids;
	private List<Cluster> clustering(Set<DataVector> centroids,List<Cluster> clusters){
		//convert to array
		//The below statement equal to DataVector centers [] = centroids.toArray(new DataVector[0]);
		DataVector centers [] = new DataVector[centroids.size()];
		centers = centroids.toArray(centers);
		TreeSet<Distance> dists = new TreeSet<Distance>();
		boolean flag = false;
		int data_size = data_.size();
		int cluster_num = clusters.size();
		for(int i = 0; i < data_size; i++){
			for(int j = 0; j < cluster_num; j++){
				DataVector source = data_.get(i);
				if(centroids.contains(source))
					break;
				flag = true;
				dists.add(new Distance(source,centers[j],distance_measure_));
			}
			if(flag){
				Distance min_dist = dists.first();
				for(int m = 0; m < clusters.size(); m++){
					if(clusters.get(m).getCentroid().equals(min_dist.getDest()))
						clusters.get(m).addDataVector(min_dist.getSource());
				}
				flag = false;
			}
			dists.clear();
		}
		return clusters;
	}
	//Choose centroids from data set Randomly 
	private Set<DataVector> centroidInit(){
		Set<DataVector> centroids = new HashSet<DataVector>();
		Random rand = new Random();
		int size = data_.size();
		while(centroids.size() < cluster_num_){
			int index = rand.nextInt(size);
			centroids.add(data_.get(index));
		}
		return centroids;
	}
	
	//Iterate to get the final clusters
	public List<Cluster> getClusters(){
		Set<DataVector> centroids = centroidInit();
		List<Cluster> clusters = clustering(centroids,preprocessCluster(centroids));
		Set<DataVector> new_centroids = new HashSet<DataVector>();
		for(int i = 0; i < iteration_num_; i++){
			int cluster_size = clusters.size();
			for(int j = 0; j < cluster_size; j++){
				List<DataVector> members = clusters.get(j).getMembers();
				int members_size = members.size();
				int vector_len = members.get(0).getLenght(); 
				double coordinate_sum [] = new double[vector_len];
				for(int m = 0; m < members_size; m++){
					for(int n = 0; n < vector_len; n++){
						coordinate_sum[n] += members.get(m).getCoordiante(n);
					}
				}
				for(int n = 0; n < vector_len; n++){
					coordinate_sum[n] /= members_size;
				}
				new_centroids.add(new DataVector(coordinate_sum,false));
			}
			//stop iteration if the centroids do not change; 
			if(new_centroids.containsAll(centroids))
				break;
			centroids = new_centroids;
			clusters = clustering(centroids,preprocessCluster(centroids));
		}
		return clusters;
	}

	public void PrintClusters(List<Cluster> clusters){
		int size = clusters.size();
		System.out.println("Clustering result:");
		for(int i = 0; i < size; i++){
			System.out.println(clusters.get(i));
		}
	}
	
	public static void main(String[] args) {
		/*
		ArrayList<DataVector> points = new ArrayList<DataVector>();
		points.add(new DataVector(new double[] {1,1}));
		points.add(new Point(2,1));
		points.add(new Point(8,8));
		points.add(new Point(8,9));
		points.add(new Point(9,8));
		points.add(new Point(9,9));
		points.add(new Point(15,2));
		points.add(new Point(2,2));
		*/
		double [][] points = {{1,1,1},{2,1,1},{8,8,1},{8,9,1},{9,8,1},{9,9,1},{1,2,1},{2,2,1}};
		ArrayList<DataVector> data_vectors = new ArrayList<DataVector>();
		for(int i = 0; i < points.length; i++){
			data_vectors.add(new DataVector(points[i]));
		}
		KMeans k_means = new KMeans(3,data_vectors,10);
		List<Cluster> clusters =  k_means.getClusters();
		k_means.PrintClusters(clusters);
	}

}

