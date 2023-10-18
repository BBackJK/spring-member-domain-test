package bback.test.proviider.service.member.handler;

import bback.test.proviider.dao.scheme.MemberTable;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

public interface Member extends User, Serializable {

    String getPassword();

    String getEmail();

    int getAge();

    char getGender();

    @NonNull
    Grade getType();

    void hashPassword(PasswordEncoder passwordEncoder);

    default boolean isCommon() {
        return Grade.COMMON.equals(this.getType());
    }

    default boolean isAdmin() {
        return Grade.ADMIN.equals(this.getType());
    }

    default boolean isAnonymous() {
        return false;
    }

    default boolean hasPassword() {
        return this.getPassword() != null && !this.getPassword().isEmpty();
    }

    default MemberTable toTable() {
        return new MemberTable(
                this.getMemberId()
                , this.getEmail()
                , this.getPassword()
                , this.getName()
                , this.getAge()
                , this.getGender()
                , this.getType()
        );
    }
}
