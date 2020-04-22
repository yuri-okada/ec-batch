package com.example.domain;

import java.util.List;

/**
 * 商品情報のドメイン.
 * @author yuri.okada
 *
 */
public class Item {
	
	/**商品ID*/
	private Integer id;
	
	/**名前*/
	private String name;
	
	/**説明*/
	private String description;
	
	/**Mの価格*/
	private Integer priceM;
	
	/**Lの価格*/
	private Integer priceL;
	
	/**画像のパス*/
	private String imagePath;
	
	/**削除フラグ*/
	private Boolean deleted;
	



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriceM() {
		return priceM;
	}

	public void setPriceM(Integer priceM) {
		this.priceM = priceM;
	}

	public Integer getPriceL() {
		return priceL;
	}

	public void setPriceL(Integer priceL) {
		this.priceL = priceL;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", priceM=" + priceM + ", priceL="
				+ priceL + ", imagePath=" + imagePath + ", deleted=" + deleted + "]";
	}

}
