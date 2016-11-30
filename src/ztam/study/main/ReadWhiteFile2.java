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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ReadWhiteFile2 {

	public static void main(String[] args) {
		try{
			  File r_file = new File("E:\\java\\data\\SISCOTGIF05.csv");
			  FileInputStream input = new FileInputStream(r_file);
			  InputStreamReader i_stream = new InputStreamReader(input,"SJIS");
			  BufferedReader b_reader = new BufferedReader(i_stream);

			  File w_file = new File("E:\\java\\data\\SISCOTGIF05w.csv");
			  FileOutputStream output = new FileOutputStream(w_file);
			  OutputStreamWriter o_stream = new OutputStreamWriter(output,"SJIS");
			  BufferedWriter b_writer = new BufferedWriter(o_stream);
			  PrintWriter writer = new PrintWriter(b_writer,true);

			  String line;
			  while((line  = b_reader.readLine()) != null){
			    System.out.println(line);
			    writer.println(line);
			  }
			  input.close();
			  i_stream.close();
			  b_reader.close();
			  output.close();
			  o_stream.close();
			  b_writer.close();
			  writer.close();

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}
	}
}
