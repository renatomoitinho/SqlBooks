# SQL BOOKS

write native jdbc and wrapper.

## it's work
> @query for native, 
> use JsonNode (jackson-databind API)
> unnecessary java beans

```java
@Queries("company") //optional book sql
public interface CompanyRepository extends Repository<Company, CompanyMapper> {

    @Query("select * from company where name =:name")
    List<Company> getCompanyByName(@Param("name") String name);
    ...
```
default use JsonNode 

```java
public interface CompanyRepository extends Repository<JsonNode, JsonNodeMapper> {

    @Query("select * from company where name =:name")
    Optional<JsonNode> getCompanyByName(@Param("name") String name);
    ...
```

## optional book (company.xml)
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

### Tech
* [Spring Data Jdbc] - Provides extensions to the JDBC support provided in the Spring Framework.

[Spring Data Jdbc]: <http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html>
