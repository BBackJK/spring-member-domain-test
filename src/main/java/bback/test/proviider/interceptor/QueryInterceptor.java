package bback.test.proviider.interceptor;

import bback.module.ourbatis.interceptors.PreQueryDelegator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class QueryInterceptor implements PreQueryDelegator {

    @Override
    public void doIntercept(Executor executor, MappedStatement mappedStatement, Object o, RowBounds rowBounds, ResultHandler<?> resultHandler) throws Throwable {
        log.info("query interceptor do intercept...");
    }
}
