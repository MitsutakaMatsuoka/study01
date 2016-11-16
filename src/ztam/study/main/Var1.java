package ztam.study.main;

public class Var1 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int s;
		int m = 60;
		int h = 60;
		int day = 24;
		int year = 365;
		s = 1;
		System.out.println("１分は　" + (s * m) + "　秒です。");
		System.out.println("１時間は　" + (s * m * h) + "　秒です。");
		System.out.println("１日は　" + (s * m * h * day) + "　秒です。");
		System.out.println("１年は　" + (s * m * h * day * year) + "　秒です。");
	}

}
