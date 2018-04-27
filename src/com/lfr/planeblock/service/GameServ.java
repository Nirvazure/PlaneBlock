package com.lfr.planeblock.service;

import java.util.Random;

import com.lfr.planeblock.util.PlaneUtil;
import com.lfr.planeblock.vo.Plane;

public class GameServ {

	public void random3Plane(int[][] _map) {
		Plane tempPlane = null;
		PlaneUtil tempPlaneUtil=null;
		Random tempRandom = new Random();
		for (int i = 0; i < 3; i++) {
			do {
				int xTemp=tempRandom.nextInt(10);
				int yTemp=tempRandom.nextInt(10);
				int desTemp=tempRandom.nextInt(4);
				tempPlane=new Plane(xTemp, yTemp, desTemp);
				tempPlaneUtil=new PlaneUtil(tempPlane);
			} while (!tempPlaneUtil.canInsertToMap(_map));
			tempPlaneUtil.insertToMap(_map);
		}
		
	}
	
}
