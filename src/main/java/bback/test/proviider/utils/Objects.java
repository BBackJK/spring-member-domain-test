package bback.test.proviider.utils;

import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;

@UtilityClass
public class Objects {


    public boolean isEmpty(Object target) {
        if (target == null) {
            return true;
        }

        if (target instanceof String) {
            return StringUtils.hasText((String) target);
        }

        if (target instanceof Collection) {
            Collection<?> collection = (Collection<?>) target;
            return CollectionUtils.isEmpty(collection)
                    || collection.stream().allMatch(java.util.Objects::isNull);
        }

        return false;
    }
}
