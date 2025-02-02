package com.employeemanagement.common;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SqlRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ResourceLoader resourceLoader;

    @PostConstruct
    public void executeSqlOnStartup() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data.txt");
        Path path = resource.getFile().toPath();
        String content = new String(Files.readAllBytes(path));
        String[] contentArray = content.split("\n");
        for (String sql : contentArray) {
            try {
                System.out.println("SQL :" + sql.trim());
                jdbcTemplate.execute(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
