package kr.co.atg.apds.komipo_main.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = {
        "kr.co.atg.apds.komipo_main.bend.mapper.db2",
        "kr.co.atg.apds.komipo_main.fend.mapper.db2",
        "kr.co.atg.apds.komipo_main.send.mapper.db2",
}, sqlSessionFactoryRef = "db2SqlSessionFactory")
public class Dbsource2Config {

    @Bean(name="db2DataSource")
    @ConfigurationProperties(prefix = "spring.db2.datasource")
    public DataSource db2DataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="db2SqlSessionFactory")
    public SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource, ApplicationContext applicationContext) throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2DataSource);
        sqlSessionFactoryBean.setMapperLocations(
                applicationContext.getResources("classpath:/mapper/db2/**/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name="db2SqlSessionTemplate")
    public SqlSessionTemplate db2SqlSessionTemplate(SqlSessionFactory db2SqlSessionFactory) {
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }

}
