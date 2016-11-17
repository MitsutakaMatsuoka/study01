/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tbl2 {

	public static void main(String[] args) {
		System.out.println("たし算と掛け算どっち？");
		System.out.println();
		System.out.println("たし算　：1");
		System.out.println("掛け算　：2");
		System.out.println("どっちかを入力して");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				try {
					String line = input.readLine();
					switch (line) {
					case "1":
					case "2":
						break;
					default:
						System.out.println("1 か 2 だ！");
						System.exit(-1);
					}
					int ope = Integer.parseInt(line);
					System.out.println("いくつの数で計算する？");
					line = input.readLine();
					System.out.println("なるほど " + line + " つだね");
					int count = Integer.parseInt(line);
					int table[] = new int[count];
					switch (ope) {
					case 1:
						System.out.println("じゃ、たし算するよ");
						for (int a = 0; a < count; a++) {
							System.out.println((a+1) + "つ目の値は？");
							line = input.readLine()
									;
							table[a] = Integer.parseInt(line);
						}
						System.out.println("ＯＫ");
						int total = 0;
						for (int a = 0; a < count; a++) {
							total = total + table[a];
						}
						System.out.println("合計は" + total + "だね");
						break;
					case 2:
						System.out.println("じゃ、掛け算するよ");
						for (int a = 0; a < count; a++) {
							System.out.println((a+1) + "つ目の値は？");
							line = input.readLine();
							table[a] = Integer.parseInt(line);
						}
						System.out.println("ＯＫ");
						total = table[0];
						for (int a = 1; a < count; a++) {
							total = total * table[a];
						}
						System.out.println("合計は" + total + "だね");
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
