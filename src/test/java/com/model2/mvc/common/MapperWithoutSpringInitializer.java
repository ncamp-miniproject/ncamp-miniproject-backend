package com.model2.mvc.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MapperWithoutSpringInitializer {

    public static SqlSession initUnitTest(String initializingSql) {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config/mybatis-config-without-spring.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = factory.openSession(true);
            sqlSession.delete(initializingSql);
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void afterUnitTest(SqlSession sqlSession, String sqlId) {
        sqlSession.delete(sqlId);
        sqlSession.close();
    }
}
