package com.lfr.planeblock.vo;

import com.lfr.planeblock.util.PlaneItemUtil;

public class PlaneItem {
	int xOfItem,yOfItem;
	int des;
	int idOfItem;
	
	public final static int HEAD=0;
	public final static int LWING=1;
	public final static int LINWING=2;
	public final static int CWING=3;
	public final static int RINWING=4;
	public final static int RWING=5;
	public final static int BODY=6;
	public final static int LTAIL=7;
	public final static int CTAIL=8;
	public final static int RTAIL=9;
	
	public PlaneItem(int _x,int _y,int _des,int _idOfItem){
		xOfItem=_x;
		yOfItem=_y;
		des=_des;
		idOfItem=_idOfItem;
		new PlaneItemUtil(this).selectFunction(des, idOfItem);
	}
	
	public int getxOfItem() {
		return xOfItem;
	}

	public void setxOfItem(int xOfItem) {
		this.xOfItem = xOfItem;
	}

	public int getyOfItem() {
		return yOfItem;
	}

	public void setyOfItem(int yOfItem) {
		this.yOfItem = yOfItem;
	}

	public int getDes() {
		return des;
	}

	public void setDes(int des) {
		this.des = des;
	}

	public int getIdOfItem() {
		return idOfItem;
	}

	public void setIdOfItem(int idOfItem) {
		this.idOfItem = idOfItem;
	}

	public int getId(){
		return idOfItem;
	}
	
 	
}
