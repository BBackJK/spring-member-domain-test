package bback.test.proviider.service.member.handler;

public interface Anonymous extends User {

    String getPassword();

    boolean isUnknown();
    @Override
    default boolean isAnonymous() {
        return true;
    }
}
