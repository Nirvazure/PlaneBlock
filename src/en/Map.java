package en;

public class Map {

	int[][] map=new int[10][10];
	int xOfMap,yOfMap;

	public Map(){
		initMap();
	}

	public void initMap() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j]=0;
			}
		}
	}

	public void initTryMap() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j]=-1;
			}
		}
	}

	public void display() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(map[i][j] == -1){
					System.out.print("- ");
				}else{
					System.out.print(map[i][j]+" ");
				}
			}
			System.out.println();
		}
	}

	public int returnStatic(int _x,int _y){
		return map[_x][_y];
	}

	public void setStatus(int _x,int _y,int _value){
		map[_x][_y]=_value;
	}

	public boolean canUse(int _x,int _y) {
		if ((_x<0)||(_y<0)||(_x>9)||(_y>9)) {
			//System.out.println("边界地带");		//需要信息时取消注释
			return false;
		}else{
			if(returnStatic(_x, _y)==0){
				return true;
			}
			else{
				//System.out.println("有已经被占用的位置");		//需要信息时取消注释
				return false;
			}
		}
	}


}
