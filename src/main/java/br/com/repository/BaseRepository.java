package br.com.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by renato on 25/05/15.
 */

@Repository
public class BaseRepository {

    @Autowired
    Environment env;

    private JdbcTemplate template;
    private NamedParameterJdbcTemplate namedTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.template = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public NamedParameterJdbcTemplate getNamedTemplate() {
        return namedTemplate;
    }

    public String getQuery(String key) {
        return env.getProperty(key);
    }
}
