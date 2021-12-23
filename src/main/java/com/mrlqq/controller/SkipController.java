package com.mrlqq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * 作者: DL代先生
 * 日期: 2021/4/29
 * 时间: 11:13
 * 描述: 这个是进行页面跳转的！
 * 内容:
 */
@Controller
@RequestMapping("skip")
public class SkipController {

    @RequestMapping("index")
    private String index(){
        return "index";
    }
}
