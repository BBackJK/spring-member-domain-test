package bback.test.proviider.interceptor;

import bback.module.ourbatis.interceptors.PrepareDelegator;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.stereotype.Component;

import java.sql.Connection;

//@Component
public class PrepareInterceptor implements PrepareDelegator {

    @Override
    public Object doProceed(Invocation invocation, StatementHandler statementHandler, Connection connection) throws Throwable {
        return invocation.proceed();
    }
}
