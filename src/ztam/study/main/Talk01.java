package ztam.study.main;

import java.io.BufferedReader;

public class Talk01 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int age;
		System.out.println("Ｙｏｕ、名前は？");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				try {
					String line = reader.readLine();
					System.out.println("ＨＥＬＬＯ " + line);
					System.out.println(line + "は何歳なんだい？");
					line = reader.readLine();
					age = Integer.parseInt(line);
					System.out.println(age + "歳か、なるほどな。");
					System.out.println("ってことは 100年後には" + (age + 100) + "歳だな。");
	}

}
