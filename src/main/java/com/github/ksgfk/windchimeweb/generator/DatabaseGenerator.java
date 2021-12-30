package com.github.ksgfk.windchimeweb.generator;

import org.apache.ibatis.io.Resources;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseGenerator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        InputStream io = Resources.getResourceAsStream("generatorConfig.xml");
        List<String> warning = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warning);
        Configuration cfg = cp.parseConfiguration(io);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator gen = new MyBatisGenerator(cfg, callback, warning);
        gen.generate(null);
        for (var g : gen.getGeneratedJavaFiles()) {
            System.out.println(g.getFileName());
        }
        for (var g : gen.getGeneratedXmlFiles()) {
            System.out.println(g);
        }
    }
}
