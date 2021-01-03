package com.kingja.wenda.result;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * Description:TODO
 * Create Time:2020/12/25 0025 21:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Data
public class PagedData {
    // 前后页余量
    private final static int SHOW_PAGES = 3;
    // 当前页
    private int pageNum;
    // 分页集合
    private List<Integer> pageNums = new ArrayList<>();
    // 总页数
    private int pages;
    // 每页记录数
    private int pageSize;
    // 总记录数
    private long total;
    // 分页内容
    private List<?> pageList;
    // 显示 上页 按钮
    private boolean showPrevious;
    // 显示 下页 按钮
    private boolean showNext;
    // 显示 首页 按钮
    private boolean showFirstPage;
    // 显示 尾页 按钮
    private boolean showEndPage;
    // 显示 分页
    private boolean display;

    public boolean isDisplay() {
        return pages > 1;
    }

    public List<Integer> getPageNums() {
        pageNums.add(pageNum);
        for (int i = 1; i <= SHOW_PAGES; i++) {
            if (pageNum - i > 0) {
                pageNums.add(0, pageNum - i);
            }
            if (pageNum + i <= pages) {
                pageNums.add(pageNum + i);
            }
        }
        return pageNums;
    }

    public boolean isShowPrevious() {
        return pageNum - 1 > 0;
    }

    /**
     * 是否显示首页
     * 2,3,4,[5],6,7,8        5-3>1,说明首页1已经不在，故显示
     */
    public boolean isShowFirstPage() {
        return pageNum - SHOW_PAGES > 1;
    }

    public boolean isShowNext() {
        return pageNum + 1 <= pages;
    }

    /**
     * 是否显示首页
     * 93,94,95,[96],97,98,99       100-96>3,说明尾页100已经不在，故显示
     */
    public boolean isShowEndPage() {
        return pages - pageNum > SHOW_PAGES;
    }
}