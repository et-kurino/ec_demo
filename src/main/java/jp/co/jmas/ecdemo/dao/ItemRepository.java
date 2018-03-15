package jp.co.jmas.ecdemo.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;

import jp.co.jmas.ecdemo.config.AppConfig;
import jp.co.jmas.ecdemo.entity.Item;


@Dao(config = AppConfig.class)
@ConfigAutowireable
public interface ItemRepository {
	@Select
	Item findOne(String itemCode);
	@Select
	List<Item> selectAll();
	@Select
	List<Item> searchResult();
}
