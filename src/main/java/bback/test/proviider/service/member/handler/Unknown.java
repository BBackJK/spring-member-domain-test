package bback.test.proviider.service.member.handler;

public class Unknown implements Anonymous {

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isUnknown() {
        return true;
    }

    @Override
    public String getMemberId() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
