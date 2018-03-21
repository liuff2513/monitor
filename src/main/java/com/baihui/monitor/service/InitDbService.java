package com.baihui.monitor.service;

import com.baihui.core.jpa.support.CrudJpaService;
import com.baihui.monitor.entity.FieldGroup;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * ClassName: InitDbService
 * Description: //TODO
 * Created by feifei.liu on 2017/11/29 15:42
 **/
@Component
@Transactional
public class InitDbService extends CrudJpaService<FieldGroup, String>{
}
