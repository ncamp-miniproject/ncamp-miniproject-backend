package category.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.model2.mvc.category.domain.Category;
import com.model2.mvc.common.MapperWithoutSpringInitializer;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class TestCategoryMapper {

    private static Logger log = LoggerFactory.getLogger(TestCategoryMapper.class);

    private SqlSession sqlSession;

    @Before
    public void init() {
        this.sqlSession = MapperWithoutSpringInitializer.initUnitTest("ProductMapper.clear", "CategoryMapper.clear");
    }

    @After
    public void after() {
        MapperWithoutSpringInitializer.afterUnitTest(this.sqlSession, "ProductMapper.clear", "CategoryMapper.clear");
    }

    @Test
    public void insert() {
        insertSample();
    }

    private List<Category> insertSample() {
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        this.sqlSession.insert("CategoryMapper.insert", category1);
        this.sqlSession.insert("CategoryMapper.insert", category2);
        return Arrays.asList(category1, category2);
    }

    @Test
    public void select() {
        List<Category> sample = insertSample();
        List<Category> found = this.sqlSession.selectList("CategoryMapper.findAll");
        assertThat(found.size()).isEqualTo(sample.size());
        assertThat(found).contains(sample.toArray(new Category[0]));
    }
}
