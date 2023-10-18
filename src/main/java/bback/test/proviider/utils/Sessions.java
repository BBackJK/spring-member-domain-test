package bback.test.proviider.utils;

import bback.test.proviider.service.member.handler.Member;
import bback.test.proviider.service.member.handler.User;
import bback.test.proviider.service.member.handler.UserFactory;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@UtilityClass
public class Sessions {

    private final String LOGIN_KEY = "LOGGED_USER";

    private HttpSession getCurrentSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return servletRequestAttributes.getRequest().getSession();
    }

    public void login(Member member) {
        login(getCurrentSession(), member);
    }

    public void login(HttpServletRequest request, Member member) {
        login(request.getSession(), member);
    }

    public void login(HttpSession httpSession, Member member) {
        httpSession.setAttribute(LOGIN_KEY, member);
    }

    public void logout() {
        logout(getCurrentSession());
    }

    public void logout(HttpServletRequest request) {
        logout(request.getSession());
    }

    public void logout(HttpSession session) {
        session.removeAttribute(LOGIN_KEY);
    }

    public User getLoginUser(HttpServletRequest request) {
        return getLoginUser(request.getSession());
    }

    public User getLoginUser(HttpSession session) {
        Member member = (Member) session.getAttribute(LOGIN_KEY);
        return member == null
                ? UserFactory.getUnKnown()
                : member;
    }

    public User getLoginUser() {
        return getLoginUser(getCurrentSession());
    }

    public String getLoginMemberId() throws RuntimeException {
        return getLoginMemberId(getCurrentSession());
    }

    public String getLoginMemberId(HttpServletRequest request) throws RuntimeException {
        return getLoginMemberId(request.getSession());
    }

    public String getLoginMemberId(HttpSession session) throws RuntimeException {
        User user = getLoginUser(session);
        if (user.isAnonymous()) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        return user.getMemberId();
    }
}
