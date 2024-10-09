package website.yny84666.spzx.channel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import website.yny84666.spzx.common.security.annotation.EnableCustomConfig;
import website.yny84666.spzx.common.security.annotation.EnableRyFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableCustomConfig
@EnableRyFeignClients
public class SpzxChannelApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(SpzxChannelApplication.class, args);
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
