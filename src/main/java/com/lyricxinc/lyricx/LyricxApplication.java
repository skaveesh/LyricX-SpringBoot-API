package com.lyricxinc.lyricx;

//import com.lyricxinc.lyricx.core.LyricxUrlProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Lyricx application.
 */
//TODO
@SpringBootApplication
//@EnableConfigurationProperties(LyricxUrlProperties.class)
public class LyricxApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        SpringApplication.run(LyricxApplication.class, args);
    }

}
