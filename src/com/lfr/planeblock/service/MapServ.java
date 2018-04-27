package com.lfr.planeblock.service;

import com.lfr.planeblock.GameActivity;
import com.lfr.planeblock.R;
import com.lfr.planeblock.util.MapUtil;

import android.graphics.Point;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MapServ {
	int[][] insideMap=null;
	int[][] map = null;
	int x, y;
	ImageView img;
	
	Point[] heads=new Point[3];
	
	MapUtil mUtil;
	
	public MapServ(int[][] map,int[][] insideMap, int x, int y,ImageView img){
		this.insideMap=insideMap;
		this.map = map;
		this.x = x;
		this.y = y;
		this.img=img;
		mUtil=new MapUtil(map);
		initHeads(insideMap);
	}
		
	public void serve(){
		map[x][y] = insideMap[x][y];
		if(map[x][y]==1){
			img.setImageResource(R.drawable.state_hurt);
		}else if(map[x][y]==2){
			img.setImageResource(R.drawable.state_sink);
		}else if(map[x][y]==3){
			img.setImageResource(R.drawable.state_normal);
		}
		if(judgeEnd(heads)){
			Log.e("end", "游戏结束");
		}
	}
	
	private boolean judgeEnd(Point _temp[]) {
		boolean all=true;
		for (int i = 0; i < _temp.length; i++) {
			if (!(mUtil.returnStatic(_temp[i].x, _temp[i].y)==2)) {
				all=false;
			}		
		}
		if(all){
			return true;
		}
		return false;
	}
	
	private void initHeads(int[][] map) {
		int k = 0;
		for (int i = 0; i < map.length; i++) {
			for(int j = 0;j < map[0].length;j++){
				if(map[i][j] == 2){
					heads[k++] = new Point(i, j);
				}
			}
		}
	}
}
