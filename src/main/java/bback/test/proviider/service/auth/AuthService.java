package bback.test.proviider.service.auth;


import bback.test.proviider.dto.MemberRequest;
import bback.test.proviider.service.member.handler.Member;

public interface AuthService {

    void register(MemberRequest.Register registerRequest);
    Member login(MemberRequest.Login loginRequest);
}
