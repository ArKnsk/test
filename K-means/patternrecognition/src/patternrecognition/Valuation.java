package patternrecognition;

public class Valuation {

	public static void precision(DMatrix[] pointset) {
		double[] precision = new double[2];

		double numerator = 0;
		double denominator = 0;

		for(int groundcls = 0; groundcls < pointset.length; groundcls++){
			double temp1 = 0;
			int fitcls;
			loop: for(int recogcls = 0; recogcls > -1; recogcls++){
				double cnf = 0;
				double temp2 = 0;
				for(int i = 0; i < pointset.length; i++){
					for(int j = 0; i < pointset[i].samplenum(); j++){
						if(pointset[i].returnkclass(j) == recogcls){
							//存在しないクラスに達した場合の終了フラグ
							cnf++;
							if(pointset[i].regroundclass(j) == groundcls){
								temp2++;
							}
						}
						//最も多くの一致が見られたクラスを正解クラスとする。
						if(temp2 > temp1){
							temp1 = temp2;
							fitcls = recogcls;
						}
						if(cnf == 0){
							break loop;
						}
					}
				}
			}
		}

		for(int i = 0; i < pointset.length; i++){
			for(int j = 0; j < pointset[i].samplenum(); j++){
				if(pointset[i].returntest(j) == 1){
					denominator++;
					if(pointset[i].regroundclass(j) == pointset[i].returnnnclass(j)){
						numerator++;
					}
				}
			}
		}
		precision[1] = numerator / denominator;
	}

	public static void recall(DMatrix[] pointset) {
		for(int i = 0; i < pointset.length; i++){
			for(int j = 0; j < pointset[i].samplenum(); j++){

			}
		}


	}

	public static void falsepositive(DMatrix[] pointset){
		for(int i = 0; i < pointset.length; i++){

			System.out.println("NN法" + i + "番目クラス偽陽性");
		}
	}

	public static void errorrate(DMatrix[] pointset) {
		// TODO 自動生成されたメソッド・スタブ


		for(int groundcls = 0; groundcls < pointset.length; groundcls++){
			double temp1 = 0;
			int fitcls;
			loop: for(int recogcls = 0; recogcls > -1; recogcls++){
				double cnf = 0;
				double temp2 = 0;
				for(int i = 0; i < pointset.length; i++){
					for(int j = 0; i < pointset[i].samplenum(); j++){
						if(pointset[i].returnkclass(j) == recogcls){
							//存在しないクラスに達した場合の終了フラグ
							cnf++;
							if(pointset[i].regroundclass(j) == groundcls){
								temp2++;
							}
						}
						//最も多くの一致が見られたクラスを正解クラスとする。
						if(temp2 > temp1){
							temp1 = temp2;
							fitcls = recogcls;
						}
						if(cnf == 0){
							break loop;
						}
					}
				}
			}
		}

		double numerator = 0;
		double denominator = 0;

		for(int i = 0; i < pointset.length; i++){
			for(int j = 0; j < pointset[i].samplenum(); j++){
				if(pointset[i].returntest(j) == 1){
					//テストデータセット数
					denominator++;
					if(pointset[i].regroundclass(j) != pointset[i].returnnnclass(j)){
						//識別クラスと正解クラスの異なるデータ数
						numerator++;
					}
				}
			}
		}
		double errorrate = numerator / denominator;
		System.out.println("NN法 誤り率" + errorrate);
	}
}
