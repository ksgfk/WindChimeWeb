package com.github.ksgfk.windchimeweb.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ksgfk.windchimeweb.entity.User;
import com.github.ksgfk.windchimeweb.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class JsonDataToDbConverter {
    public static void main(String[] args) throws IOException {
        var path = Paths.get("C:\\Users\\ksgfk\\Desktop\\users");
        var file = path.toFile();
        var list = file.listFiles();

        var inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        var factory = new SqlSessionFactoryBuilder().build(inputStream);
        var session = factory.openSession();
        var users = session.getMapper(UserMapper.class);

        var mapper = new ObjectMapper();
        User u = null;
        for (var f : list) {
            try {
                var stream = new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8);
                u = mapper.readValue(f, User.class);
                var b = u.getName().getBytes(StandardCharsets.UTF_8);
                String str = new String(b, StandardCharsets.UTF_8);
                u.setName(str);
                users.insertUser(u);
                stream.close();
            } catch (Exception e) {
                throw e;
            }
        }

        session.commit();
        session.close();
    }
}
