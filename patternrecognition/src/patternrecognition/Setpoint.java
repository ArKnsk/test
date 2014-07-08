package patternrecognition;

import Jama.Matrix;

public class Setpoint {
	/**
	 * 0～最大値の間で多次元正規分布に従う座標を各クラスの生成サンプル数出力
	 * 0～1の乱数を発生させ、多次元正規分布の確率分布の値より下回った場合その座標を採用
	 * @param points
	 * @param mu
	 * @param sig
	 * @param max
	 */
	public static void Gaussian(DMatrix points , Matrix mu, Matrix sig, Matrix max){
		Matrix temp;

		temp = new Matrix(points.dimension(), 1);

		for(int i = 0; i < points.samplenum(); i++){
			boolean checker = true;

			while(checker){
				for(int j = 0; j < points.dimension();j++){
					temp.set(j, 0, Math.random());
				}
				checker = Gauss(temp, mu, sig) < Math.random();
			}

			temp.timesEquals(500);
			points.setMatrix(temp, i);
		}
	}

	/**
	 * 多次元正規分布に従う確率分布生成
	 * (2π^d |Σ|)^1/2 * exp(-(x-μ)Σ^(-1)(x-μ)/2)
	 * @param x
	 * @param mu
	 * @param sig
	 * @return
	 */
	private static double Gauss(Matrix x, Matrix mu, Matrix sig) {
		// TODO 自動生成されたメソッド・スタブ
		Matrix mu_x = x.minus(mu);
		double det = sig.det();

		double a = Math.sqrt(Math.pow(2 * Math.PI, x.getRowDimension()) * det);

		double b = mu_x.transpose().times(sig.inverse()).times(mu_x).get(0, 0);

		double pdf = Math.exp(- b / 2) / a;

		if(pdf > 1){
			System.out.println(pdf + "," + x.getRowDimension());
			for(int i = 0; i < x.getRowDimension(); i++){
				System.out.println(x.get(i,0));
			}
			for(int i = 0; i < mu.getRowDimension(); i++){
				System.out.println(mu.get(i,0));
			}
			for(int i = 0; i < mu_x.getRowDimension(); i++){
				System.out.println(mu_x.get(i,0));
			}
			for(int i = 0; i < sig.getRowDimension(); i++){
				for(int j = 0; j < sig.getColumnDimension(); j++){
					System.out.println(sig.get(i,j));
				}
			}
		}
		return pdf;
	}
}
