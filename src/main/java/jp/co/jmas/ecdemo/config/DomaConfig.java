package jp.co.jmas.ecdemo.config;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.boot.autoconfigure.DomaConfigBuilder;
import org.seasar.doma.jdbc.ClassHelper;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.GreedyCacheSqlFileRepository;
import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

public class DomaConfig implements Config {
	
	private DataSource dataSource;

	private Dialect dialect;

	private SqlFileRepository sqlFileRepository;
	
	private ClassHelper classHelper = new MyClassHelper();

	private static class MyClassHelper implements ClassHelper {
		@SuppressWarnings("unchecked")
		@Override
		public <T> Class<T> forName(String className) throws Exception {
			return (Class<T>)this.getClass().getClassLoader().loadClass(className);
		}
	}

	@Bean
	public DomaConfigBuilder domaConfigBuilder() {
		return new DomaConfigBuilder() {
			public ClassHelper classHelper() {
				return classHelper;
			}
		};
	}
	


	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = new TransactionAwareDataSourceProxy(dataSource);
	}

	@SuppressWarnings("deprecation")
	@Autowired
	public void setDialect(@Value("${doma.dialect}") String domaDialect)
			throws ClassNotFoundException, IllegalAccessException, InstantiationException {
		//this.dialect = (Dialect) Class.forName(domaDialect).newInstance();
		this.dialect = getDialectType(domaDialect);
	}

	private Dialect getDialectType(String domaDialect) {
		// TODO Auto-generated method stub
		return null;
	}



	@Autowired
	public void setSqlFileRepository(@Value("${spring.profiles.active}") String springProfilesActive) {
		// develop モードの時は SQL ファイルがキャッシュされないようにする
		if (StringUtils.equals(springProfilesActive, "develop")) {
			this.sqlFileRepository = new NoCacheSqlFileRepository();
		} else {
			this.sqlFileRepository = new GreedyCacheSqlFileRepository();
		}
	}

	@Override
	public DataSource getDataSource() {
		return this.dataSource;
	}

	@Override
	public Dialect getDialect() {
		return this.dialect;
	}

	@Override
	public SqlFileRepository getSqlFileRepository() {
		return this.sqlFileRepository;
	}

}
