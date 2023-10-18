package bback.test.proviider.interceptor;

import bback.test.proviider.config.I18nConfig;
import bback.test.proviider.utils.Locales;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Slf4j
public class CustomLocaleChangeInterceptor implements HandlerInterceptor {
    public static final String LOCALE_ATTR_QUERY_KEY = "LANG_CODE_QUERY";
    public static final String LOCALE_ATTR_KEY = "LANG_CODE";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ServletException {

        String locale = request.getParameter(I18nConfig.I18N_COOKIE_VALUE);
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        if (localeResolver == null) {
            throw new IllegalStateException(
                    "No LocaleResolver found: not in a DispatcherServlet request?");
        }

        if (!Locales.LOCALE_EN.equals(locale)) {
            locale = Locales.DEFAULT_LOCALE_KO;
        }
        
        log.info("locale :: {}", locale);

        localeResolver.setLocale(request, response, StringUtils.parseLocale(locale));
        request.setAttribute(LOCALE_ATTR_KEY, Locales.getLanguage(request));
        request.setAttribute(LOCALE_ATTR_QUERY_KEY, getLocaleQuery(request));
        return true;
    }

    private String getLocaleQuery(HttpServletRequest request) {
        String cookieLanguage = Locales.getLanguage(request);
        return Locales.LOCALE_EN.equals(cookieLanguage)
                ? String.format("%s=%s", I18nConfig.I18N_COOKIE_VALUE, cookieLanguage)
                : "";
    }

}
