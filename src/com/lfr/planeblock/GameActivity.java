package com.lfr.planeblock;

import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lfr.planeblock.component.MapView;
import com.lfr.planeblock.service.GameServ;
import com.lfr.planeblock.vo.GlobalData;

public class GameActivity extends Activity{

	ImageView img;
	
	@SuppressLint({ "NewApi", "UseSparseArrays" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		//设置全屏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//获取屏幕宽，高
        Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		GlobalData.setHeight(size.y);
		GlobalData.setWidth(size.x);
		//初始化数据
		int[][] map = new int[10][10];
		
		int[][] insideMap=new int[10][10];
		singleStart(insideMap);
		
		HashMap<Integer, Integer> items = new HashMap<Integer, Integer>();
		items.put(0, R.drawable.cell_normal);
		items.put(1, R.drawable.cell_hurt);
		items.put(2, R.drawable.cell_sink);
		items.put(3, R.drawable.cell_miss);
		//初始化界面
		LinearLayout mapContainer = (LinearLayout) this.findViewById(R.id.map);
		mapContainer.setOrientation(LinearLayout.VERTICAL);
		
		img=(ImageView)findViewById(R.id.stateImg);
		MapView mv = new MapView(this, map, insideMap,items, 10,img);
		mv.setBackcolor(0x99000099);
		mv.setGridColor(0xffcccccc);
		mapContainer.addView(mv);
		//setContentView(mapContainer);
	}

	static void singleStart(int[][] _insideMap){
		//初始化三架飞机
		GameServ singleServ=new GameServ();
		singleServ.random3Plane(_insideMap);
		for (int i = 0; i < _insideMap.length; i++) {
			for (int j = 0; j < _insideMap[0].length; j++) {
				if(_insideMap[i][j]==0){
					_insideMap[i][j]=3;
				}
			}
			
		}
	}
}
