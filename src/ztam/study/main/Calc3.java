package ztam.study.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calc3 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		BufferedReader input1;
		BufferedReader input2;
		String name1;
		String name2;
		int age1;
		int age2;
		System.out.println("キミの名前は？");
		System.out.println("");
		input1 = new BufferedReader(new InputStreamReader(System.in));
		input2 = new BufferedReader(new InputStreamReader(System.in));
				try {
					String line = input1.readLine();
					System.out.println("ＨＥＬＬＯ " + line);
					System.out.println("");
					name1 = line;
					System.out.println(name1 + "は何歳なんだい？");
					System.out.println("");
					line = input1.readLine();
					age1 = Integer.parseInt(line);
					System.out.println(age1 + "歳か、なるほどな。");
					System.out.println("");
					System.out.println("ってことは 100年後には" + (age1 + 100) + "歳だな。");
					System.out.println("");
					System.out.println("");
					System.out.println("そっちのキミ、名前は？");
					System.out.println("");
					line = input2.readLine();
					System.out.println("ＨＥＬＬＯ " + line);
					System.out.println("");
					name2 = line;
					System.out.println(name2 + "は何歳なんだい？");
					System.out.println("");
					line = input2.readLine();
					age2 = Integer.parseInt(line);
					System.out.println(age2 + "歳か、なるほどな。");
					System.out.println("");
					System.out.println("ってことは 100年後には" + (age2 + 100) + "歳だな。");
					System.out.println("");
					System.out.println("");
					int heikin = (age1 + age2) / 2;
					System.out.println(name1 + " と " + name2 + " の年齢平均は" + heikin + "歳だな。");
				} catch (IOException e) {
					System.out.println(e);
				} catch (NumberFormatException e) {
					System.out.println("年齢間違ってる！");
				}
	}

}