package bback.test.proviider.dto;

import bback.test.proviider.exceptions.ValidationException;
import bback.test.proviider.service.member.handler.Anonymous;
import bback.test.proviider.service.member.handler.Grade;
import bback.test.proviider.service.member.handler.Member;
import bback.test.proviider.service.member.handler.UserFactory;
import bback.test.proviider.utils.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

@ToString
@Setter
@Getter
public class MemberRequest {

    private String memberId;
    private String password;
    private String name;

    @ToString(callSuper = true)
    @Getter
    public static class Register extends MemberRequest {

        @Setter
        private int age;
        @Setter
        private char gender;
        @Setter
        private String email;
        private Grade type = null;

        public void adminType() throws IllegalAccessException {
            if (type != null && !Grade.ADMIN.equals(type)) {
                throw new IllegalAccessException();
            }
            type = Grade.ADMIN;
        }

        public void commonType() throws IllegalAccessException {
            if (type != null && !Grade.COMMON.equals(type)) {
                throw new IllegalAccessException();
            }
            type = Grade.COMMON;
        }

        public void validate() throws ValidationException {
            super.valid();
            if (!StringUtils.hasText(super.getName())) {
                throw new ValidationException("이름이 존재하지 않습니다.");
            }

            if (age < 1) {
                throw new ValidationException("나이가 올바르지 않습니다.");
            }

            if (!Strings.anyMatch(gender, 'M', 'F')) {
                throw new ValidationException("성별이 올바르지 않습니다.");
            }

            if (!StringUtils.hasText(email)) {
                throw new ValidationException("이메일이 존재하지 않습니다.");
            }
        }

        public Member toMember() {
            return UserFactory.getSignUpAccessor(
                    this.getMemberId()
                    , this.getPassword()
                    , this.getName()
                    , this.getAge()
                    , this.getGender()
                    , this.getEmail()
                    , this.getType()
            );
        }
    }

    @ToString(callSuper = true)
    @Setter
    @Getter
    public static class Login extends MemberRequest {

        public void validate() throws ValidationException {
            super.valid();
        }

        public Anonymous toAnonymous() {
            return UserFactory.getSignInAccessor(
                    this.getMemberId()
                    , this.getPassword()
            );
        }
    }


    protected void valid() throws ValidationException {
        if (!StringUtils.hasText(memberId)) {
            throw new ValidationException("아이디가 존재하지 않습니다.");
        }
        if (!StringUtils.hasText(password)) {
            throw new ValidationException("비밀번호가 존재하지 않습니다.");
        }
    }

}
