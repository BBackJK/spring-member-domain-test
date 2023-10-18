package bback.test.proviider.dao.scheme;

import bback.module.ourbatis.persistance.OurbatisCrudHelper;
import bback.test.proviider.service.member.handler.Grade;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@OurbatisCrudHelper.Table(tableName = "member")
@AllArgsConstructor
public class MemberTable {

    @OurbatisCrudHelper.PK(isAutoIncrement = false)
    private String memberId;

    private String email;

    private String password;

    private String name;

    private int age;

    private char gender;

    private Grade type;
}
