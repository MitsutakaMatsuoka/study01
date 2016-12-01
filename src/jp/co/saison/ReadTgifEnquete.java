package jp.co.saison;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;



public class ReadTgifEnquete {

	public static void main(String[] args) {
		try{
//			FILE-READ-Definition
			 BufferedReader b_reader = TgifUtil.read_file("E:\\java\\data\\SISCOTGIF05.csv");

//			FILE-WRITE-Definition
//			 PrintWriter writer = TgifUtil.write_file("E:\\java\\data\\SISCOTGIF05_edit.csv");

			 TgifUtil.makeTgifList(b_reader);

//			 TgifUtil.WriteTgifDb();


//			  String line;
//			  Enquete s_tgif = new Enquete();
//			  while((line  = b_reader.readLine()) != null){
//			    System.out.println(line);
//			    writer.println(line);
//				String[] w_tgif = line.split(",",0);
//				s_tgif.name = w_tgif[0];
//				s_tgif.date = w_tgif[4];
//				switch(w_tgif[1]){
//				case "出席する":
//					s_tgif.status1 = 1;
//					break;
//				default:
//					s_tgif.status1 = 0;
//					break;
//				}
//				switch(w_tgif[2]){
//				case "エントリする":
//					s_tgif.lt1 = 1;
//					break;
//				default:
//					s_tgif.lt1 = 0;
//					break;
//				}
//			  }
//			  input.close();
//			  i_stream.close();
			  b_reader.close();
//			  output.close();
//			  o_stream.close();
//			  b_writer.close();
//			  writer.close();

			}catch(FileNotFoundException e){
			  System.out.println(e);
			}catch(IOException e){
			  System.out.println(e);
		}

	}

}
