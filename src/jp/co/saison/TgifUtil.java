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

public class TgifUtil {

//----------------------------------------------------------------------------------
//【ＣＳＶファイルを読み込みバッファに格納】
	public static BufferedReader readCsvToBuffer(String filePath) throws FileNotFoundException, UnsupportedEncodingException, IOException{
		File r_file = new File(filePath);
		FileInputStream input = new FileInputStream(r_file);
		InputStreamReader i_stream = new InputStreamReader(input,"SJIS");
		BufferedReader b_reader = new BufferedReader(i_stream);
//		input.close();
//		i_stream.close();
		return b_reader;

	}

//----------------------------------------------------------------------------------
//【バッファを読み込みアレイリストに格納】
	public static ArrayList<Enquete> makeTgifList(BufferedReader b_reader) throws IOException{
//フィールド定義
		String readLine;												//入力引数の格納先
		ArrayList<Enquete> enqueteList = new ArrayList<Enquete>();	//戻り値の格納用

//引数入力～編集～リスト格納
		while((readLine  = b_reader.readLine()) != null){

//ヘッダー行は除外
			switch(readLine.substring(0,3)){
			case "登録者"://ヘッダー行は"登録者"で始まるのでその場合は処理なし
				break;
			default:
				Enquete enquete = new Enquete();//出力オブジェクトの格納先

//入力引数内容の確認
				System.out.println("－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
				System.out.println("【入力引数内容】");
				System.out.println("　　　" + readLine);

//入力ライン内容をＳＰＬＩＴ化
				String[] splitReadLine = readLine.split(",",0);

//スプリット内容の確認
				System.out.println("【スプリット内容】");
				int splitCount = 1;
				for (String contentReadLine: splitReadLine) {
					System.out.println("　　　" + splitCount + " " + contentReadLine);
					splitCount++;
				}

//出力内容の編集＆オブジェクト化
				enquete.setGuestName(splitReadLine[0]);
				enquete.setTgifNumber(splitReadLine[5].substring(26,36));
				switch(splitReadLine[1]){
				case "出席する":
				case "1":
					enquete.setAttendStatus(1);
					break;
				default:
					enquete.setAttendStatus(0);
					break;
				}
				switch(splitReadLine[2]){
				case "エントリする":
				case "1":
					enquete.setLtStatus(1);
					break;
				default:
					enquete.setLtStatus(0);
					break;
				}
//出力内容の確認
				System.out.println("【編集内容】");
				System.out.print("　　　");
				System.out.print(enquete.getGuestName() + "　");
				System.out.print(enquete.getTgifNumber() + "　");
				System.out.print(enquete.getAttendStatus() + "　");
				System.out.print(enquete.getLtStatus());
				System.out.println("");


//オブジェクト化したアンケート内容をリストに格納
				enqueteList.add(enquete);

//リストに格納したオブジェクトの確認
				System.out.println("【編集内容／オブジェクト内容】");
				System.out.println("　　　No." + enqueteList.size() + "　" + enqueteList.get(enqueteList.size()-1));
				System.out.println();
			}
		}
//リスト全体内容の確認
		System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
		System.out.println("【リスト内容ＡＬＬ】");
		for (int listCount = 0; listCount < enqueteList.size(); listCount++){
			System.out.println("　　　No." + listCount + "　" + enqueteList.get(listCount));
		}

		return enqueteList;

	}

//----------------------------------------------------------------------------------
//【アレイリストを読み込みＤＢに登録・更新】
	public static void   insertTgifDb(ArrayList<Enquete> enqueteList,String dbName,String tblName) throws IOException{
		Connection dbConnect = null;
		Statement dbStmt = null;
//		ResultSet dbResultSet = null;
		try {
//フィールド情報
			String dbDriver = "org.postgresql.Driver";							//ＤＢドライバ
			String dbServer = "localhost:5432/";								//ＤＢサーバー
			String dbUrl = "jdbc:postgresql://" + dbServer + dbName;
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
			for(Enquete enqueteCount :enqueteList){
//追加・更新の判定（SELECT）
				System.out.println("　　　select実施　対象DB　= " + dbName + "　対象TBL = " + tblName);
				String dbSql = "select * from " + tblName +
						" where \"guestName\" = '" + enqueteCount.getGuestName() +
						"' and \"tgifNumber\" = '" + enqueteCount.getTgifNumber() +
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
					if (dbResultSet.getInt("attendStatus") == enqueteCount.getAttendStatus() &&
						dbResultSet.getInt("ltStatus") == enqueteCount.getLtStatus()){
						System.out.println("　　　　データ内容一致");
						System.out.println("");
					} else {
						System.out.println("　　　　★★データ内容不一致／更新実施★★");
//SQL文発行（UPDATE）
						dbSql = "update " + tblName +
								" set \"attendStatus\" = " + enqueteCount.getAttendStatus() +
								", \"ltStatus\" = " + enqueteCount.getLtStatus() +
								" where \"guestName\" = '" + enqueteCount.getGuestName() +
								"' and \"tgifNumber\" = '" + enqueteCount.getTgifNumber() +
								"'";
						System.out.println("　　　　SQL文 = " + dbSql);
						dbResult = dbStmt.executeUpdate(dbSql);
						System.out.println("　　　　DB 更新結果 = " + dbResult);
						System.out.println("");
					}
					break;
				default:
					System.out.println("　　　　★★同一Ｋｅｙデータ無し★★");
					System.out.println("　　　　insert実施　" + enqueteCount.getGuestName() + " さん");
					dbSql = "insert into tbl_tgif values ('" +
					enqueteCount.getGuestName() + "','" +
					enqueteCount.getTgifNumber() + "'," +
					enqueteCount.getAttendStatus() + "," +
					enqueteCount.getLtStatus() + ")";
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
