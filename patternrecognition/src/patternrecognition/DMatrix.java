package patternrecognition;

import Jama.Matrix;

public class DMatrix {
	private int samplenum;
	private int dimension;
	private Matrix[] XMatrix;
	private int groundcls[];
	private int knewcls[];
	private int nnnewcls[];
	private int test[];

	/**
	 * 次元dim,クラスcls,サンプル数numを用いてサンプルの入れ物を確保
	 * @param dim 次元数
	 * @param cls クラス番号
	 * @param num サンプル数
	 */
	public DMatrix(int dim, int cls, int num){
		samplenum = num;
		dimension = dim;
		groundcls = new int[samplenum];
		knewcls = new int[samplenum];
		nnnewcls = new int[samplenum];
		test = new int[samplenum];
		for(int i = 0; i < samplenum; i++){
			groundcls[i] = cls;
			knewcls[i] = -1;
			nnnewcls[i] = -1;
			test[i] = 0;
		}

		XMatrix = new Matrix[samplenum];
		for(int i = 0; i < samplenum; i++){
			XMatrix[i] = new Matrix(dim,1);
		}
	}

	/**
	 * i番目の座標行列を座標行列setに置き換え
	 * @param set 座標行列
	 * @param i 行列番号
	 */
	public void setMatrix(Matrix set, int i){
		if(set.getRowDimension() == dimension){
			XMatrix[i] = set.copy();
			return;
		}
		else{
			return;
		}
	}

	/**
	 * i番目の座標・特徴を表す行列を返す
	 * @param i
	 * @return i番目のdimension次元座標・特徴を表す行列 (Matrix型)
	 */
	public Matrix returnPoint(int i){
		return XMatrix[i];
	}

	/**
	 * サンプル数を返す
	 * @return samplenum
	 */
	public int samplenum(){
		return samplenum;
	}

	/**
	 * 次元数を返す
	 * @return dimension
	 */
	public int dimension(){
		return dimension;
	}

	/**
	 * i番目の座標の正解クラスを返す
	 * @param i
	 * @return 正解クラス
	 */
	public int regroundclass(int i){
		return groundcls[i];
	}

	/**
	 * i番目の座標の識別後のクラスを返す
	 * @param i
	 * @return 識別後クラス
	 */
	public int returnkclass(int i){
		return knewcls[i];
	}

	/**
	 * i番目の座標の識別クラスを変更
	 * @param setcls
	 */
	public void setkclass(int setcls, int i){
		knewcls[i] = setcls;
		return;
	}

	/**
	 * i番目の座標の識別後のクラスを返す
	 * @param i
	 * @return 識別後クラス
	 */
	public int returnnnclass(int i){
		return nnnewcls[i];
	}

	/**
	 * i番目の座標の識別クラスを変更
	 * @param setcls
	 */
	public void setnnclass(int setcls, int i){
		nnnewcls[i] = setcls;
		return;
	}

	public int returntest(int i){
		return test[i];
	}

	public void settest(int flag, int i){
		test[i] = flag;
		return;
	}
}
