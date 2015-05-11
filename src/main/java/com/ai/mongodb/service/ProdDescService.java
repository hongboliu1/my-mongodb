/**
 * 
 */
package com.ai.mongodb.service;

import com.ai.mongodb.model.ProdDesc;

/**
 * Class Name		: ProdDescService<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
public interface ProdDescService {

    /**
     * Description	： 生成注释<br>
     * 
     * liuhb
     * @param prodDesc
     * @since
     *
     */
    void save(ProdDesc prodDesc);
    
    /**
     * Description	： 更新<br>
     * 
     * liuhb
     * @param prodDesc
     * @return
     * @since
     *
     */
    long update(ProdDesc prodDesc);
    
    /**
     * Description	： 这里记述method的说明<br>
     * 
     * liuhb
     * @param prodDesc
     * @return
     * @since
     *
     */
    long delete(ProdDesc prodDesc);
    
    /**
     * Description	： findById <br>
     * 
     * liuhb
     * @return
     * @since
     *
     */
    ProdDesc findById(Long id);
}
