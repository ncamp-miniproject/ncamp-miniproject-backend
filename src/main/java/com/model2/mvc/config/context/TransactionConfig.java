package com.model2.mvc.config.context;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;

@Configuration
public class TransactionConfig {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Value("${jdbc.maxActive}")
    private String maxActive;

    @Value("${jdbc.initialSize}")
    private String initialSize;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(Integer.parseInt(maxActive));
        dataSource.setInitialSize(Integer.parseInt(initialSize));
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    public TransactionAttributeSource transactionAttributeSource() {
        NameMatchTransactionAttributeSource transactionAttributeSource = new NameMatchTransactionAttributeSource();
        DefaultTransactionAttribute transactionAttribute = new DefaultTransactionAttribute();
        transactionAttribute.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        transactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        transactionAttributeSource.addTransactionalMethod("get*", transactionAttribute);
        transactionAttributeSource.addTransactionalMethod("add*", transactionAttribute);
        transactionAttributeSource.addTransactionalMethod("update*", transactionAttribute);
        return transactionAttributeSource;
    }

    @Bean
    public TransactionInterceptor transactionInterceptor(PlatformTransactionManager transactionManager,
                                                         TransactionAttributeSource transactionAttributeSource) {
        TransactionInterceptor transactionInterceptor = new TransactionInterceptor();
        transactionInterceptor.setTransactionManager(transactionManager);
        transactionInterceptor.setTransactionAttributeSource(transactionAttributeSource);
        return transactionInterceptor;
    }

    @Bean
    public AspectJExpressionPointcut transactionPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.model2.mvc..*ServiceImpl.*(..))");
        return pointcut;
    }

    @Bean
    public Advisor transactionAdvisor(TransactionInterceptor transactionInterceptor, AspectJExpressionPointcut pointcut) {
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setAdvice(transactionInterceptor);
        advisor.setExpression(pointcut.getExpression());
        return advisor;
    }
}
