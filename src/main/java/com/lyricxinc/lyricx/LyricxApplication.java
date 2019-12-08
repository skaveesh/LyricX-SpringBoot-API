package com.lyricxinc.lyricx;

//import com.lyricxinc.lyricx.core.LyricxUrlProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

//TODO
@SpringBootApplication
//@EnableConfigurationProperties(LyricxUrlProperties.class)
public class LyricxApplication {

    public static void main(String[] args) {

        SpringApplication.run(LyricxApplication.class, args);
    }

}
