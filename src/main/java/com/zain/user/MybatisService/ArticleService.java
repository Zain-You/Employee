package com.zain.user.MybatisService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zain.user.entity.Account;
import com.zain.user.entity.Admin;
import com.zain.user.entity.Article;
import com.zain.user.mapper.AdminMapper;
import com.zain.user.mapper.ArticleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// 标注为springboot里面的一个bean
@Service
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    public List<Article> getAll() {

        List<Article> list = articleMapper.selectAll(new Article());
        return list;
    }

    public Article getById(Integer id) {
        return articleMapper.selectById(id);
    }

    public PageInfo<Article> getPage(Article article, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleMapper.selectAll(article);
        return PageInfo.of(list);
    }

    public void add(@RequestBody Article article) {
        // 当前 时间
        article.setTime(DateUtil.now());
        articleMapper.add(article);
    }

    public void update(Article article) {
        articleMapper.updateById(article);

    }

    public void delete(Integer id) {
        articleMapper.deleteById(id);
    }

    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            this.delete(id);
        }
    }


}
