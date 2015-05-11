/**
 * 
 */
package com.ai.mongodb.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class Name		: CollectionField<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
@Target({FIELD,METHOD})  
@Retention(RetentionPolicy.RUNTIME)
public @interface CollectionField {

    String value() default "";
    
    boolean primaryKey() default false; 
}
