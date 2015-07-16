package br.com.test;

import br.com.config.ApplicationConfig;
import br.com.model.Company;
import br.com.repository.BaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by renato on 25/05/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
public class Connection {

    @Autowired
    private BaseRepository repository;

//    @Before
//    public void init() {
//        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        repository = applicationContext.getBean("repository",Repository.class);
//    }


    @Test
    public void create(){

        String sql = "CREATE TABLE COMPANY " +
                "(ID INT PRIMARY KEY     NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " AGE            INT     NOT NULL, " +
                " ADDRESS        CHAR(50), " +
                " SALARY         REAL)";

        repository.getTemplate().execute(sql);
    }

    @Test
    public void insert(){
        AtomicInteger integer = new AtomicInteger(nextVal());
        String sql1,sql2,sql3,sql4;

        sql1 = sql("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES ({0}, \"Paul\", 32, \"California\", 20000.00 )", integer);

        sql2 = sql("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES ({0}, \"Allen\", 25, \"Texas\", 15000.00 )", integer);

        sql3 = sql("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES ({0}, \"Teddy\", 23, \"Norway\", 20000.00 )", integer);

        sql4 = sql("INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
                "VALUES ({0}, \"Mark\", 25, \"Rich-Mond\", 65000.00 )", integer);

        repository.getTemplate().batchUpdate(new String[]{sql1, sql2, sql3, sql4});
    }

    @Test
    public void query(){
        repository.getTemplate().query("select * from company",Connection::simpleWrapper);
    }

    @Test
    public void beanParam(){

        Optional<Company> company = repository.getNamedTemplate()
                .query("select * from company where id =:id ",
                        Collections.singletonMap("id", 1),
                        Connection::companyWrapper).stream().findFirst();

        System.out.println( company.isPresent() ? company.get() : "No value present" );
    }

    public String sql(String insert, AtomicInteger integer){
        return MessageFormat.format(insert, integer.incrementAndGet());
    }

    public static Company companyWrapper(ResultSet rs, int line){
        Company company = new Company();
        try {
            company.id = rs.getInt("id");
            company.name = rs.getString("name");
            company.salary = rs.getLong("salary");
        }catch (SQLException e) {}
        return company;
    }

    public static Void simpleWrapper(ResultSet rs, int line) {
        try {
            System.out.println( rs.getInt("id") +"/"+ rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int nextVal(){
        return repository.getTemplate().queryForObject("select count(*) + 1 from company",Integer.class);
    }
}
