package bback.test.proviider.service.member.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInAccessor implements Anonymous {

    private final String memberId;
    private String password;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isUnknown() {
        return false;
    }

    public static SignInAccessor of(String memberId, String password) {
        return new SignInAccessor(memberId, password);
    }
}
