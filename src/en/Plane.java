package en;

import java.util.ArrayList;

public class Plane {
	int x,y;
	int destination;
	ArrayList<PlaneItem> items;
	
	final static int UP=0;
	final static int DOWN=1;
	final static int LEFT=2;
	final static int RIGHT=3;
	/*飞机类的构造方法，根据定点坐标，以及朝向，创建一个飞机对象
	 * 
	 * 
	 * */
	public Plane(int _x,int _y,int _des){
		x=_x;
		y=_y;
		destination=_des;
		items=new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			items.add(new PlaneItem(_x,_y,_des,i)); 			
		}
	}
	
	/*如果可以插入，改变地图状态
	 * 
	 * 
	 * */
	public void insertToMap(Map _map){
		if(canInsertToMap(_map)){
			System.out.println("可以插入");	
			_map.setStatus(items.get(0).xOfItem, items.get(0).yOfItem,2);
			for (int i1 = 1; i1 < items.size(); i1++) {
				PlaneItem tempP=items.get(i1);
				_map.setStatus(tempP.xOfItem, tempP.yOfItem,1);
			}
		}else{
			System.out.println("无法插入");
		}
	}
	/*检测飞机是否可以插入地图
	 * 
	 * 
	 * */
	public boolean canInsertToMap(Map _map){
		boolean temp=true;
		for (int i = 0; i < items.size(); i++) {
			PlaneItem tempItem=items.get(i);
			if (!_map.canUse(tempItem.xOfItem, tempItem.yOfItem)) {
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
		for (int i = 0; i <items.size(); i++) {
			PlaneItem tempP=items.get(i);
			if ((_x==tempP.xOfItem)&&(_y==tempP.yOfItem)) {
				tempItem=tempP;
			}
		}
		return tempItem;
	}

	public int isHurt(int _x,int _y){	//0代表空，1代表伤，2代表沉
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
