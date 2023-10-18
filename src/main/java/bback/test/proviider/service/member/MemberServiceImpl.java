package bback.test.proviider.service.member;

import bback.test.proviider.dao.MemberDao;
import bback.test.proviider.dao.scheme.MemberTable;
import bback.test.proviider.service.member.handler.Member;
import bback.test.proviider.service.member.handler.UserFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberDao memberDao;

    @Override
    public Member detail(String memberId) {
        MemberTable memberModel = memberDao.baseGetById(memberId, () -> new RuntimeException("사용자 정보가 존재하지 않습니다."));
        return UserFactory.getMember(memberModel);
    }
}
