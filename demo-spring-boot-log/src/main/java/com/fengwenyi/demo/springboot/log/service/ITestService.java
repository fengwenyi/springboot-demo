package com.fengwenyi.demo.springboot.log.service;

import com.fengwenyi.demo.springboot.log.dto.ShopAddDto;
import com.fengwenyi.demo.springboot.log.vo.ShopVo;

/**
 * @author <a href="https://fengwenyi.com">Erwin Feng</a>
 * @since 2024-08-19
 */
public interface ITestService {

    ShopVo addShop(ShopAddDto dto);

}
