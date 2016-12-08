package jp.co.saison;

public class ShopRating{

	//カプセル化するために、フィールドをprivate指定する
	private String shopName;
	private String shopArea;
	private String shopGenre;
	private String shopPoint;
	private String shopReviewCount;

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
	public String getShopPoint() {
		return shopPoint;
	}
	public void setShopPoint(String shopPoint) {
		this.shopPoint = shopPoint;
	}
	public String getShopReviewCount() {
		return shopReviewCount;
	}
	public void setShopReviewCount(String shopReviewCount) {
		this.shopReviewCount = shopReviewCount;
	}
}
