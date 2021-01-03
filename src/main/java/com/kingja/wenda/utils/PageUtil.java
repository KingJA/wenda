package com.kingja.wenda.utils;

import com.github.pagehelper.PageInfo;
import com.kingja.wenda.result.PagedData;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2020/12/25 0025 21:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PageUtil {
    public static PagedData getPagedData(PageInfo<?> pageInfo,List<?> list) {
        PagedData pageData = new PagedData();
        pageData.setPageNum(pageInfo.getPageNum());
        pageData.setPageSize(pageInfo.getPageSize());
        pageData.setPages(pageInfo.getPages());
        pageData.setTotal(pageInfo.getTotal());
        pageData.setPageList(list);
        return pageData;
    }

    public static PagedData getPagedData(List<?> list) {
        PageInfo<?> pageInfo = new PageInfo<>(list);
        PagedData pageData = new PagedData();
        pageData.setPageNum(pageInfo.getPageNum());
        pageData.setPageSize(pageInfo.getPageSize());
        pageData.setPages(pageInfo.getPages());
        pageData.setTotal(pageInfo.getTotal());
        pageData.setPageList(pageInfo.getList());
        return pageData;
    }
}
