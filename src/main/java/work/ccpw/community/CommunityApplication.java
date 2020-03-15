package work.ccpw.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author tockc
 */
@SpringBootApplication
@MapperScan("work.ccpw.community.mapper")
public class CommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }
}
