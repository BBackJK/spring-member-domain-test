package bback.test.proviider.events;

public interface EventAspectHandler<T> {

    void handle(T data);
}
