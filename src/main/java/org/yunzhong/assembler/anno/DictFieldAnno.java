package org.yunzhong.assembler.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author yunzhong
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface DictFieldAnno {
    String value() default "";

    /**
     * property name
     * 
     * @return
     */
    String property();

    /**
     * dict category
     * 
     * @return
     */
    String category();
}
