package com.xieguanzhi.web;

import com.xieguanzhi.domain.SysLog;
import com.xieguanzhi.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogWeb {

    @Autowired
    SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv = new ModelAndView();
        List<SysLog> all = sysLogService.findAll();
        mv.addObject("sysLogs",all);
        mv.setViewName("syslog-list");
        return mv;
    }
}
