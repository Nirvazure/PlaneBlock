package com.lfr.planeblock.util;


import com.lfr.planeblock.vo.Plane;
import com.lfr.planeblock.vo.PlaneItem;

public class PlaneUtil {
	
	Plane plane;
	
	public PlaneUtil(Plane _palne) {
		plane=_palne;
	}
	
	/*如果可以插入，改变地图状态
	 * 
	 * 
	 * */
	public void insertToMap(int[][] _map){
		if(canInsertToMap(_map)){
			System.out.println("可以插入");
			
			MapUtil mUtil = new MapUtil(_map);
			mUtil.setStatus(plane.getItems().get(0).getxOfItem(), plane.getItems().get(0).getyOfItem(),2);
			for (int i1 = 1; i1 < plane.getItems().size(); i1++) {
				PlaneItem tempP=plane.getItems().get(i1);
				mUtil.setStatus(tempP.getxOfItem(), tempP.getyOfItem(),1);
			}
		}else{
			System.out.println("无法插入");
		}
	}
	/*检测飞机是否可以插入地图
	 * 
	 * 
	 * */
	public boolean canInsertToMap(int[][] _map){
		boolean temp=true;
		for (int i = 0; i < plane.getItems().size(); i++) {
			PlaneItem tempItem=plane.getItems().get(i);
			
			if (!new MapUtil(_map).canUse(tempItem.getxOfItem(), tempItem.getyOfItem())) {
				temp=false;
			}
		}
		return temp;
	}
	
	/*在plane的items表中，如果找到x，y坐标分别符合的items返回items
	 * 
	 * */
	public PlaneItem scanArrayTofindItem(int _x,int _y){
		PlaneItem tempItem=null;	//不符合的话返回空
		for (int i = 0; i <plane.getItems().size(); i++) {
			PlaneItem tempP=plane.getItems().get(i);
			if ((_x==tempP.getxOfItem())&&(_y==tempP.getyOfItem())) {
				tempItem=tempP;
			}
		}
		return tempItem;
	}

	public int isHurt(int _x,int _y){	//-1代表空，1代表伤，2代表沉
		int status=0;
		PlaneItem temp=scanArrayTofindItem(_x, _y);
		if (temp==null) {
			status=0;
		}else {
			if (temp.getId()==0) {
				status=2;
			}else {
				status=1;
			}
		}
		return status;
	}
	
}
