package com.lfr.planeblock.vo;

import java.util.ArrayList;

public class Plane {
	int x,y;
	int destination;
	ArrayList<PlaneItem> items;
	
	public final static int UP=0;
	public final static int DOWN=1;
	public final static int LEFT=2;
	public final static int RIGHT=3;
	/*飞机类的构造方法，根据定点坐标，以及朝向，创建一个飞机对象
	 * 
	 * 
	 * */
	public Plane(int _x,int _y,int _des){
		x=_x;
		y=_y;
		destination=_des;
		items=new ArrayList<PlaneItem>();
		for (int i = 0; i < 10; i++) {
			items.add(new PlaneItem(_x,_y,_des,i)); 			
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getDestination() {
		return destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}
	public ArrayList<PlaneItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<PlaneItem> items) {
		this.items = items;
	}
	

	
}