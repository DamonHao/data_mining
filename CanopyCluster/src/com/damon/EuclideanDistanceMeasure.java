package com.damon;

public class EuclideanDistanceMeasure extends DistanceMeasure {
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
}
