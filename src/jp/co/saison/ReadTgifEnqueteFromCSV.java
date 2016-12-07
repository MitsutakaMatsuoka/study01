package jp.co.saison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class ReadTgifEnqueteFromCSV {

	public static void main(String[] args) {
		try{
//ファイルを読んでバッファに格納
			 BufferedReader b_reader = TgifUtil.readCsvToBuffer("E:\\java\\data\\SISCOTGIF05.csv");

//バッファの内容をアレイリストに格納
			 ArrayList<Enquete> enqueteList = TgifUtil.makeTgifList(b_reader);
			 b_reader.close();

//アレイリストからＤＢに更新・格納
			 TgifUtil.insertTgifDb(enqueteList,"tgifdb","tbl_tgif");

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
