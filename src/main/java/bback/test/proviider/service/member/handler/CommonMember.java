package bback.test.proviider.service.member.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor(staticName = "of")
public class CommonMember implements Member {

    private final String memberId;
    private String name;
    private String password;
    private String email;
    private int age;
    private char gender;
    private final Grade type;

    @Override
    public void hashPassword(PasswordEncoder passwordEncoder) throws RuntimeException {
        if (!hasPassword()) {
            throw new RuntimeException("해싱할 비밀번호가 없습니다.");
        }
        this.password = passwordEncoder.encode(this.password);
    }
}
