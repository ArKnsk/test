package patternrecognition;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Jama.Matrix;


public class Pareco {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		/*===============================================================================
		 サンプル作成パラメータ設定
		===============================================================================*/

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String temp = null;

		int dim = 0;								//次元数
		Matrix[] mu;								//1クラスを生成する多次元正規分布の平均
		Matrix[] sig;								//1クラスを生成する多次元正規分布の共分散
		Matrix max;									//定義域
		int[] samplenum;							//1クラスのサンプル数
		int clsnum = 0;								//クラス数

		try {
			System.out.println("次元数入力");
			temp = br.readLine();
			dim = Integer.parseInt(temp);			//次元数dimの設定

			System.out.println("クラス数入力");
			temp = br.readLine();
			clsnum = Integer.parseInt(temp);		//生成クラス数clsnumの設定
		} catch (IOException e) {
			e.printStackTrace();
		}

		max = new Matrix(dim,1);
		mu = new Matrix[clsnum];
		sig = new Matrix[clsnum];
		samplenum = new int[clsnum];

		for(int i = 0; i < clsnum; i++){
			mu[i] = new Matrix(dim,1);
			sig[i] = new Matrix(dim,dim);
		}

		System.out.println("自力で入力:1,d=2固定:2,random:other");
		String checker = null;
		try {
			checker = br.readLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		if(checker.equals("1")){										//任意の定義域・平均・共分散・サンプル数でサンプル生成
			try {
				for(int i = 0; i < dim; i++){
					System.out.println("最大値Max" + i + "入力");
					temp = br.readLine();
					int maxtemp = Integer.parseInt(temp);					//各次元の定義域入力
					max.set(i, 0, maxtemp);
				}

				for(int k = 0; k < clsnum; k++){
					for(int i = 0; i < dim; i++){
						System.out.println("多次元正規分布の平均μ" + i + "入力");
						temp = br.readLine();
						double mutemp = Double.parseDouble(temp);			//クラスkのある次元iに関する平均入力
						mu[k].set(i, 0, mutemp);
					}

					for(int i = 0; i < dim; i++){
						for(int j = 0; j < dim; j++){
							System.out.println("多次元正規分布の分散Σ" + i + "," + j + "入力");
							temp = br.readLine();
							double sigtemp = Double.parseDouble(temp);	//クラスkの共分散行列のi行j列成分の入力
							sig[k].set(i, j, sigtemp);
						}
					}
				}

				for(int i = 0; i < clsnum; i++){
					System.out.println("クラス" + i + "サンプル数入力");
					temp = br.readLine();
					samplenum[i] = Integer.parseInt(temp);				//各クラスのサンプル数
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(checker.equals("2") && dim == 2 && clsnum == 2){		//固定のサンプル生成
			for(int i = 0; i < dim; i++){
				max.set(i, 0, 800);											//固定の定義域
			}
																		//固定の平均・共分散
			mu[0].set(0, 0, 0.5);
			mu[0].set(1, 0, 0.3);
			mu[1].set(0, 0, 0.2);
			mu[1].set(1, 0, 0.6);

			sig[0].set(0, 0, 0.006);
			sig[0].set(0, 1, 0.003);
			sig[0].set(1, 0, 0.003);
			sig[0].set(1, 1, 0.003);
			sig[1].set(0, 0, 0.001);
			sig[1].set(0, 1, 0.002);
			sig[1].set(1, 0, 0.002);
			sig[1].set(1, 1, 0.005);

			for(int i = 0; i < clsnum; i++){
				samplenum[i] = 500;									//固定のサンプル数
			}
		}
		else if(checker.equals("2") && dim == 2 && clsnum == 3){		//固定のサンプル生成
			for(int i = 0; i < dim; i++){
				max.set(i, 0, 800);											//固定の定義域
			}
																		//固定の平均・共分散
			mu[0].set(0, 0, 0.5);
			mu[0].set(1, 0, 0.3);
			mu[1].set(0, 0, 0.2);
			mu[1].set(1, 0, 0.6);
			mu[2].set(0, 0, 0.6);
			mu[2].set(1, 0, 0.6);

			sig[0].set(0, 0, 0.006);
			sig[0].set(0, 1, 0.003);
			sig[0].set(1, 0, 0.003);
			sig[0].set(1, 1, 0.003);
			sig[1].set(0, 0, 0.001);
			sig[1].set(0, 1, 0.002);
			sig[1].set(1, 0, 0.002);
			sig[1].set(1, 1, 0.005);
			sig[2].set(0, 0, 0.002);
			sig[2].set(0, 1, 0.004);
			sig[2].set(1, 0, 0.005);
			sig[2].set(1, 1, 0.003);

			for(int i = 0; i < clsnum; i++){
				samplenum[i] = 500;									//固定のサンプル数
			}
		}
		else{															//固定の定義域、サンプル数で平均、共分散は0～1のランダム
			for(int i = 0; i < dim; i++){
				max.set(i, 0, 800);											//固定の定義域
			}

			for(int k = 0; k < clsnum; k++){							//平均・共分散はランダム値
				for(int i = 0; i < dim; i++){
					mu[k].set(i, 0, Math.random() / 4);
				}
				for(int i = 0; i < dim; i++){
					for(int j = 0; j < dim; j++){
						sig[k].set(i, j, Math.random() / 100);
					}
				}
			}

			for(int i = 0; i < clsnum; i++){							//サンプル数
				samplenum[i] = 500;
			}
		}

		/*===============================================================================
		 サンプル作成
		===============================================================================*/

		DMatrix[] pointset = new DMatrix[clsnum];
		for(int i = 0; i < clsnum; i++){
			pointset[i] = new DMatrix(dim, i, samplenum[i]);
			Setpoint.Gaussian(pointset[i], mu[i],  sig[i], max);
		}

		for(int i = 0; i < 100; i++){
			boolean flag = true;
			while(flag){
				int clssel = (int) (Math.random() * clsnum);
				int samplesel = (int) (Math.random() * samplenum[clssel]);

				if(pointset[clssel].returntest(samplesel) != 1){
					pointset[clssel].settest(1, samplesel);
					flag = false;
				}
			}
		}

		/*===============================================================================
		 パターン認識
		===============================================================================*/

		/*
		String k = null;
		try {
			k = br.readLine();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		clsnum = Integer.parseInt(k);
		*/


		Classifier.kmeans(pointset, clsnum);
		Classifier.nn(pointset);

		/*===============================================================================
		 評価
		===============================================================================*/

		//Valuation.errorrate(pointset);
		//Valuation.precision(pointset);
		//Valuation.recall(pointset);

		/*===============================================================================
		 2次元描画
		===============================================================================*/

		if(dim == 2){
			/*===============================================================================
			 2次元パネル設定
			===============================================================================*/
			JFrame frame = new JFrame();

			int margin = 100;
			frame.setTitle("recog");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(3 * margin + 2 * (int)max.get(0, 0), 2 * margin + (int)max.get(1, 0));
			frame.setLayout(null);

			/*===============================================================================
			 k-means用パネル内描画
			===============================================================================*/
			JPanel cp1 = new JPanel();
			cp1.setBackground(Color.WHITE);
			cp1.setLayout(null);
			frame.add(cp1);
			cp1.setBounds(0 , 0, 2 * margin + (int) max.get(0, 0), 2 * margin + (int)max.get(1, 0));

			RecogCanvas canvas1 = new RecogCanvas(pointset, max, 0);
			cp1.add(canvas1);
			canvas1.setBounds(margin,margin,(int)max.get(0 ,0),(int)max.get(1,0));

			/*===============================================================================
			 nn法用パネル内描画
			===============================================================================*/
			JPanel cp2 = new JPanel();
			cp2.setBackground(Color.WHITE);
			cp2.setLayout(null);
			frame.add(cp2);
			cp2.setBounds(2 * margin + (int) max.get(0, 0), 0, margin + (int) max.get(0, 0), 2 * margin + (int)max.get(1, 0));

			RecogCanvas canvas2 = new RecogCanvas(pointset, max, 1);
			cp2.add(canvas2);
			canvas2.setBounds(0 ,margin,(int)max.get(0 ,0),(int)max.get(1,0));

			frame.setVisible(true);
		}
	}
}
