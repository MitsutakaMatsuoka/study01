/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReadWriteFile1 {

	public static void main(String[] args) {
		try{
//			  File file = new File("C:\\java.txt");
			  File r_file = new File("C:\\SISCOTGIF05.csv");
			  BufferedReader read = new BufferedReader(new FileReader(r_file));
//			  BufferedReader b_reader = new BufferedReader(new InputStreamReader(new FileInputStream(r_file),"SJIS"));

			  File w_file = new File("C:\\SISCOTGIF05w.csv");
			  PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(w_file,true)));

			  String line = read.readLine();
			  while(line != null){
			    System.out.println(line);
			    writer.println(line);

			    line = read.readLine();
			  }
			  read.close();
			  writer.close();
			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
			}
		}

}
