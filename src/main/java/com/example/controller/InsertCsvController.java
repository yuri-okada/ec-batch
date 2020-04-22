package com.example.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.domain.Item;
import com.example.service.InsertCsvService;

/**
 * csvファイルをインサートするプログラムです.
 * 
 * @author yuri.okada
 *
 */
@Component
public class InsertCsvController implements CommandLineRunner{

	@Autowired
	private  InsertCsvService service;
	
	private static final Logger logger = LoggerFactory.getLogger(InsertCsvController.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 * csvファイルを読み込んで変換し、DBにインサート処理しています.
	 * それに加え、画像を表示できるように保存します.
	 * 
	 */
	@Override
	public void run(String... args) {//...可変長引数
		try {
			logger.info("取り込み開始");
			
			File f = new File("items2.csv");
			InputStreamReader osr = new InputStreamReader(new FileInputStream(f), "SJIS");// 文字化け対策
			BufferedReader br = new BufferedReader(osr);

			String line;

			List<String> dataList = new ArrayList<>();
			List<List<String>> bigList = new ArrayList<>();
			// 1行ずつCSVファイルを読み込む
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",", 0); // 行をカンマ区切りで配列に変換

				for (String elem : data) {
					dataList.add(elem);
					if (dataList.size() % 6 == 0) {
						bigList.add(dataList);
						dataList = new ArrayList<>();
					}
				}
			}
			// indexを削除
			bigList.remove(0);
			br.close();
			
			for (List<String> itemList : bigList) {
				Item item = new Item();
				item.setName(itemList.get(0));
				item.setDescription(itemList.get(1));
				item.setPriceM(Integer.parseInt(itemList.get(2)));
				item.setPriceL(Integer.parseInt(itemList.get(3)));
				item.setImagePath(itemList.get(4));
				item.setDeleted(Boolean.valueOf(itemList.get(5)));
				
//				//ec-okadaプロジェクトにimageを保存する.
//				String imageName=item.getImagePath();
//				InputStream stream = new ByteArrayInputStream(imageName.getBytes("utf-8"));
//
////				//ローカルのフォルダを参照し画像情報を取得
////				FileInputStream stream=new FileInputStream("C:\\Users\\chemi\\OneDrive\\デスクトップ\\研修\\画像"+"\\"+imageName);
////				System.out.println(stream);
//			
//				//resousesのフォルダにコピー
//				Files.copy(stream, Paths.get(
//						"C:\\env\\springworkspace\\ec2020-okada\\src\\main\\resources\\static\\img_aloha", imageName));
				service.insertItem(item);
			}			
			
			logger.info("取り込み完了");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
