# jdbc book

write native jdbc and wrapper.

## it's work
```java
@Queries("company") //optional book sql
public interface CompanyRepository extends Repository<Company, CompanyMapper> {

    @Query("select * from company where name =:name")
    List<Company> getCompanyByName(@Param("name") String name);
    ...
```

## optional book
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
    <comment>This is easy!</comment>
    <entry key="getCompanyByName">
        <![CDATA[
            select * from company where name =:name
        ]]>
    </entry>
```

## simple wrapper
```java
public class CompanyMapper implements RowMapper<Company> {
    @Override
    public Company mapRow(ResultSet rs, int i) throws SQLException {
        Company company = new Company();
        company.setId(rs.getInt("id"));
        company.setAddress(rs.getString("address"));
        company.setAge(rs.getInt("age"));
        company.setName(rs.getString("name"));

        return company;
    }
```

## This test
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class CompanyRepositoryTest {

    @Autowired
    CompanyRepository repository;

    @Test
    public void test() {
        repository.getCompanyByName("Allen").forEach(System.out::println);
    }
}
```
