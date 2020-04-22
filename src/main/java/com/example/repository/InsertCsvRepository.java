package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * itemsテーブルへの登録操作をするリポジトリ.
 * @author yuri.okada
 *
 */
@Repository
public class InsertCsvRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 商品情報を登録します.
	 * <pre>
	 * このメソッドでは、IDをプログラムで採番しています。
	 * ・商品テーブルから重複しないIDを取ってくる
	 * ・取ってきたIDを使ってインサートする
	 * これを別スレッドに移ることなく確実に処理するためにsynchronizedをつけています。
	 * </pre>
	 * @param item　商品情報
	 * @return インサートした商品情報
	 */
	synchronized public Item insertItem(Item item) {
		// IDの採番
		item.setId(getPrimaryId());
		
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);

		// インサート処理
		String insertSql = "INSERT INTO items(id,name,description,price_m,price_l,image_path,deleted)"
				+ " VALUES(:id,:name,:description,:priceM,:priceL,:imagePath,:deleted)";
		Integer result=template.update(insertSql, param);
		System.out.println("挿入件数"+result);
		return item;
	}
	
	
	/*
	 * 商品テーブルの中で一番大きいID + 1(プライマリーキー=主キー)を取得する.
	 * 
	 * @return テーブル内で一番値が大きいID + 1.データがない場合は1
	 */
	private Integer getPrimaryId() {
		try {
			Integer maxId = template.queryForObject("SELECT MAX(id) FROM items;", new MapSqlParameterSource(),
					Integer.class);
			return maxId + 1;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return 1;
		}
	}
}
