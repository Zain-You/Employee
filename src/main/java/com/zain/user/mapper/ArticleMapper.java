package com.zain.user.mapper;

import com.zain.user.entity.Admin;
import com.zain.user.entity.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArticleMapper
{
    List<Article> selectAll(Article article);

    @Select("select * from Admin where id= #{id}")
    Article selectById(Integer id);


    void add(Article article);

    void updateById(Article article);

    @Delete("delete from `Admin` where id = #{id}")
    void deleteById(Integer id);

}
