package jp.co.saison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class ReadTgifEnquete {

	public static void main(String[] args) {
		try{
//ファイルを読んでバッファに格納
			 BufferedReader b_reader = TgifUtil.read_file("E:\\java\\data\\SISCOTGIF05.csv");

//ファイルを出力
//			 PrintWriter writer = TgifUtil.write_file("E:\\java\\data\\SISCOTGIF05_edit.csv");

//バッファの内容をアレイリストに格納
			 ArrayList<ArrayList<String>> enqueteList = TgifUtil.makeTgifList(b_reader);

//			 input.close();
//			 i_stream.close();
			 b_reader.close();
//			 output.close();
//			 o_stream.close();
//			 b_writer.close();
//			 writer.close();

//アレイリストからＤＢに格納
			 TgifUtil.makeTgifDb(enqueteList);

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
