package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.InsertCsvRepository;

/**
 * item情報の登録を操作するサービス.
 * 
 * @author yuri.okada
 *
 */
@Service
@Transactional
public class InsertCsvService {

	@Autowired
	private InsertCsvRepository repository;

	/**
	 * 商品情報を登録します.
	 * @param item 商品情報
	 */
	public void insertItem(Item item) {
		repository.insertItem(item);
	}
	
}
	