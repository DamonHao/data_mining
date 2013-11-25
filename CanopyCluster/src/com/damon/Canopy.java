package com.damon;
import java.util.ArrayList;
import java.util.List;
public class Canopy {
	private int id_;
	private DataVector centroid_;
	private ArrayList<DataVector> members_ = new ArrayList<DataVector>();
	public Canopy(int id, DataVector centroid){
		id_ = id;
		centroid_ = centroid;
		members_.add(centroid);
	}
	public void addMember(DataVector v){
		members_.add(v);
	}
	public List<DataVector> getMembers(){
		return members_;
	}
    @Override  
    public String toString() {
        return "Canopy{" +  
                "id=" + id_ +  
                ", centroid=" + centroid_+  
                ", members=" + members_ +  
                "}";  
    } 
}
