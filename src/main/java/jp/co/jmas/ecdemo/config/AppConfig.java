package jp.co.jmas.ecdemo.config;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.seasar.doma.jdbc.Config;
import org.seasar.doma.jdbc.GreedyCacheSqlFileRepository;
import org.seasar.doma.jdbc.NoCacheSqlFileRepository;
import org.seasar.doma.jdbc.SqlFileRepository;
import org.seasar.doma.jdbc.dialect.Dialect;
import org.seasar.doma.jdbc.dialect.MysqlDialect;
import org.seasar.doma.jdbc.dialect.StandardDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class AppConfig implements Config {

	private DataSource dataSource;

	private Dialect dialect;

	private SqlFileRepository sqlFileRepository;

	public Dialect getDialectType(String s) {
		Dialect d;
		switch (s) {
		case "mysql":
			d = new MysqlDialect();

		default:
			d = new StandardDialect();
		}

		return d;
	}

	public AppConfig() {
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
