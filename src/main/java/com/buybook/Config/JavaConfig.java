package com.buybook.Config;

import javax.sql.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.*;

@Configuration
@ComponentScan("com.buybook")
public class JavaConfig {
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource
				.setUrl("jdbc:mysql://localhost:3306/graDB?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("rootoor");
		return dataSource;
	}
}
