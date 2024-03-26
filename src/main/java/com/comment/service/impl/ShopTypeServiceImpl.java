package com.comment.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.comment.model.dto.Result;
import com.comment.model.entity.ShopType;
import com.comment.mapper.ShopTypeMapper;
import com.comment.service.IShopTypeService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.comment.utils.RedisConstants.SHOPTYPE_LIST_KEY;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 *   
 *    
 */
@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result queryList() {
        String key=SHOPTYPE_LIST_KEY;
        String JsonList=stringRedisTemplate.opsForValue().get(key);
        //判断是否存在,存在则直接返回
        if (StrUtil.isNotBlank(JsonList)){
            List<ShopType> list= JSONUtil.toList(JsonList, ShopType.class);
            return Result.ok(list);
        }
        List<ShopType> list=query().list();
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(list),30, TimeUnit.MINUTES);
        return Result.ok(list);
    }
}
