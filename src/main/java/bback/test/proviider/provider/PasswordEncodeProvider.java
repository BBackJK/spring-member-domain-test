package bback.test.proviider.provider;

import bback.test.proviider.service.member.handler.Anonymous;
import bback.test.proviider.service.member.handler.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

@Component
public class PasswordEncodeProvider {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void hash(Member member) {
        member.hashPassword(passwordEncoder);
    }

    public PasswordEncodeProvider ifNotMatch(Anonymous anonymous, Member member, Runnable runnable) throws RuntimeException {
        if (!this.match(anonymous, member)) {
            runnable.run();
        }
        return this;
    }

    public <X extends Throwable> PasswordEncodeProvider ifNotMatchThrow(Anonymous anonymous, Member member, Supplier<? extends X> exceptionSupplier) throws X {
        if (!this.match(anonymous, member)) {
            throw exceptionSupplier.get();
        }
        return this;
    }

    public PasswordEncodeProvider ifMatch(Anonymous anonymous, Member member, BiConsumer<Anonymous, Member> biConsumer) throws RuntimeException {
        if (this.match(anonymous, member)) {
            biConsumer.accept(anonymous, member);
        }
        return this;
    }

    public boolean match(Anonymous anonymous, Member member) throws RuntimeException {
        if (anonymous.isUnknown()) {
            throw new RuntimeException("비교할 대상이 아닙니다.");
        }
        return this.passwordEncoder.matches(
                anonymous.getPassword(), member.getPassword()
        );
    }
}
