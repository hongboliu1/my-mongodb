/**
 * 
 */
package com.ai.mongodb.annotation;

import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class Name		: CollectionName<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
@Target({TYPE})  
@Retention(RetentionPolicy.RUNTIME)  
public @interface CollectionName {

    String value() default "";
}
