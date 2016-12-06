package jp.co.saison;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class ReadTgifEnqueteFromDB {

	public static void main(String[] args) {
		try{
//ＤＢを読んでアレイリストに格納
			ArrayList<Enquete> enqueteList = TgifUtil.selectTgifDb("tgifdb","tbl_tgif");

//アレイリストの内容をファイルに出力
			TgifUtil.writeCsv(enqueteList,"E:\\java\\data\\SISCOTGIF05_edit.csv");
//			ArrayList<Enquete> writer = TgifUtil.WriteCsv(enqueteList,"E:\\java\\data\\SISCOTGIF05_edit.csv");

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
