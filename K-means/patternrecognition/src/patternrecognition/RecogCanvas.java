package patternrecognition;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import Jama.Matrix;

public class RecogCanvas extends Canvas {
	private DMatrix[] drawpo;
	private Matrix dmax;
	private int classmethod;

	public RecogCanvas(DMatrix[] setpo, Matrix max, int clsmethod){
		drawpo = setpo;
		dmax = max.copy();
		classmethod = clsmethod;
	}

	public void paint(Graphics g){
	  //ここに描画処理を書きます。
		Color bgcol = new Color(220,220,220);
		setBackground(bgcol);

		g.setColor(Color.BLACK);
		g.drawLine(0, (int)dmax.get(1,0) - 100, (int)dmax.get(0, 0), (int)dmax.get(1, 0) - 100);	//
		g.drawLine(100 , 0, 100, (int)dmax.get(1, 0));	//y軸

		int zeropointx = 100;
		int zeropointy = (int) dmax.get(1, 0) - 100;

		for(int i = 0; i < drawpo.length; i++){
			for(int j = 0; j < drawpo[i].samplenum(); j++){
				double xy[] = new double[drawpo[i].dimension()];

				for(int k = 0; k < drawpo[i].dimension(); k++){
					xy[k] = drawpo[i].returnPoint(j).get(k,0);
				}

				if(classmethod == 0){
					g.setColor(setclr(drawpo[i].returnkclass(j)));
				}
				else{
					g.setColor(setclr(drawpo[i].returnnnclass(j)));
				}

				g.fillRect(zeropointx + (int) xy[0], zeropointy - (int) xy[1], 3, 3);
			}
		}
	 }

	private Color setclr(int i){
		if(i == 0){
			return Color.BLUE;
		}
		else if(i == 1){
			return Color.RED;
		}
		else if(i == 2){
			return Color.GREEN;
		}
		else if(i == 3){
			return Color.ORANGE;
		}
		else if(i == 4){
			return Color.YELLOW;
		}
		else if(i == 5){
			return Color.PINK;
		}
		else{
			return Color.BLACK;
		}
	}

}
