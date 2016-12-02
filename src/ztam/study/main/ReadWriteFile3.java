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

public class ReadWriteFile3 {
	public class Settgif{
		String name;
		String date;
		int status1;
		int lt1;
		int status2;
		int lt2;
		void setData(String i_name, String i_date, int i_status1, int i_lt1, int i_status2, int i_lt2){
			name = i_name;
			date = i_date;
			status1 = i_status1;
			lt1 = i_lt1;
			status2 = i_status2;
			lt2 = i_lt2;
		}
	}
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
			  Settgif s_tgif = new Settgif();
			  while((line  = b_reader.readLine()) != null){
			    System.out.println(line);
			    writer.println(line);
				String[] w_tgif = line.split(",",0);
				s_tgif.name = w_tgif[0];
				s_tgif.date = w_tgif[4];
				switch(w_tgif[1]){
				case "出席する":
					s_tgif.status1 = 1;
					break;
				default:
					s_tgif.status1 = 0;
					break;
				}
				switch(w_tgif[2]){
				case "エントリする":
					s_tgif.lt1 = 1;
					break;
				default:
					s_tgif.lt1 = 0;
					break;
				}
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
