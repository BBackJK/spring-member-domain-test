package bback.test.proviider.controller.api;

import bback.test.proviider.dto.MemberRequest;
import bback.test.proviider.dto.MemberResponse;
import bback.test.proviider.exceptions.ValidationException;
import bback.test.proviider.service.auth.AuthService;
import bback.test.proviider.service.member.MemberService;
import bback.test.proviider.service.member.handler.Member;
import bback.test.proviider.utils.Sessions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final AuthService authService;
    private final MemberService memberService;


    @PostMapping("/api/v1/common/member")
    String register(HttpServletRequest request, @RequestBody MemberRequest.Register register) {

        try {
            register.validate();
        } catch (ValidationException ex) {
            log.info("register :: {}", register);
            return ex.getMessage();
        }

        try {
            register.commonType();
            authService.register(register);
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "fail";
        }
    }

    @PostMapping("/api/v1/member/login")
    String login(
            HttpServletRequest request
            , @RequestBody MemberRequest.Login login
    ) {

        try {
            login.validate();
        } catch (ValidationException ex) {
            return ex.getMessage();
        }

        try {
            Member verifiedMember = authService.login(login);
            Sessions.login(request, verifiedMember);
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "fail";
        }
    }

    @GetMapping("/api/v1/member/logout")
    String logout(HttpServletRequest request) {
        try {
            Sessions.logout(request);
            return "success";
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return "fail";
        }
    }


    @GetMapping("/api/v1/member")
    MemberResponse.InfoResponse sessionCheck() {
        String loginMemberId = Sessions.getLoginMemberId();
        Member detail = memberService.detail(loginMemberId);
        return MemberResponse.toInfoResponse(detail);
    }

}
