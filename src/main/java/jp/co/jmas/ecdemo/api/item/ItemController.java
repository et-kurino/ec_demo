package jp.co.jmas.ecdemo.api.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import jp.co.jmas.ecdemo.entity.Item;
import jp.co.jmas.ecdemo.service.ItemService;


@Controller
public class ItemController {
	@Autowired
	@Qualifier("itemService")	
	ItemService itemService;
	/*
	 * 1.->mainアクセス（一覧表示　＋　ユーザ検索フォーム）
	 * 2.->detailアクセス（一覧内の商品画像（path格納）　detailリンクから）
	 * 2-2.->searchアクセス(ユーザ検索フォームから)
	 * */
	
	//main 商品全件渡す
	@GetMapping("/item/search-list")
	public String main(Model model) {
		
		//model.addAttribute("item", new Item());
		
		model.addAttribute("items", itemService.getItems());
		return "item/search-list";
	}
	
	
	//search 商品検索結果渡す
	@PostMapping("/item/search-list")
	public String search(@ModelAttribute Item item) {
		
		
		return "item/search-list"
		;}
	
	
	//detail 詳細遷移
	@RequestMapping(value="/item/{itemId}", method = RequestMethod.GET)
	public String detail(@PathVariable("itemId") String itemId, ModelAndView mav) {
		
		//clickされたitemIdで個別itemに飛ぶ
		mav.addObject("itemData", itemService.getItem(itemId));
		mav.setViewName("item/item");
		return "item";
	}
	
	
	
	
}
