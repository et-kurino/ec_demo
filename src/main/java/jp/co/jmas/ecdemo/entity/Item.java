package jp.co.jmas.ecdemo.entity;

import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.jdbc.entity.NamingType;
import org.springframework.stereotype.Component;

import lombok.Data;

@Entity(naming = NamingType.SNAKE_LOWER_CASE) 
@Component
@Data
public class Item {
	@Id
	String itemCd;
	String itemNm;
	String itemVal;
	String imgSrc;
	String explain;
	String lastUpdate;
}
