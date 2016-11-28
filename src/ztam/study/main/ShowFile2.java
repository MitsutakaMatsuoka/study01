/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ShowFile2 {

	public static void main(String[] args) {
		try{
//			  File file = new File("C:¥\Users¥¥Mitsutaka_Matsuoka.SISCO\\Desktop\\study\\java\\SISCOTGIF05.xlsx");
//			  File file = new File("C:\\java.txt");
			  File file = new File("C:\\SISCOTGIF05.csv");
			  BufferedReader read = new BufferedReader(new FileReader(file));

			  String line = read.readLine();
			  while(line != null){
			    System.out.println(line);

			    line = read.readLine();
			  }

			  read.close();
			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}
		}
}
