package en;

public class PlaneItem {
	
	int xOfItem,yOfItem;
	int des;
	int idOfItem;
	
	final static int HEAD=0;
	final static int LWING=1;
	final static int LINWING=2;
	final static int CWING=3;
	final static int RINWING=4;
	final static int RWING=5;
	final static int BODY=6;
	final static int LTAIL=7;
	final static int CTAIL=8;
	final static int RTAIL=9;
	
	public PlaneItem(int _x,int _y,int _des,int _idOfItem){
		xOfItem=_x;
		yOfItem=_y;
		des=_des;
		idOfItem=_idOfItem;
		selectFunction(des, idOfItem);
	}
	
	public int getId(){
		return idOfItem;
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
		case HEAD:
			break;
		case LWING:
			xOfItem=xOfItem-2;
			yOfItem=yOfItem-1;
			break;
		case LINWING:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem-1;
			break;
		case CWING:
			yOfItem=yOfItem-1;
			break;
		case RINWING:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem-1;
			break;
		case RWING:
			xOfItem=xOfItem+2;
			yOfItem=yOfItem-1;
			break;
		case BODY:
			yOfItem=yOfItem-2;
			break;
		case LTAIL:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem-3;
			break;
		case CTAIL:
			yOfItem=yOfItem-3;
			break;
		case RTAIL:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem-3;
			break;
		default:
			break;
		}		
	}
	
	private void leftFunction(int _id) {
		switch (_id) {
		case HEAD:
			
			break;
		case LWING:
			xOfItem=xOfItem+2;
			yOfItem=yOfItem+1;
			break;
		case LINWING:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem+1;
			break;
		case CWING:
			yOfItem=yOfItem+1;
			break;
		case RINWING:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem+1;
			break;
		case RWING:
			xOfItem=xOfItem-2;
			yOfItem=yOfItem+1;
			break;
		case BODY:
			yOfItem=yOfItem+2;
			break;
		case LTAIL:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem+3;
			break;
		case CTAIL:
			yOfItem=yOfItem+3;
			break;
		case RTAIL:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem+3;
			break;
		default:
			break;
		}
		
	}
	
	private void upFunction(int _id) {
		switch (_id) {
		case HEAD:
			
			break;
		case LWING:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem-2;
			break;
		case LINWING:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem-1;
			break;
		case CWING:
			xOfItem=xOfItem+1;
			break;
		case RINWING:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem+1;
			break;
		case RWING:
			xOfItem=xOfItem+1;
			yOfItem=yOfItem+2;
			break;
		case BODY:
			xOfItem=xOfItem+2;
			break;
		case LTAIL:
			xOfItem=xOfItem+3;
			yOfItem=yOfItem-1;
			break;
		case CTAIL:
			xOfItem=xOfItem+3;
			break;
		case RTAIL:
			xOfItem=xOfItem+3;
			yOfItem=yOfItem+1;
			break;
		default:
			break;
		}
		
	}
	
	private void downFunction(int _id) {
		switch (_id) {
		case HEAD:
			
			break;
		case LWING:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem+2;
			break;
		case LINWING:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem+1;
			break;
		case CWING:
			xOfItem=xOfItem-1;
			break;
		case RINWING:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem-1;
			break;
		case RWING:
			xOfItem=xOfItem-1;
			yOfItem=yOfItem-2;
			break;
		case BODY:
			xOfItem=xOfItem-2;
			break;
		case LTAIL:
			xOfItem=xOfItem-3;
			yOfItem=yOfItem+1;
			break;
		case CTAIL:
			xOfItem=xOfItem-3;
			break;
		case RTAIL:
			xOfItem=xOfItem-3;
			yOfItem=yOfItem-1;
			break;
		default:
			break;
		}
		
	}
	

}
	
