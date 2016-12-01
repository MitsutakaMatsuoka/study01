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
import java.util.ArrayList;

public class TgifUtil {


	public static BufferedReader read_file(String filePath) throws FileNotFoundException, UnsupportedEncodingException, IOException{
		File r_file = new File(filePath);
		FileInputStream input = new FileInputStream(r_file);
		InputStreamReader i_stream = new InputStreamReader(input,"SJIS");
		BufferedReader b_reader = new BufferedReader(i_stream);
//		input.close();
//		i_stream.close();
		return b_reader;

	}


	public static PrintWriter write_file(String filePath) throws FileNotFoundException, UnsupportedEncodingException{
		File w_file = new File(filePath);
		FileOutputStream output = new FileOutputStream(w_file);
		OutputStreamWriter o_stream = new OutputStreamWriter(output,"SJIS");
		BufferedWriter b_writer = new BufferedWriter(o_stream);
		PrintWriter writer = new PrintWriter(b_writer,true);
		return writer;
	}


	public static ArrayList<String> makeTgifList(BufferedReader b_reader) throws IOException{
		String readLine;
		ArrayList<String> enqueteList = new ArrayList<String>();
		Enquete enqueteLine = new Enquete();
		while((readLine  = b_reader.readLine()) != null){
			System.out.println(readLine);
//		    writer.println(readLine);
			String[] splitReadLine = readLine.split(",",0);


			int i = 1;
			for (String nakami: splitReadLine) {
				System.out.println(i + " " + nakami);
				i++;
			}

			enqueteLine.setGuestName(splitReadLine[0]);
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

			//			enqueteList.add(enqueteLine);
			System.out.print(enqueteLine.getGuestName());
			System.out.print(enqueteLine.getTgifNumber());
			System.out.print(enqueteLine.getAttendStatus());
			System.out.println(enqueteLine.getLtStatus());
		}
		return enqueteList;
	}
}
