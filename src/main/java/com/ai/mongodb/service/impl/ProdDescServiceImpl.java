/**
 * 
 */
package com.ai.mongodb.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ai.mongodb.model.ProdDesc;
import com.ai.mongodb.service.ProdDescService;
import com.ai.mongodb.util.MongoClientUtils;

/**
 * Class Name		: ProdDescServiceImpl<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
@Service(value="prodDescService")
public class ProdDescServiceImpl implements ProdDescService {
    @Resource MongoClientUtils      mongoClientUtils;
    
    @Override
    public void save(ProdDesc prodDesc) {
        mongoClientUtils.save(prodDesc);
    }
    
    @Override
    public long update(ProdDesc prodDesc) {
        return mongoClientUtils.update(prodDesc); 
    }
}
