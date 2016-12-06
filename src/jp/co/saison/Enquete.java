package jp.co.saison;

public class Enquete{

	//カプセル化するために、フィールドをprivate指定する
	private String guestName;
	private String tgifNumber;
	private int attendStatus;
	private int ltStatus;

	//クラスがnewされたときに呼ばれる
//	SetTgif(){
//
//	}

	@Override
	public String toString(){
		return guestName + " " + tgifNumber + " " + attendStatus + " " + ltStatus;
	}

	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getTgifNumber() {
		return tgifNumber;
	}
	public void setTgifNumber(String tgifNumber) {
		this.tgifNumber = tgifNumber;
	}
	public int getAttendStatus() {
		return attendStatus;
	}
	public void setAttendStatus(int attendStatus) {
		this.attendStatus = attendStatus;
	}
	public int getLtStatus() {
		return ltStatus;
	}
	public void setLtStatus(int ltStatus) {
		this.ltStatus = ltStatus;
	}

//	public Enquete(String guestName,String tgifNumber,int attendStatus,int ltStatus ){
//		this.guestName = guestName;
//		this.tgifNumber = tgifNumber;
//		this.attendStatus = attendStatus;
//		this.ltStatus = ltStatus;
//	}




}
