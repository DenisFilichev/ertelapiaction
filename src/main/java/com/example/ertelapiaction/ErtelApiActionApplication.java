package com.example.ertelapiaction;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
public class ErtelApiActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErtelApiActionApplication.class, args);
    }

    @Component
    private static class PropertyConfig extends PropertySourcesPlaceholderConfigurer {
        public PropertyConfig() throws IOException {
            String separator = File.separator;
            File dir = new File("conf");
            dir.mkdir();
            String URL = separator.equals("\\") ? dir.getAbsolutePath().replaceAll("\\\\", "/") : dir.getAbsolutePath();
            System.out.println();
            System.out.println("---------------" + URL + "----------------");
            System.out.println();
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            List<Resource> resources = new ArrayList<>();
            System.out.println("URL=" + URL);
            resources.addAll(Arrays.asList(resolver.getResources("classpath:/*.properties")));
            resources.addAll(Arrays.asList(resolver.getResources("file:" + URL + "/*.properties")));

            Resource[] resourcesArray = new Resource[resources.size()];
            setLocations(resources.toArray(resourcesArray));
            for(Resource r : resourcesArray){
                System.out.println(r.getFilename());
            }
            Arrays.sort(resourcesArray, Comparator.comparing(Resource::getFilename));
            setIgnoreUnresolvablePlaceholders(true);
        }
    }

}
