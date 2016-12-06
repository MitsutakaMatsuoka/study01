package jp.co.saison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;



public class ReadTgifEnqueteFromDB {

	public static void main(String[] args) {
		try{
//ＤＢを読んでバッファに格納
			BufferedReader b_reader = TgifUtil.selectTgifDb("tgifdb","tbl_tgif");

//バッファの内容をアレイリストに格納
//			ArrayList<Enquete> enqueteList = TgifUtil.makeTgifList(b_reader);
//			b_reader.close();
//
////アレイリストの内容をファイルに出力
//			PrintWriter writer = TgifUtil.write_file("E:\\java\\data\\SISCOTGIF05_edit.csv");
//
////アレイリストからＤＢに格納
//			 TgifUtil.insertTgifDb(enqueteList);



			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
