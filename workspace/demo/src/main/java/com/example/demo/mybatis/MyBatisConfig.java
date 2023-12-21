package com.example.demo.mybatis;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
/*
* 스프링 컨테이너 빈에 등록할때 사용하는 어노테이션은 @Component이다
* 빈을 등록하는 어노테이션이 하나만 있는게 아니다.
* 그중 하나가@Configuration이다.
* 특병한 기능이 있는것이 아니라 빈의 사용 목적을 구분짓기 위해 이름이 나위어져 있다.
* @Configuration은 설정을 위한 빈이라고 안ㄹ려주고 등록하는 것이다.
* */

@RequiredArgsConstructor
public class MyBatisConfig {
    //ApplicationContext 객체는 스프링 부트의 핵심 인터페이스 이다.
    //우리가 스프링 컨테이너라고 부르는 누리적인 구조를 실ㅊ화한 것이다.
    // 이 객체를 불러와서 설정을 해줄 수 있다.
    private final ApplicationContext applicationContext;


//    이 어노테이션은 외부파일의 설정 값들을 자바 객체로 가져롱ㄹ때 사용한다
//    스프링부트에서는 .application.properties라는 파일에 설정 값을 전부 작성하기 때문에
//    해당 파일에서 특정 속성을 가져올 때 사용하는 어노테이션인다
//    prefix는 어노테이션의 속성이며
//    접두사를 성정할 때 사용한다
//    spring.datasource.hikari 로 시작하는 설정 값을 전부 가져오라는 의미이다.
//    가져온 값을 new HikariConfig();로 만든 객체의 필드에 바인딩한다.
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig(){
//        Hikari란?
//        HikariCP(히카리 커넥션 풀) 라이브러리를 의미한다.
//        빠르고 가볍고 설정이 쉽고 안전성이 높다
//        jsp에서 사용한 DBCP는 아파치 톰갯에서 지원하는 라이브러이였다.
//        Spring Boot에서는 Hikari를 사용한다
//        new HikariConfig()로 객체를 생성했는데 이 객체의 필드에 설정값을 저장하기 위해
//        @ConfigurationProperties(prefix="spring.datasource.hikari") 를 사용한것이다.
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(){
//       DataSource 란?
//        DataSource객체는 CP를 관리하고CP에 있는 커넥션 객체를 제공, 반납받는다.
//        모든 CP라이브러리는 DataSource객체를 사용한다.
//        CP를 사용하려면 DataSource객체가 필요하며 DataSource객체를 만들기 위해서는 DB정보가 필요하다
//        해당 정보를 HikariConfig객체가 가지고 있다.
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));

        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
