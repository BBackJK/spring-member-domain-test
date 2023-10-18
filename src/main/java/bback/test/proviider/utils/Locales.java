package bback.test.proviider.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;

@UtilityClass
public class Locales {

    public final String DEFAULT_LOCALE_KO = "ko";
    public final String LOCALE_EN = "en";

    public String getLanguage(HttpServletRequest request) {
        try {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            return localeResolver == null ? DEFAULT_LOCALE_KO : localeResolver.resolveLocale(request).getLanguage();
        } catch (Exception ex) {
            return DEFAULT_LOCALE_KO;
        }
    }
}
