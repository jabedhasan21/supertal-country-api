package com.supertal.assignment.seeds;

import com.codesnippets4all.json.parsers.JSONParser;
import com.codesnippets4all.json.parsers.JsonParserFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.Document;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author Jabed Hasan<jabedhasan21@gmail.com>
 * @created on 27/01/2022 - 13:42
 */
@Configuration
public class DatabaseSeeder {


    @Value("classpath:/seed-data.json")
    private Resource seedData;


    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {
        }

        // Don't save _class to mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return mappingConverter;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public Boolean run(MongoTemplate mongoTemplate) throws IOException {
        mongoTemplate.dropCollection("users");
        JSONParser parser = getJsonParser();
        Map<String, Object> jsonMap = parser.parseJson(convertSeedDataInputStreamToString());
        ObjectMapper mapper = new ObjectMapper();

        jsonMap.forEach((key, value) -> {
            List dbObjects = (List) jsonMap.get(key);

            for (Object dbObject : dbObjects) {
                Document document = null;
                try {
                    document = new Document(parser.parseJson(mapper.writeValueAsString(dbObject)));
                    mongoTemplate.insert(document, key);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });

        return true;
    }

    private JSONParser getJsonParser() {
        JsonParserFactory factory = JsonParserFactory.getInstance();
        return factory.newJsonParser();
    }

    private String convertSeedDataInputStreamToString() {
        StringBuilder textBuilder = new StringBuilder();
        try {
            InputStream inputStream = seedData.getInputStream();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textBuilder.toString();
    }
}
