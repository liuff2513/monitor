package com.baihui.monitor.web;

import com.baihui.core.util.json.JSONUtil;
import com.baihui.monitor.dao.FieldDao;
import com.baihui.monitor.entity.Field;
import com.baihui.monitor.service.InitDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: InitDbController
 * Description: //TODO
 * Created by feifei.liu on 2017/11/29 15:48
 **/
@RestController
public class InitDbController {
    @Autowired
    InitDbService initDbService;
    @Autowired
    FieldDao fieldDao;

    @RequestMapping("/fieldList")
    public Page<Field> fieldList() {
        Pageable pageable = new PageRequest(1, 83);
        Page<Field> page = fieldDao.findAll(pageable);
        System.out.println(page.getContent().size());
        return page;
    }

    @RequestMapping("/fieldGroupList")
    public Object fieldGroupList() {
        Pageable pageable = new PageRequest(1, 10);
        return JSONUtil.excludeArray(initDbService.findAll(), new String[]{});
    }
}
