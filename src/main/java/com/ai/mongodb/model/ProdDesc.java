/**
 * 
 */
package com.ai.mongodb.model;

import java.io.Serializable;
import java.util.Date;

import com.ai.mongodb.annotation.CollectionField;
import com.ai.mongodb.annotation.CollectionName;

/**
 * Class Name : ProdDesc<br>
 * 
 * Description : 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 * 
 */
@CollectionName(value="PROD_DESC")
public class ProdDesc implements Serializable {

    private static final long serialVersionUID = 7496564184415482381L;

    @CollectionField(primaryKey=true)
    private Long id;

    private String content;
    @CollectionField(value="create_date")
    private Date createDate;
    @CollectionField(value="create_by")
    private Long createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

}
