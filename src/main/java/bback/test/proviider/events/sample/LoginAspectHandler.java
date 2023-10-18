package bback.test.proviider.events.sample;

import bback.test.proviider.events.EventAspectHandler;
import bback.test.proviider.service.member.handler.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoginAspectHandler implements EventAspectHandler<Member> {
    @Override
    public void handle(Member data) {
        log.info("LoginAspectHandler data :: {}", data);
    }
}
