/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tbl1 {

	public static void main(String[] args) {
		System.out.println("いくつの数を足す？");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				try {
					String line = input.readLine();
					System.out.println("なるほど " + line + " つだね");
					int count = Integer.parseInt(line);
					int table[] = new int[count];
					for (int a = 0; a < count; a++) {
						System.out.println((a+1) + "つめは？");
						line = input.readLine();
						table[a] = Integer.parseInt(line);
					}
					System.out.println("ＯＫ");
					int total = 0;
					for (int a = 0; a < count; a++) {
						total = total + table[a];
					}
					System.out.println("合計は" + total + "だね");
				} catch (IOException e) {
					System.out.println(e);
				} catch (NumberFormatException e) {
					System.out.println("年齢間違ってる！");
				}
	}

}
