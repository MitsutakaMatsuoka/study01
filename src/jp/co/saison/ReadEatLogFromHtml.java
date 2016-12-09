package jp.co.saison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class ReadEatLogFromHtml {

	public static void main(String[] args) {
		try{
//ファイルを読んでバッファに格納
//			 BufferedReader b_reader = ShopRatingUtil.readHtmlToBuffer("E:\\java\\data\\tabelog.html");
			 BufferedReader b_reader = ShopRatingUtil.readHtmlToBuffer("E:\\java\\data\\tabelog3.html");

//バッファの内容をアレイリストに格納
			 ArrayList<ShopRating> shopRatingList = ShopRatingUtil.makeShopRatingList(b_reader);
			 b_reader.close();
//
//アレイリストからＤＢに更新・格納
			 ShopRatingUtil.insertShopRatingDb(shopRatingList);

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
