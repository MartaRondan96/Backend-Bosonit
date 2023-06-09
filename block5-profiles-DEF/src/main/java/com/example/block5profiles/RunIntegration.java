package com.example.block5profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Configuration
@Profile("INTEGRATION")
public class RunIntegration implements CommandLineRunner{

    @Value("${bd.url}")
    private String bdURL;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(bdURL);
    }
}
