package bback.test.proviider.service.member.handler;

import bback.test.proviider.dao.scheme.MemberTable;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public final class UserFactory {

    private static final Anonymous UNKNOWN_INSTANCE = new Unknown();

    private UserFactory() throws IllegalAccessException {
        throw new IllegalAccessException(" is factory class ");
    }

    public static Member getMember(@NonNull MemberTable memberTable) {
        Grade grade = memberTable.getType();
        switch (grade) {
            case ADMIN:
                return AdminMember.of(
                        memberTable.getMemberId()
                        , memberTable.getEmail()
                        , memberTable.getPassword()
                        , memberTable.getName()
                        , memberTable.getAge()
                        , memberTable.getGender()
                        , memberTable.getType()
                );
            case COMMON:
                return CommonMember.of(
                        memberTable.getMemberId()
                        , memberTable.getEmail()
                        , memberTable.getPassword()
                        , memberTable.getName()
                        , memberTable.getAge()
                        , memberTable.getGender()
                        , memberTable.getType()
                );
            default:
                throw new IllegalStateException("존재하지 않는 등급입니다.");
        }
    }

    public static List<Member> getMember(List<MemberTable> memberSchemeList) {
        return memberSchemeList.stream().map(UserFactory::getMember).collect(Collectors.toList());
    }

    public static Anonymous getUnKnown() {
        return UNKNOWN_INSTANCE;
    }

    public static Anonymous getSignInAccessor(String memberId, String password) {
        return SignInAccessor.of(memberId, password);
    }

    public static Member getSignUpAccessor(
            String memberId, String password, String name
            , int age, char gender, String email, Grade type
    ) {
        return SignUpAccessor.of(
                memberId, name, password, email, age, gender, type
        );
    }
}
