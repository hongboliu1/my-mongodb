/**
 * 
 */
package com.ai.mongodb.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import com.ai.mongodb.BaseTest;
import com.ai.mongodb.model.ProdDesc;

/**
 * Class Name		: ProdDescServiceTest<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public class ProdDescServiceTest extends BaseTest {
    @Resource private ProdDescService           prodDescService; 

    /**
     * 起初总是加载不了配置文件，打印class路径发现build的目标目录是：target/test-classes。修改成target/classes
     *  pom 文件也需要修改
     *  System.out.println(Thread.currentThread().getContextClassLoader().getResource("").getPath());
     * liuhb
     * @since
     *
     */
    @Test
    public void testFindById() {
        ProdDesc findById = prodDescService.findById(10000L);
        System.out.println(findById.getContent());
        Assert.assertTrue(StringUtils.isNotBlank(findById.getContent()));
    }
}
