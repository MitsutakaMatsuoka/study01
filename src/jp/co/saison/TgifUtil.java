package jp.co.saison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class TgifUtil {

//----------------------------------------------------------------------------------
	public static BufferedReader read_file(String filePath) throws FileNotFoundException, UnsupportedEncodingException, IOException{
		File r_file = new File(filePath);
		FileInputStream input = new FileInputStream(r_file);
		InputStreamReader i_stream = new InputStreamReader(input,"SJIS");
		BufferedReader b_reader = new BufferedReader(i_stream);
//		input.close();
//		i_stream.close();
		return b_reader;

	}

//----------------------------------------------------------------------------------
//	public static PrintWriter write_file(String filePath) throws FileNotFoundException, UnsupportedEncodingException{
//		File w_file = new File(filePath);
//		FileOutputStream output = new FileOutputStream(w_file);
//		OutputStreamWriter o_stream = new OutputStreamWriter(output,"SJIS");
//		BufferedWriter b_writer = new BufferedWriter(o_stream);
//		PrintWriter writer = new PrintWriter(b_writer,true);
//		return writer;
//	}


//----------------------------------------------------------------------------------
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
					enquete.setAttendStatus(1);
					break;
				default:
					enquete.setAttendStatus(0);
					break;
				}
				switch(splitReadLine[2]){
				case "エントリする":
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
	public static void   insertTgifDb(ArrayList<Enquete> enqueteList) throws IOException{
		Connection dbConnect = null;
		Statement dbStmt = null;
//		ResultSet dbResultSet = null;
		try {
//フィールド情報
			String dbDriver = "org.postgresql.Driver";							//ＤＢドライバ
//			String dbDriver = "/JavaStudy01/lib/postgresql-9.4.1212.jar";		//ＤＢドライバ
//			String dbServer = "172.16.91.121";									//ＤＢサーバー
			String dbServer = "localhost:5432/";								//ＤＢサーバー
			String dbName = "tgifdb";											//ＤＢ名称
			String dbUrl = "jdbc:postgresql://" + dbServer + dbName;
			String dbUser =  "postgres";										//ＤＢユーザー
			String dbPass =  "password";												//ＤＢパスワード
			Class.forName (dbDriver);
//			Class.forName("org.postgresql.Driver");

//データベースへ接続
			dbConnect = DriverManager.getConnection(dbUrl,dbUser,dbPass);
//ステートメントオブジェクトを生成
			dbStmt = dbConnect.createStatement();
//SQL実施
			System.out.println("");
			System.out.println("");
			System.out.println("＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊");
			System.out.println("DB insert開始");
			System.out.println("");

//SQL文を指定し実行
			for(Enquete enqueteCount :enqueteList){
				System.out.println("　　　insert実施　" + enqueteCount.getGuestName() + " さん");
				String dbSql = "insert into tbl_tgif values ('" +
						enqueteCount.getGuestName() + "','" +
						enqueteCount.getTgifNumber() + "'," +
						enqueteCount.getAttendStatus() + "," +
						enqueteCount.getLtStatus() + ")";
				System.out.println("　　　SQL文 = " + dbSql);
				int dbResult = dbStmt.executeUpdate(dbSql);
				System.out.println("DB insert結果 = " + dbResult);
			}
// データベースから切断
			dbStmt.close();
			dbConnect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
