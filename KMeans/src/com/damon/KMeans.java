package com.damon;
import java.util.*;
public class KMeans {
	private int cluster_num_;
	private List<Point> data_;
	private int iteration_num_;
	private ComputeMethod compute_method_ = new ComputeMethod(){
		@Override
		public double getDist(Point p1, Point p2){
			return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
		}
	};
	
	public KMeans(int cluster_num, List<Point> data,int iteration_num){
		cluster_num_ = cluster_num;
		data_ = data;
		iteration_num_= iteration_num;
	}
	//
	private List<Cluster> preprocessCluster(Set<Point> centroids){
		List<Cluster> clusters = new ArrayList<Cluster>();
		Iterator<Point> it_p = centroids.iterator();
		int id = 0;
		while(it_p.hasNext()){
			Point p = it_p.next();
			if(p.isSample()){//if p is sample data, need to add it to cluster member;
				Cluster c = new Cluster(id++,p,compute_method_);
				c.addPoint(p);
				clusters.add(c);
			}else{
				clusters.add(new Cluster(id++,p,compute_method_));
			}
		}
		return clusters;
	}
	//Cluster data by centroids;
	private List<Cluster> clustering(Set<Point> centroids,List<Cluster> clusters){
		//convert to array
		//The below statement equal to Point centers [] = centroids.toArray(new Point[0]);
		Point centers [] = new Point[centroids.size()];
		centers = centroids.toArray(centers);
		TreeSet<Distance> dists = new TreeSet<Distance>();
		boolean flag = false;
		int data_size = data_.size();
		int cluster_num = clusters.size();
		for(int i = 0; i < data_size; i++){
			for(int j = 0; j < cluster_num; j++){
				Point source = data_.get(i);
				if(centroids.contains(source))
					break;
				flag = true;
				dists.add(new Distance(source,centers[j],compute_method_));
			}
			if(flag){
				Distance min_dist = dists.first();
				for(int m = 0; m < clusters.size(); m++){
					if(clusters.get(m).getCentroid().equals(min_dist.getDest()))
						clusters.get(m).addPoint(min_dist.getSource());
				}
				flag = false;
			}
			dists.clear();
		}
		return clusters;
	}
	
	private Set<Point> centroidInit(){
		Set<Point> centroids = new HashSet<Point>();
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
		Set<Point> centroids = centroidInit();
		List<Cluster> clusters = clustering(centroids,preprocessCluster(centroids));
		Set<Point> new_centroids = new HashSet<Point>();
		for(int i = 0; i < iteration_num_; i++){
			int cluster_size = clusters.size();
			for(int j = 0; j < cluster_size; j++){
				List<Point> members = clusters.get(j).getMembers();
				int members_size = members.size();
				double x_sum = 0; double y_sum = 0;
				for(int m = 0; m < members_size; m++){
					x_sum +=  members.get(m).getX();
					y_sum += members.get(m).getY();
				}
				new_centroids.add(new Point(x_sum/members_size,y_sum/members_size,false));
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
	
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(new Point(1,1));
		points.add(new Point(2,1));
		points.add(new Point(8,8));
		points.add(new Point(8,9));
		points.add(new Point(9,8));
		points.add(new Point(9,9));
		points.add(new Point(15,2));
		points.add(new Point(2,2));
		
		KMeans k_means = new KMeans(2,points,10);
		List<Cluster> clusters =  k_means.getClusters();
		k_means.PrintClusters(clusters);
	}

}

