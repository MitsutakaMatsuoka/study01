/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Talk02 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ　　　
		int age;
		BufferedReader input;		//入力値の定義
		String line;				//入力値の格納先
		System.out.println("ユー、名前は？");
		input = new BufferedReader(new InputStreamReader(System.in));
				try {
					line = input.readLine();
					System.out.println("ＨＥＬＬＯ " + line);
					System.out.println(line + "は何歳なんだい？");
					line = input.readLine();
					age = Integer.parseInt(line);
					System.out.println(age + "歳か、なるほどな。");
					System.out.println("ってことは 100年後には" + (age + 100) + "歳だな。");
				} catch (IOException e) {
					System.out.println(e);
				} catch (NumberFormatException e) {
					System.out.println("年齢間違ってる！");
				}
	}

}
