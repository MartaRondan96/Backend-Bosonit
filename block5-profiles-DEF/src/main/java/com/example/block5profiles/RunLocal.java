package com.example.block5profiles;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Configuration
@Profile("LOCAL")
public class RunLocal implements CommandLineRunner{
    @Value("${bd.url}")
    private String bdURL;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(bdURL);
    }


}
