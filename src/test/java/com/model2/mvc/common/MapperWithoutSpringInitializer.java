package com.model2.mvc.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MapperWithoutSpringInitializer {

    public static SqlSession initUnitTest(String... initializingSql) {
        try (Reader reader = Resources.getResourceAsReader("mybatis-config/mybatis-config-without-spring.xml")) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
            SqlSession sqlSession = factory.openSession(true);
            for (String sql : initializingSql) {
                sqlSession.delete(sql);
            }
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void afterUnitTest(SqlSession sqlSession, String... destroySql) {
        for (String sql : destroySql) {
            sqlSession.delete(sql);
        }
        sqlSession.close();
    }
}
