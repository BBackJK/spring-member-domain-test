package bback.test.proviider.service.auth;

import bback.test.proviider.dao.MemberDao;
import bback.test.proviider.dao.scheme.MemberTable;
import bback.test.proviider.dto.MemberRequest;
import bback.test.proviider.events.EventCatcher;
import bback.test.proviider.events.sample.LoginAspectHandler;
import bback.test.proviider.provider.PasswordEncodeProvider;
import bback.test.proviider.service.member.handler.Anonymous;
import bback.test.proviider.service.member.handler.Member;
import bback.test.proviider.service.member.handler.UserFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberDao memberDao;
    private final PasswordEncodeProvider passwordEncodeProvider;

    @Override
    public void register(MemberRequest.Register registerRequest) {
        memberDao.baseFindById(registerRequest.getMemberId()).ifPresent(m -> new RuntimeException("중복된 아이디입니다."));
        Member signUpAccessor = registerRequest.toMember();
        passwordEncodeProvider.hash(signUpAccessor);
        MemberTable memberTable = signUpAccessor.toTable();
        memberDao.baseSave(memberTable);
    }

    @Override
    @EventCatcher(LoginAspectHandler.class)
    public Member login(MemberRequest.Login loginRequest) {
        MemberTable memberTable = memberDao.baseGetById(loginRequest.getMemberId(), () -> new RuntimeException("아이디나 비밀번호가 일치하지 않습니다."));
        Member savedMember = UserFactory.getMember(memberTable);
        Anonymous loginTrial = loginRequest.toAnonymous();
        passwordEncodeProvider.ifNotMatchThrow(loginTrial, savedMember, () -> new RuntimeException("아이디나 비밀번호가 일치하지 않습니다."));
        return savedMember;
    }
}
