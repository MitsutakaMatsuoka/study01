package jp.co.saison;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
//	public static ArrayList<String> makeTgifList(BufferedReader b_reader) throws IOException{
	public static ArrayList<ArrayList<String>> makeTgifList(BufferedReader b_reader) throws IOException{
		String readLine;
//		ArrayList<String> enqueteList = new ArrayList<String>();
		ArrayList<ArrayList<String>> enqueteList = new ArrayList<ArrayList<String>>();
		Enquete enqueteLine = new Enquete();
		while((readLine  = b_reader.readLine()) != null){
//入力ライン内容の確認
			System.out.println("【入力ライン内容】");
			System.out.println(readLine);
//		    writer.println(readLine);
			String[] splitReadLine = readLine.split(",",0);


//有馬さん
			Enquete enquate = new Enquete();


//スプリット内容の確認
			System.out.println("【スプリット内容】");
			int i = 1;
			for (String contentReadLine: splitReadLine) {
				System.out.println(i + " " + contentReadLine);
				i++;
			}

//出力内容の編集
			enqueteLine.setGuestName(splitReadLine[0]);

			enquate.setGuestName(splitReadLine[0]);


			enqueteLine.setTgifNumber(splitReadLine[5]);
			switch(splitReadLine[1]){
			case "出席する":
				enqueteLine.setAttendStatus(1);
				break;
			default:
				enqueteLine.setAttendStatus(0);
				break;
			}
			switch(splitReadLine[2]){
			case "エントリする":
				enqueteLine.setLtStatus(1);
				break;
			default:
				enqueteLine.setLtStatus(0);
				break;
			}
//出力内容を配列に格納
			String enqueteTBL[] = new String[4];
			enqueteTBL[0] = enqueteLine.getGuestName();
			enqueteTBL[1] = enqueteLine.getTgifNumber();
			enqueteTBL[2] = String.valueOf(enqueteLine.getAttendStatus());
			enqueteTBL[3] = String.valueOf(enqueteLine.getLtStatus());
//配列内容の確認
			System.out.println("【配列内容】");
			for(i = 0;i<4;i++){
				System.out.print(enqueteTBL[i] + " ");
			}
			System.out.println();

//出力内容を１次元リストに格納
			ArrayList<String> enqueteSubList = new ArrayList<String>();
			enqueteSubList.add(enqueteLine.getGuestName());
			enqueteSubList.add(enqueteLine.getTgifNumber());
			enqueteSubList.add(String.valueOf(enqueteLine.getAttendStatus()));
			enqueteSubList.add(String.valueOf(enqueteLine.getLtStatus()));

//１次元リスト内容の確認
			System.out.println("【１次元リスト内容】");
			for (i = 0; i < enqueteSubList.size();i++){
				System.out.print(enqueteSubList.get(i)+ " ");
			}
			System.out.println();
			System.out.println();
			System.out.println();

//１次元リストを２次元リストに格納
			enqueteList.add(enqueteSubList);



		}
//２次元リスト内容の確認
		System.out.println("【２次元リスト内容】");
		for (int i = 0; i < enqueteList.size();i++){
			System.out.println(i + " " + enqueteList.get(i));
		}

		return enqueteList;
	}
//----------------------------------------------------------------------------------
//	public static void   makeTgifDb(ArrayList<ArrayList<String>> enqueteList) throws IOException{
//		try {
//			 // ドライバクラスをロード
//			Class.forName("org.postgresql.Driver");
//			// データベースへ接続
//			Connection dbConnnect = DriverManager.getConnection("jdbc:postgresql:tgifdb","postgres","");
//			// ステートメントオブジェクトを生成
//			Statement dbStatement = dbConnnect.createStatement();
//			String readListLine;
//			while((readListLine  = enqueteList != null)){
//				String[] dbReadLine = enqueteList.get();
//				// SQL文を指定
//				String sql = "INSERT INTO tbl_tgif VALUE('" + dbReadLine + ")'";
//			}
//			// データベースから切断
//			dbStatement.close();
//			dbConnnect.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
}
