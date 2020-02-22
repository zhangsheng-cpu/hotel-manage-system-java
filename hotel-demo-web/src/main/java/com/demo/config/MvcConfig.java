package com.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author 过往云烟~
 */
@Configuration
@EnableWebMvc
@ComponentScan({"com.demo.service","com.demo.controller", "com.demo.interceptor", "com.demo.exception"})
@MapperScan("com.demo.dao")
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class MvcConfig implements WebMvcConfigurer {

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(url);
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPlugins(pageInterceptor());
        PathMatchingResourcePatternResolver resourcePatternResolver =
                new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:mappers/**/*.xml");
        factoryBean.setMapperLocations(resources);

        factoryBean.setConfiguration(getConfiguration());

        return factoryBean.getObject();
    }

    //事务
    @Bean
    public PlatformTransactionManager transactionManager(){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }

    //分页插件
    private PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor = new PageInterceptor();

        Properties properties = new Properties();
        properties.put("supportMethodsArguments", "true");
        properties.put("reasonable", "true");
        pageInterceptor.setProperties(properties);
        return  pageInterceptor;
    }
    //sql日志
    private org.apache.ibatis.session.Configuration getConfiguration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setLogImpl(StdOutImpl.class);
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

//    -------------------------------------------------------------------------------
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
    //日期转换格式
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd hh:mm:ss"));
    }
    //拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//
//    }

    //静态资源的处理
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        ResourceHandlerRegistration resourceHandlerRegistration =
                registry.addResourceHandler("/static/**");
        resourceHandlerRegistration.addResourceLocations("classpath:/static/");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1736764878);
        return multipartResolver;
    }
}
