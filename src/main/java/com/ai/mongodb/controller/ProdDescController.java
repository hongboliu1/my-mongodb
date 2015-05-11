/**
 * 
 */
package com.ai.mongodb.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ai.mongodb.model.ProdDesc;
import com.ai.mongodb.service.ProdDescService;

/**
 * Class Name		: ProdDescController<br>
 * 
 * Description		: 这里记述class说明<br>
 * 
 * @author liuhb
 * @version $Revision$
 * @see
 *
 */
@Controller
public class ProdDescController {

    @Resource private ProdDescService         prodDescService;
    
    @RequestMapping(value="/publishProdDesc",method=RequestMethod.GET)
    public String publishProdDesc(HttpServletRequest request,HttpServletResponse response,Model model) {
        ProdDesc prodDesc = new ProdDesc();
        prodDesc.setId(10000L);
        prodDesc.setContent("MongoDatabase withReadPreference(ReadPreference readPreference)");
        prodDesc.setCreateDate(Calendar.getInstance().getTime());
        prodDesc.setCreateBy(200L);
        prodDescService.save(prodDesc);
        model.addAttribute("publishProdDesc","updatePredDesc");
        return "proddesc/success";
    }
    
    @RequestMapping(value="/updateProdDesc",method=RequestMethod.GET)
    public String updateProdDesc(HttpServletRequest request,HttpServletResponse response,Model model) {
        ProdDesc prodDesc = new ProdDesc();
        prodDesc.setId(10000L);
        prodDesc.setContent("my name is liuhongbo，update teset is OK");
        prodDesc.setCreateDate(Calendar.getInstance().getTime());
        prodDesc.setCreateBy(200L);
        prodDescService.update(prodDesc);
        model.addAttribute("doSomething","updatePredDesc");
        return "proddesc/success";
    }
}
