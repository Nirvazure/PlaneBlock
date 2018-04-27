package com.lfr.planeblock.util;

import com.lfr.planeblock.vo.Plane;
import com.lfr.planeblock.vo.PlaneItem;

public class PlaneItemUtil {
	
	PlaneItem pI;
	
	public PlaneItemUtil(PlaneItem _pI) {
		this.pI=_pI;
	}
	
	public void selectFunction(int _des,int _id){
		switch (_des) {
		case Plane.UP:
			upFunction(_id);
			break;
		case Plane.DOWN:
			downFunction(_id);
			break;
		case Plane.LEFT:
			leftFunction(_id);
			break;
		case Plane.RIGHT:
			rightFunction(_id);
			break;
		default:
			break;
		}
	}
	
	private void rightFunction(int _id) {
		switch (_id) {
		case PlaneItem.HEAD:
			break;
		case PlaneItem.LWING:
			pI.setxOfItem(pI.getxOfItem()-2);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.LINWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.CWING:
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.RINWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.RWING:
			pI.setxOfItem(pI.getxOfItem()+2);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.BODY:
			pI.setyOfItem(pI.getyOfItem()-2);
			break;
		case PlaneItem.LTAIL:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()-3);
			break;
		case PlaneItem.CTAIL:
			pI.setyOfItem(pI.getyOfItem()-3);
			break;
		case PlaneItem.RTAIL:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()-3);
			break;
		default:
			break;
		}		
	}
	
	private void leftFunction(int _id) {
		switch (_id) {
		case PlaneItem.HEAD:		
			break;
		case PlaneItem.LWING:
			pI.setxOfItem(pI.getxOfItem()+2);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.LINWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.CWING:
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.RINWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.RWING:
			pI.setxOfItem(pI.getxOfItem()-2);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.BODY:
			pI.setyOfItem(pI.getyOfItem()+2);
			break;
		case PlaneItem.LTAIL:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()+3);
			break;
		case PlaneItem.CTAIL:
			pI.setyOfItem(pI.getyOfItem()+3);
			break;
		case PlaneItem.RTAIL:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()+3);
			break;
		default:
			break;
		}
		
	}
	
	private void upFunction(int _id) {
		switch (_id) {
		case PlaneItem.HEAD:	
			break;
		case PlaneItem.LWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()-2);
			break;
		case PlaneItem.LINWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.CWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			break;
		case PlaneItem.RINWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.RWING:
			pI.setxOfItem(pI.getxOfItem()+1);
			pI.setyOfItem(pI.getyOfItem()+2);
			break;
		case PlaneItem.BODY:
			pI.setxOfItem(pI.getxOfItem()+2);
			break;
		case PlaneItem.LTAIL:
			pI.setxOfItem(pI.getxOfItem()+3);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.CTAIL:
			pI.setxOfItem(pI.getxOfItem()+3);
			break;
		case PlaneItem.RTAIL:
			pI.setxOfItem(pI.getxOfItem()+3);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		default:
			break;
		}
		
	}
	
	private void downFunction(int _id) {
		switch (_id) {
		case PlaneItem.HEAD:
			
			break;
		case PlaneItem.LWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()+2);
			break;
		case PlaneItem.LINWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.CWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			break;
		case PlaneItem.RINWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		case PlaneItem.RWING:
			pI.setxOfItem(pI.getxOfItem()-1);
			pI.setyOfItem(pI.getyOfItem()-2);
			break;
		case PlaneItem.BODY:
			pI.setxOfItem(pI.getxOfItem()-2);
			break;
		case PlaneItem.LTAIL:
			pI.setxOfItem(pI.getxOfItem()-3);
			pI.setyOfItem(pI.getyOfItem()+1);
			break;
		case PlaneItem.CTAIL:
			pI.setxOfItem(pI.getxOfItem()-3);
			break;
		case PlaneItem.RTAIL:
			pI.setxOfItem(pI.getxOfItem()-3);
			pI.setyOfItem(pI.getyOfItem()-1);
			break;
		default:
			break;
		}
		
	}
	
}
