package jp.co.saison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class ReadEatLogFromHtml {

	public static void main(String[] args) {
		try{
//ファイルを読んでバッファに格納
			 BufferedReader b_reader = ShopRathingUtil.readHtmlToBuffer("E:\\java\\data\\tabelog.html");

////バッファの内容をアレイリストに格納
			 ArrayList<ShopRating> shopRatingList = ShopRathingUtil.makeShopRatingList(b_reader);
			 b_reader.close();
//
//アレイリストからＤＢに更新・格納
			 ShopRathingUtil.insertShopRathingDb(shopRatingList);

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
