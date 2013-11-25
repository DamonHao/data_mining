package com.damon;

public class DataVector {
	private double [] coordinates_;
	private boolean is_sample_;
	
	//For sample data;
	public DataVector(double [] coordinates){
		//Copty to save storage
		coordinates_ = coordinates; 
		is_sample_ = true;
	}
	//For Centroid;
	public DataVector(double [] coordinates,boolean is_sample){
		if(!is_sample){//Clone because the coordinates of centroids will change;
			coordinates_ = coordinates.clone(); 
		}else{
			coordinates_ = coordinates;
		}
		is_sample_ = is_sample;
	}
	public double [] getAllCoordiante(){
		return coordinates_.clone();
	}
	public double getCoordiante(int i){return coordinates_[i];}
	public int getLenght(){
		return coordinates_.length;
	}
	public boolean isSample(){
		return is_sample_;
	}
	//Override the equals because if can be used for Set.contain() or Set.containAll()
    @Override  
    public boolean equals(Object o) {  
        if (this == o) return true;  
        if (o == null || getClass() != o.getClass()) return false;  
  
        DataVector data_vector = (DataVector) o;  
        int size = coordinates_.length;
        for(int i = 0; i < size; i++){
        	if(Double.compare(coordinates_[i], data_vector.getCoordiante(i)) != 0 )
        		return false;
        }
        return true;  
    } 
    @Override  
    public String toString() {  
    	String str = "(";
    	int len = coordinates_.length;
    	for(int i = 0; i < len-1; i++)
    		str += coordinates_[i]+",";
    	if(len > 0)
    		str += coordinates_[len-1];
    	str += ")";
        return str;  
    } 
}
