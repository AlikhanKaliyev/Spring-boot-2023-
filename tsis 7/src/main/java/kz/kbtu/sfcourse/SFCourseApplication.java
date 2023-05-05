package kz.kbtu.sfcourse;

import kz.kbtu.sfcourse.domain.model.MyGreeting;
import kz.kbtu.sfcourse.domain.model.PrintWithSemicolon;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SFCourseApplication {
    public static void main(String[] args) {
        SpringApplication.run(SFCourseApplication.class, args);
        MyGreeting g = new MyGreeting(new PrintWithSemicolon());
        g.print("test");
    }

}
