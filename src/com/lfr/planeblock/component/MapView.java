package com.lfr.planeblock.component;

import java.util.Map;
import java.util.Set;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.lfr.planeblock.vo.GlobalData;
import com.lfr.planeblock.service.MapServ;

public class MapView extends View {

	private int startX;
	private int startY;
	//棋盘与边界间的空隙
	private int MAP_PADDING;
	//格子宽度
	private int GRID_WIDTH;
	//行数（列数）
	private int GRID_NUM;
	private Paint paint = null;
	//棋盘数组
	private int[][] map = null;
	private int[][] insideMap=null;
	//棋子Map<数组值, 资源ID>
	private Map<Integer, Integer> items = null;
	//棋盘背景颜色
	private int backcolor = 0xFF8B4726;
	//网格颜色
	private int gridColor = Color.BLACK;
	//Map服务对象
	MapServ ms = null;
	ImageView img;

	public MapView(Context context, int[][] map,int[][] insideMap, Map<Integer, Integer> items, int padding,ImageView img) throws IllegalArgumentException{
		super(context);

		if(map.length != map[0].length){
			throw new IllegalArgumentException("map数组的行数与列数必须相等");
		}
		//初始化数据
		this.map = map;//初始化棋盘数组
		this.insideMap=insideMap;//***
		this.img=img;
		this.items = items;//初始化棋子Map
		this.MAP_PADDING = padding;//初始化padding
		GRID_NUM = map.length;//初始化行数
		GRID_WIDTH = (GlobalData.getWidth()-MAP_PADDING*2)/GRID_NUM;//初始化行宽
		MAP_PADDING = (GlobalData.getWidth()-GRID_WIDTH*GRID_NUM)/2;//修正padding
		startX = MAP_PADDING;//初始化起始位置
		startY = MAP_PADDING;
		//初始化画图相关
		paint = new Paint();//实例化一个画笔
		paint.setAntiAlias(true);//设置画笔去锯齿，没有此语句，画的线或图片周围不圆滑
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(backcolor);//背景色
		paint.setColor(gridColor);//画笔颜色

		/*绘制棋盘网格*/
		for(int i = 0;i < GRID_NUM + 1;i++){
			canvas.drawLine(startX, startY+i*GRID_WIDTH, startX+(GRID_NUM)*GRID_WIDTH, startY+i*GRID_WIDTH, paint);
			canvas.drawLine(startX+i*GRID_WIDTH, startY, startX+i*GRID_WIDTH, startY+(GRID_NUM)*GRID_WIDTH, paint);
		}
		/*绘制棋子*/
		for(int i=0;i<GRID_NUM;i++){
			for(int j=0;j<GRID_NUM;j++){
				Set<Integer> keys = items.keySet();
				for(int k : keys){
					if(map[i][j] == k){
						int left = startX + i * GRID_WIDTH;
						int top = startY + j * GRID_WIDTH;
						Bitmap bmp = BitmapFactory.decodeResource(getResources(), items.get(k));
						bmp = resize(bmp, (float)GRID_WIDTH / bmp.getWidth());
						canvas.drawBitmap(bmp, left, top, paint);
					}
				}
			}
		}
	}

	//重写View的监听触摸事件的方法
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float touchX = event.getX();
		float touchY = event.getY();

		if(touchX >= startX && touchX <= startX + (GRID_NUM) * GRID_WIDTH && touchY >= startY && touchY <= startY + (GRID_NUM) * GRID_WIDTH){
			//根据点击的位置，获取数组行列下标
			int index_x = (int)(touchX-startX) / GRID_WIDTH;
			int index_y = (int)(touchY-startY) / GRID_WIDTH;
			//创建Map服务对象处理逻辑
			ms = new MapServ(map,insideMap, index_x, index_y,img);
			ms.serve();
		}

		invalidate();//点击完成后，通知重绘即再次执行onDraw方法
		return super.onTouchEvent(event);
	}

	public int getMAP_PADDING() {
		return MAP_PADDING;
	}

	public void setMAP_PADDING(int mAP_PADDING) {
		MAP_PADDING = mAP_PADDING;
		startX = MAP_PADDING;
		startY = MAP_PADDING;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public Map<Integer, Integer> getItems() {
		return items;
	}

	public void setItems(Map<Integer, Integer> items) {
		this.items = items;
	}

	public int getBackcolor() {
		return backcolor;
	}

	/**
	 * @param backcolor 16进制ARGB，例如0xFF8B4726或者Color.Black
	 */
	public void setBackcolor(int backcolor) {
		this.backcolor = backcolor;
	}

	public int getGridColor() {
		return gridColor;
	}

	/**
	 * @param gridColor 16进制ARGB，例如0xFFFFFFFF或者Color.Black
	 */
	public void setGridColor(int gridColor) {
		this.gridColor = gridColor;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int width = GlobalData.getWidth(); 
		int height = GlobalData.getWidth();
		setMeasuredDimension(width, height);
	}

	private static Bitmap resize(Bitmap bitmap, float scale) {
		Matrix matrix = new Matrix();
		matrix.postScale(scale, scale);//长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return resizeBmp;
	}
}
