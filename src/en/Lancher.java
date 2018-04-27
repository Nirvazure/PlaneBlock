package en;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

public class Lancher {
	//存放机头坐标
	static Point[] heads = new Point[3];

	public Lancher() {

	}

	public static void main(String[] args) {

		Map map=new Map();
		Map tryMap=new Map();
		tryMap.initTryMap();

		init3Plane(map);
		initHeads(map.map);

		for(Point point : heads){
			System.out.println(point.toString());
		}
		map.display();
		tryMap.display();

		boolean isContinue=true;

		do {
			int tempX = -1, tempY = -1;
			boolean isValid = true;

			Scanner input=new Scanner(System.in);
			while(true){
				isValid = true;
				try{
					System.out.println("请输入打击点x坐标：");
					tempX=input.nextInt();
					System.out.println("请输入打击点y坐标：");
					tempY=input.nextInt();
				}catch(Exception e){
					isValid = false;
					input.next();
				}
				if(isValid){
					break;
				}
			}
			System.out.println(tempX + ", " + tempY);

			if ((tempX>9)||(tempX<0)||(tempY>9)||(tempY<0)) {
				System.out.println("不合法点");	
			}else {
				if (map.returnStatic(tempX, tempY)==0) {
					System.out.println("空");
					tryMap.setStatus(tempX, tempY, 0);
				}else if (map.returnStatic(tempX, tempY)==1) {
					System.out.println("伤");
					tryMap.setStatus(tempX, tempY, 1);
				}else if (map.returnStatic(tempX, tempY)==2) {
					System.out.println("沉没");
					tryMap.setStatus(tempX, tempY, 2);
				}
			}
			
			tryMap.display();
			
			if (judgeEnd(heads,tryMap)) {
				isContinue=false;
			}
		} while (isContinue);
	}

	private static boolean judgeEnd(Point _temp[],Map _tryMap) {
		boolean all=true;
		for (int i = 0; i < _temp.length; i++) {
			if (!(_tryMap.returnStatic(_temp[i].x, _temp[i].y)==2)) {
				all=false;
			}		
		}
		if(all){
			System.out.println("击落所有");
			return true;
		}
		return false;
	}

	private static void initHeads(int[][] map) {
		int k = 0;
		for (int i = 0; i < map.length; i++) {
			for(int j = 0;j < map[0].length;j++){
				if(map[i][j] == 2){
					heads[k++] = new Point(i, j);
				}
			}
		}
	}

	/*随机生成三个飞机
	 * 
	 * */
	public static void init3Plane(Map _map){
		for (int i = 0; i < 3; i++) {
			randomCreatePlane(_map);	
		}
	}	
	/*随机生成飞机函数，并插入地图
	 * 
	 * 
	 * */
	public static void randomCreatePlane(Map _map){
		Plane tempPlane;
		Random tempRandom = new Random();
		do {
			int xTemp=tempRandom.nextInt(10);
			int yTemp=tempRandom.nextInt(10);
			int desTemp=tempRandom.nextInt(4);
			tempPlane=new Plane(xTemp, yTemp, desTemp);
		} while (!tempPlane.canInsertToMap(_map));
		tempPlane.insertToMap(_map);
	}

	/*命令行手动创建飞机函数
	 *返回一个Plane类型的飞机
	 *pre-condition：
	 *post-condition：
	 * */
	public static Plane createPlane(){
		Scanner input=new Scanner(System.in);
		System.out.println("请输入机头行数：");
		int xTemp=input.nextInt();
		System.out.println("请输入机头列数：");
		int yTemp=input.nextInt();
		System.out.println("请输入机头方向0up1down2left3right");
		int desTemp=input.nextInt();
		return new Plane(xTemp, yTemp, desTemp);
	}


}
