package jp.co.saison;

public class ShopRating{

	//カプセル化するために、フィールドをprivate指定する
	private String shopName;
	private String shopArea;
	private String shopGenre;
	private float shopPoint;
	private int shopReviewCount;

	@Override
	public String toString() {
		return shopName + " " + shopArea + " " + shopGenre + " " + shopPoint + " " + shopReviewCount;
	}




	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopArea() {
		return shopArea;
	}
	public void setShopArea(String shopArea) {
		this.shopArea = shopArea;
	}
	public String getShopGenre() {
		return shopGenre;
	}
	public void setShopGenre(String shopGenre) {
		this.shopGenre = shopGenre;
	}
	public float getShopPoint() {
		return shopPoint;
	}
	public void setShopPoint(float shopPoint) {
		this.shopPoint = shopPoint;
	}
	public int getShopReviewCount() {
		return shopReviewCount;
	}
	public void setShopReviewCount(int shopReviewCount) {
		this.shopReviewCount = shopReviewCount;
	}
}
