import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import javax.print.DocFlavor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class LoggerAOP {
    @Autowired private Logger logger;

    @Around("@annotation(LogExecution)")
    public void loggingAdvice(JoinPoint jp) {
        String calledMethod = jp.toShortString();
        logger.log("calledMethod   "+calledMethod);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext();
        config.register(Config.class);
        config.refresh();

        NameRepository repository = config.getBean(NameRepository.class);
        System.out.println(repository.getNames());
    }
}

@Component
class NameRepository{
    @LogExecution
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        names.add("John");
        names.add("Mary");
        return names;
    }
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogExecution {}


interface Logger {
    public void log(String data);
}


@Configuration
@EnableAspectJAutoProxy
@Import({LoggerAOP.class, NameRepository.class})
class Config {
    @Bean
    public Logger logger() {
        return (message) -> System.out.println(message);
    }

}


