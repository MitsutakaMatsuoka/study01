/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tbl3 {
	String name;
	int ninzu;

	public Tbl3(String name,int ninzu) {
		this.name = name;
		this.ninzu = ninzu;
	}
	@Override
	public String toString(){
		return this.name + "は" + this.ninzu + "名";
	}
	public static void main(String[] args){
		System.out.println("SISCO-TGIF参加者の部署・人数について確認しましょう。");
		System.out.println("平均、合計、それとも両方？");
		System.out.println();
		System.out.println("平均　：1");
		System.out.println("合計　：2");
		System.out.println("両方　：3");
		System.out.println();
		System.out.println("どれかを入力して");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			try {
				String line1 = input.readLine();
				switch (line1) {
				case "1":
					System.out.println("じゃ平均を求めよう");
					break;
				case "2":
					System.out.println("じゃ合計を求めよう");
					break;
				case "3":
					System.out.println("じゃ平均と合計を求めよう");
					break;
				default:
					System.out.println("1 ～ 3 のどれかだ！");
					System.exit(-1);
				}
				int ope = Integer.parseInt(line1);
				System.out.println("部署の数はいくつ？");
				line1 = input.readLine();
				System.out.println("なるほど " + line1 + " つだね");
				int count = Integer.parseInt(line1);
				Tbl3 matome[] = new Tbl3[count];
				String line2;
				String line3;
				for (int a = 0; a < count; a++) {
					System.out.println((a+1) + "つ目の部署名は？");
					line2 = input.readLine();
					System.out.println(line2 + "の参加者数は？(半角数字で)");
					line3 = input.readLine();
					matome[a] = new Tbl3(line2, Integer.parseInt(line3));
					System.out.println(matome[a].toString() + "だね");
				}
				System.out.println("ＯっＫ！");
				int total = 0;
				for (int a = 0; a < count; a++) {
					total = total + matome[a].ninzu;
				}
				double heikin = (double)total / count;
				System.out.print("部署は ");
				for (int a = 0; a < count; a++) {
					System.out.print(matome[a].name + " ");
				}
				System.out.println("で");
					switch (ope) {
				case 1:
					System.out.println(count + "つの部署の平均は " + heikin + " 名だね");
					break;
				case 2:
					System.out.println(count + "つの部署の合計は " + total + " 名だね");
					break;
				case 3:
					System.out.println(count + "つの部署の平均は " + heikin + " 名で");
					System.out.println(count + "つの部署の合計は " + total + " 名だね");
					break;
				default:
					break;
				}
			} catch (IOException e) {
				System.out.println(e);
			} catch (NumberFormatException e) {
				System.out.println("入力間違ってる！");
			}
	}
}