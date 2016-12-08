package jp.co.saison;

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
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ShopRathingUtil {

//----------------------------------------------------------------------------------
//【ＣＳＶファイルを読み込みバッファに格納】
	public static BufferedReader readHtmlToBuffer(String filePath) throws FileNotFoundException, UnsupportedEncodingException, IOException{
		File r_file = new File(filePath);
		FileInputStream input = new FileInputStream(r_file);
		InputStreamReader i_stream = new InputStreamReader(input,"UTF8");
		BufferedReader b_reader = new BufferedReader(i_stream);
//		input.close();
//		i_stream.close();
		return b_reader;

	}

//----------------------------------------------------------------------------------
//【バッファを読み込みアレイリストに格納】
	public static ArrayList<ShopRating> makeShopRatingList(BufferedReader b_reader) throws IOException{
//フィールド定義
		String readLine;												//入力引数の格納先
		ArrayList<ShopRating> shopRathingList = new ArrayList<ShopRating>();	//戻り値の格納用

//引数入力～編集～リスト格納
		int lineAdoption;
		int startWord;
		int endWord;
		ShopRating shopRating = new ShopRating();//出力オブジェクトの格納先
		while((readLine  = b_reader.readLine()) != null){
			ShopRating shopRatingWork = new ShopRating();//出力オブジェクトの格納先
//ヘッダー行は除外
			if(readLine.indexOf("list-rst__rst-name-target cpy-rst-name") > -1){
				System.out.println("----------------------------------------------------------------------------");
				System.out.print("【入力行採用】店舗名・エリア・ジャンル ");
				System.out.println(readLine.indexOf("list-rst__rst-name-target cpy-rst-name") + "文字目～");
				lineAdoption = 1;
			} else {
				if(readLine.indexOf("tb-rating__val tb-rating__val--strong list-rst__rating-val") > -1){
					System.out.print("【入力行採用】評価値 ");
					System.out.println(readLine.indexOf("tb-rating__val tb-rating__val--strong list-rst__rating-val") + "文字目～");
					lineAdoption = 2;
				} else {
					if(readLine.indexOf("list-rst__rvw-count-num cpy-review-count") > -1){
						System.out.print("【入力行採用】Ｒｖ数 ");
						System.out.println(readLine.indexOf("list-rst__rvw-count-num cpy-review-count") + "文字目～");
						lineAdoption = 3;
					} else {
//						System.out.println("【入力行不採用】");
						lineAdoption = 0;
					}
				}
			}
//			ShopRating shopRating = new ShopRating();//出力オブジェクトの格納先
//採用行判定
			switch(lineAdoption){
			case 0://不採用行は処理なし   target="_blank">  </a><span
				break;
			case 1:
				startWord = readLine.indexOf("target=\"_blank\">") + 16;
				endWord = readLine.indexOf("</a><span class=");
				System.out.println("　　　　　　　" + readLine.substring(startWord,endWord));
				shopRating.setShopName(readLine.substring(startWord,endWord));

				startWord = readLine.indexOf("list-rst__area-genre cpy-area-genre") + 38;
				endWord = readLine.indexOf(" / ");
				System.out.println("　　　　　　　" + readLine.substring(startWord,endWord));
				shopRating.setShopArea(readLine.substring(startWord,endWord));

				startWord = readLine.indexOf(" / ") + 3;
				endWord = readLine.indexOf("）</span>");
				System.out.println("　　　　　　　" + readLine.substring(startWord,endWord));
				shopRating.setShopGenre(readLine.substring(startWord,endWord));
				System.out.println("");

				break;
			case 2:
				startWord = readLine.indexOf("tb-rating__val tb-rating__val--strong list-rst__rating-val") + 60;
				endWord = startWord + 4;
				System.out.println("　　　　　　　" + readLine.substring(startWord,endWord));
				shopRating.setShopPoint(readLine.substring(startWord,endWord));
				System.out.println("");

				break;
			case 3:
				startWord = readLine.indexOf("list-rst__rvw-count-num cpy-review-count") + 42;
				endWord = readLine.indexOf("</em>件");
				System.out.println("　　　　　　　" + readLine.substring(startWord,endWord));
				shopRating.setShopReviewCount(readLine.substring(startWord,endWord));
				System.out.println("");

				break;
			}
//リスト格納
			switch(lineAdoption){
			case 0:
			case 1:
			case 2:
				break;
			case 3:
//出力内容の確認
				System.out.println("【オブジェクト編集内容】");
				System.out.print("　　　");
				System.out.print(shopRating.getShopName() + "/");
				System.out.print(shopRating.getShopArea() + "/");
				System.out.print(shopRating.getShopGenre() + "/");
				System.out.print(shopRating.getShopPoint() + "/");
				System.out.print(shopRating.getShopReviewCount());
				System.out.println("");
				System.out.println("");
				shopRatingWork = shopRating;

//オブジェクト化した店舗評価内容をアレイリストに格納
				shopRathingList.add(shopRatingWork);
				shopRating = new ShopRating();//格納後初期化

//リストに格納したオブジェクトの確認
				System.out.println("【アレイリスト内容（１件）】");
				System.out.println("　　　" + shopRathingList.get(shopRathingList.size()-1));
				System.out.println();
			}

		}
//リスト全体内容の確認
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("【リスト内容ＡＬＬ】");
		for (int listCount = 0; listCount < shopRathingList.size(); listCount++){
			System.out.println("　　　No." + listCount + "　" + shopRathingList.get(listCount));
		}
		return shopRathingList;
	}

//----------------------------------------------------------------------------------
//【アレイリストを読み込みＤＢに登録・更新】
	public static void   insertShopRathingDb(ArrayList<ShopRating> shopRathingList) throws IOException{
		Connection dbConnect = null;
		Statement dbStmt = null;
//		ResultSet dbResultSet = null;
		try {
//フィールド情報
			String dbDriver = "org.postgresql.Driver";							//ＤＢドライバ
			String dbServer = "localhost:5432/";								//ＤＢサーバー
			String dbUrl = "jdbc:postgresql://" + dbServer + "eatLogdb";
			String dbUser =  "postgres";										//ＤＢユーザー
			String dbPass =  "password";										//ＤＢパスワード
			ResultSet dbResultSet = null;
			int dbResult;
			int dbSelectResult;
			Class.forName (dbDriver);

//データベースへ接続
			dbConnect = DriverManager.getConnection(dbUrl,dbUser,dbPass);
//ステートメントオブジェクトを生成
			dbStmt = dbConnect.createStatement();
//SQL実施
			System.out.println("");
			System.out.println("");
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.println("【追加・更新の判定を行い挿入もしくは更新を実施】");
			System.out.println("");
//SQL文発行

//SQL文を指定し実行
			for(ShopRating ShopRatingCount :shopRathingList){
//追加・更新の判定（SELECT）
				System.out.println("　　　select実施　対象DB = eatLogdb　対象TBL = shopRating_tbl");
				String dbSql = "select * from shopRating_tbl " +
						" where \"shopName\" = '" + ShopRatingCount.getShopName() +
						"' and \"shopArea\" = '" + ShopRatingCount.getShopArea() +
						"'";
				System.out.println("　　　発行するSQL文の内容 = " + dbSql); //SQL文内容確認
//SQL文発行（SELECT）
				dbResultSet = dbStmt.executeQuery(dbSql);
				if (dbResultSet.next() == true){
					dbSelectResult = 1;
				} else {
					dbSelectResult = 0;
				}
				switch(dbSelectResult){
				case(1):
					System.out.println("　　　　同一Ｋｅｙデータ存在");
					if (dbResultSet.getString("shopGenre") == ShopRatingCount.getShopGenre() &&
						dbResultSet.getString("shopPoint") == ShopRatingCount.getShopPoint() &&
						dbResultSet.getString("shopReviewCount") == ShopRatingCount.getShopReviewCount()){
						System.out.println("　　　　データ内容一致");
						System.out.println("");
					} else {
						System.out.println("　　　　★★データ内容不一致／更新実施★★");
//SQL文発行（UPDATE）
						dbSql = "update shopRating_tbl " +
								" set \"shopGenre\" = " + ShopRatingCount.getShopGenre() +
								", \"shopPoint\" = " + ShopRatingCount.getShopPoint() +
								", \"shopReviewCount\" = " + ShopRatingCount.getShopReviewCount() +
								" where \"shopName\" = '" + ShopRatingCount.getShopName() +
								"' and \"shopArea\" = '" + ShopRatingCount.getShopArea() +
								"'";
						System.out.println("　　　　SQL文 = " + dbSql);
						dbResult = dbStmt.executeUpdate(dbSql);
						System.out.println("　　　　DB 更新結果 = " + dbResult);
						System.out.println("");
					}
					break;
				default:
					System.out.println("　　　　★★同一Ｋｅｙデータ無し★★");
					System.out.println("　　　　insert実施　店舗名 = " + ShopRatingCount.getShopName());
					dbSql = "insert into shopRating_tbl values ('" +
							ShopRatingCount.getShopName() + "','" +
							ShopRatingCount.getShopArea() + "'," +
							ShopRatingCount.getShopGenre() + "'," +
							ShopRatingCount.getShopPoint() + "," +
							ShopRatingCount.getShopReviewCount() + ")";
					System.out.println("　　　　SQL文 = " + dbSql);
//SQL文発行（INSERT）
					dbResult = dbStmt.executeUpdate(dbSql);
					System.out.println("　　　　DB insert結果 = " + dbResult);
					System.out.println("");
				}
			}
// データベースから切断
			dbStmt.close();
			dbConnect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
//----------------------------------------------------------------------------------
//【ＤＢを読み込みアレイリストに格納】
	public static ArrayList<Enquete> selectTgifDb(String dbName,String tblName) throws IOException{
		Connection dbConnect = null;
		Statement dbStmt = null;
		ResultSet dbResultSet = null;
		ArrayList<Enquete> enqueteList = new ArrayList<Enquete>();		//戻り値の格納用
		try {
//フィールド情報
			String dbDriver = "org.postgresql.Driver";							//ＤＢドライバ
			String dbServer = "localhost:5432/";								//ＤＢサーバー
//			String dbName = "tgifdb";											//ＤＢ名称は引数
			String dbUrl = "jdbc:postgresql://" + dbServer + dbName;
			String dbUser =  "postgres";										//ＤＢユーザー
			String dbPass =  "password";										//ＤＢパスワード
			Class.forName (dbDriver);


//データベースへ接続
			dbConnect = DriverManager.getConnection(dbUrl,dbUser,dbPass);
//ステートメントオブジェクトを生成
			dbStmt = dbConnect.createStatement();
//SQL実施
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.println("【DB select開始】");
			System.out.println("");

//SQL文を作成し実行
			System.out.println("　　　select実施　対象DB　= " + dbName + "　対象TBL = " + tblName);
			String dbSql = "select * from " + tblName;
//SQL文内容確認
			System.out.println("　　　発行するSQL文の内容 = " + dbSql); //SQL文内容確認
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.println("");
//SQL文発行
			dbResultSet = dbStmt.executeQuery(dbSql);
//SQL発行結果確認＆アレイリストに格納
			while(dbResultSet.next()){
				Enquete enquete = new Enquete();								//出力オブジェクトの格納先
			    String guestName = dbResultSet.getString("guestname");
			    String tgifNumber = dbResultSet.getString("tgifnumber");
				int attendStatus = dbResultSet.getInt("attendstatus");
				int ltStatus = dbResultSet.getInt("ltstatus");
 //ＤＢ取得結果内容の確認
				System.out.println("　　　DB select結果　= " + guestName + " " + tgifNumber + " " + attendStatus + " " + ltStatus);
 //出力内容の編集＆オブジェクト化
				enquete.setGuestName(guestName);
			    enquete.setTgifNumber(tgifNumber);
			    enquete.setAttendStatus(attendStatus);
			    enquete.setLtStatus(ltStatus);
 //出力内容の確認
			    System.out.print("　　　編集内容　　　 = ");
				System.out.print(enquete.getGuestName() + " ");
				System.out.print(enquete.getTgifNumber() + " ");
				System.out.print(enquete.getAttendStatus() + " ");
				System.out.print(enquete.getLtStatus());
				System.out.println("");
//オブジェクト化したアンケート内容をリストに格納
				enqueteList.add(enquete);

//リストに格納したオブジェクトの確認
				System.out.print("　　　object内容　　 = ");
				System.out.println("No." + enqueteList.size() + "　" + enqueteList.get(enqueteList.size()-1));
				System.out.println("");

			}
//リスト全体内容の確認
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.println("【リスト内容ＡＬＬ】");
			for (int listCount = 0; listCount < enqueteList.size(); listCount++){
				System.out.println("　　　No." + listCount + "　" + enqueteList.get(listCount));
			}
//			File r_file = new File(filePath);
//			FileInputStream input = new FileInputStream(r_file);
//			InputStreamReader i_stream = new InputStreamReader(input,"SJIS");
//			BufferedReader b_reader = new BufferedReader(i_stream);
//データベースから切断
			dbStmt.close();
			dbConnect.close();
			dbResultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return enqueteList;
	}

//----------------------------------------------------------------------------------
//【アレイリストを読み込みＣＳＶファイルを書き込み】
	public static void   writeCsv(ArrayList<Enquete> enqueteList,String filePath) throws IOException{
		File w_file = new File(filePath);
		FileOutputStream output = new FileOutputStream(w_file);
		OutputStreamWriter o_stream = new OutputStreamWriter(output,"SJIS");
		BufferedWriter b_writer = new BufferedWriter(o_stream);
		PrintWriter writer = new PrintWriter(b_writer,true);

		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("【ＣＳＶ出力開始】");

//SQL文を指定し実行
		for(Enquete enqueteCount :enqueteList){
			System.out.println("　　　Ｗｒｉｔｅ　" + enqueteCount.getGuestName() + " さん");
			writer.print(enqueteCount.getGuestName());
			writer.print(",");
			writer.print(enqueteCount.getTgifNumber());
			writer.print(",");
			writer.print(enqueteCount.getAttendStatus());
			writer.print(",");
			writer.print(enqueteCount.getLtStatus());
			writer.println();
		}
//ファイルに書き出す
		writer.close();
//終了メッセージ
		System.out.println("ＣＳＶ出力完了" + "ファイル = " + filePath);
			}

}
