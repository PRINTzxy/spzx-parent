package website.yny84666.spzx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import website.yny84666.spzx.common.security.annotation.EnableCustomConfig;
import website.yny84666.spzx.common.security.annotation.EnableRyFeignClients;

@EnableCustomConfig
@EnableRyFeignClients
@SpringBootApplication
public class SpzxUserApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(SpzxUserApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  系统模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
