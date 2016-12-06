package ztam.study.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.saison.Enquete;

public class SampleList {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ


		Enquete enq = new Enquete();
		List<Object> list = new ArrayList<>();
		List<String> list1 = new ArrayList<>();
		List<Enquete> list2 = new ArrayList<>();

		list.add(enq);


//		list1.add(enq);


		list2.add(enq);

		Iterator it = list.iterator();
		while (it.hasNext())
		{
			Enquete val = (Enquete)it.next();
		    val.getGuestName();
		}


	}

}
