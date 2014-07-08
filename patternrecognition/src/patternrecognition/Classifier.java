package patternrecognition;

import Jama.Matrix;

public class Classifier {
	/**
	 * k-means法による多クラス識別
	 * @param points
	 * @param k
	 */
	public static void kmeans(DMatrix[] points, int k){
		Matrix[] center;
		center = new Matrix[k];

		for(int i = 0; i < k; i++){
			center[i] = new Matrix(points[0].dimension(), 1);
		}

		/*===============================================================================
		 ランダムにk個のクラスを割り当て
		===============================================================================*/
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[i].samplenum(); j++){
				points[i].setkclass((int) (Math.random() * k), j);
			}
		}

		boolean checker = true;
		while(checker){
			checker = false;

			/*===============================================================================
			 重心の初期化
			===============================================================================*/
			for(int c = 0; c < k; c++){
				for(int i = 0; i < points[0].dimension(); i++){
					center[c].set(i, 0, 0);
				}
			}
			/*===============================================================================
			 重心の計算
			===============================================================================*/

			for(int c = 0; c < k; c++){
				int pointnum = 0;
				for(int i = 0; i < points.length; i++){
					for(int j = 0; j < points[i].samplenum(); j++){
						if(points[i].returnkclass(j) == c){
							center[c].plusEquals(points[i].returnPoint(j));
							pointnum++;
						}
					}
				}
				if(pointnum != 0){
					for(int i = 0; i < center[c].getRowDimension(); i++){
						center[c].set(i, 0, center[c].get(i, 0) /pointnum);
					}
				}
			}

			/*===============================================================================
			 最も近い重心のクラスに再分類
			===============================================================================*/
			for(int i = 0; i < points.length; i++){
				for(int j = 0; j < points[i].samplenum(); j++){
					double Dist = Euclid(center[0], points[i].returnPoint(j));
					int tempcls = 0;
					for(int c = 1; c < k; c++){
						double tempDist = Euclid(center[c],points[i].returnPoint(j));
						if(Dist > tempDist){
							Dist = tempDist;
							tempcls = c;
						}
					}
					if(points[i].returnkclass(j) != tempcls){
						points[i].setkclass(tempcls, j);
						checker = true;
					}
				}
			}
		}
	}

	/**
	 * 最近傍法による分類
	 * @param points
	 */
	public static void nn(DMatrix[] points){
		for(int i = 0; i < points.length; i++){
			for(int j = 0; j < points[i].samplenum(); j++){
				if(points[i].returntest(j) == 1){
					int cls1 = 0,cls2 = 0;
					double Dist = 0;

					loop : for(int m = 0; m < points.length; m++){
						for(int n = 0; n < points[m].samplenum(); n++){
							if(points[m].returntest(n) != 1){
								Dist = Euclid(points[i].returnPoint(j), points[m].returnPoint(n));
								cls1 = m;
								cls2 = n;
								break loop;
							}
						}
					}

					double temp;
					for(int m = 0; m < points.length; m++){
						for(int n = 0; n < points[m].samplenum(); n++){
							if(points[m].returntest(n) != 1){
								temp = Euclid(points[i].returnPoint(j), points[m].returnPoint(n));
								if(temp < Dist){
									Dist = temp;
									cls1 = m;
									cls2 = n;
								}
							}
						}
					}
					points[i].setnnclass(points[cls1].regroundclass(cls2), j);
				}
			}
		}
	}

	private static double Euclid(Matrix p1, Matrix p2){
		double Euclid = 0;
		if(p1.getColumnDimension() == 1 && p2.getColumnDimension() == 1){
			if(p1.getRowDimension() == p2.getRowDimension()){
				for(int i = 0; i < p1.getRowDimension(); i++){
					Euclid += Math.pow(p1.get(i, 0) - p2.get(i, 0), 2);
				}
				return Math.sqrt(Euclid);
			}
		}
		return 0;
	}
}
