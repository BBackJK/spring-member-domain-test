package bback.test.proviider.dto;

import bback.test.proviider.service.member.handler.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberResponse {

    private String memberId;
    private String name;


    @ToString(callSuper = true)
    @Setter
    @Getter
    @AllArgsConstructor(staticName = "of")
    public static class InfoResponse extends MemberResponse {
        private int age;
        private char gender;
        private String email;
    }

    public static InfoResponse toInfoResponse(Member member) {
        MemberResponse.InfoResponse response = InfoResponse.of(member.getAge(), member.getGender(), member.getEmail());
        response.setMemberId(member.getMemberId());
        response.setName(member.getName());
        return response;
    }
}
