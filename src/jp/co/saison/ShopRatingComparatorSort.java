package jp.co.saison;

import java.util.Comparator;

public class ShopRatingComparatorSort implements Comparator<ShopRating>{

	@Override
	public int compare(ShopRating sortKey1,ShopRating sortKey2){
		return sortKey1.getShopPoint() > sortKey2.getShopPoint() ? -1 : 1;
	}
}