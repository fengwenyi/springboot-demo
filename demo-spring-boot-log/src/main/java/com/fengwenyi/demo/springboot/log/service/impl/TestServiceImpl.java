package com.fengwenyi.demo.springboot.log.service.impl;

import com.fengwenyi.demo.springboot.log.dto.ShopAddDto;
import com.fengwenyi.demo.springboot.log.service.ITestService;
import com.fengwenyi.demo.springboot.log.vo.ShopVo;
import com.fengwenyi.javalib.generate.IdUtils;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.starter.annotation.LogRecord;
import org.springframework.stereotype.Service;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-08-19
 */
@Service
public class TestServiceImpl implements ITestService {
    @Override
    @LogRecord(
            success = "{{#dto.userId}}添加了商品【{{#dto.name}}】",
            type = "test",
            bizNo = "{{#id}}")
    public ShopVo addShop(ShopAddDto dto) {
        String id = IdUtils.generateId();
        LogRecordContext.putVariable("id", id);
        ShopVo vo = new ShopVo();
        vo.setId(id);
        return vo;
    }
}
