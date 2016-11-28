/**
 * matzテスト用
 *
 */

package ztam.study.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ShowWhiteFile2 {

	public static void main(String[] args) {
		try{
			  File r_file = new File("C:\\SISCOTGIF05.csv");
			  FileInputStream input = new FileInputStream(r_file);
			  InputStreamReader stream = new InputStreamReader(input,"SJIS");
			  BufferedReader b_reader = new BufferedReader(stream);

			  File w_file = new File("C:\\SISCOTGIF05w.csv");
			  FileWriter output = new FileWriter(w_file);
			  BufferedWriter b_writer = new BufferedWriter(output);
			  PrintWriter writer = new PrintWriter(b_writer,true);

			  String line;
			  while((line  = b_reader.readLine()) != null){
			    System.out.println(line);
			    writer.println(line);
			  }
			  input.close();
			  stream.close();
			  b_reader.close();
			  output.close();
			  b_writer.close();
			  writer.close();
			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}
	}
}
