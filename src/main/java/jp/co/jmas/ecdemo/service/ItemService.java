package jp.co.jmas.ecdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.jmas.ecdemo.dao.ItemRepository;
import jp.co.jmas.ecdemo.entity.Item;

@Service
@Transactional
public class ItemService {
	@Autowired
	//@Qualifier("itemRepositoryImpl")
	private ItemRepository itemRepository;
	
	public List<Item> getItems(){
		return itemRepository.selectAll();
	}
	
	public Item getItem(String itemId) {
		itemId ="1";
		//serch-resultの商品idで検索
		return itemRepository.findOne(itemId);
	}
}
