package com.zain.user.controller;

import com.zain.user.mybatisService.ArticleService;
import com.zain.user.entity.Article;
import com.zain.user.pojo.dto.ResponseMessage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @GetMapping("/getAll")
    public ResponseMessage<List<Article>> getAll()
    {
        List<Article> list = articleService.getAll();

        return ResponseMessage.success(list);
    }

    @GetMapping("/getById/{id}")
    public ResponseMessage<Article> getById(@PathVariable Integer id)
    {
        Article article = articleService.getById(id);
        return ResponseMessage.success(article);
    }

    @GetMapping("/getOne")
    public ResponseMessage<Article> getOne(@RequestParam Integer id, @RequestParam(required = false) String other)
    {
        Article article = articleService.getById(id);
        return ResponseMessage.success(article);
    }

    // 使用getList？id=1&name=str 的方式请求
    @GetMapping("/getList")
    public ResponseMessage<Article> getList(Article article)
    {
        return  ResponseMessage.success(article);

    }

    // 分页查询
    @GetMapping("/getPage")
    public ResponseMessage getPage(Article article,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "10") Integer pageSize ) {
        com.github.pagehelper.PageInfo<Article> pageInfo = articleService.getPage(article,pageNum, pageSize);
        return ResponseMessage.success(pageInfo);
    }

    @PostMapping("/add")
    public ResponseMessage add(@RequestBody Article article)
    {
        articleService.add(article);
        return ResponseMessage.success(null);
    }

    @PutMapping("/update")
    public ResponseMessage update(@RequestBody Article article)
    {
        articleService.update(article);
        return ResponseMessage.success(null);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseMessage update(@PathVariable Integer id)
    {
        articleService.delete(id);
        return ResponseMessage.success(null);
    }

    @DeleteMapping("/deleteBatch")
    public ResponseMessage deleteBatch(@RequestBody List<Integer> ids) {
        articleService.deleteBatch(ids);
        return ResponseMessage.success(null);
    }

}
