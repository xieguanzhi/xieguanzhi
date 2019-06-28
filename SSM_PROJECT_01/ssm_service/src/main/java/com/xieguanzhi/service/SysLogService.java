package com.xieguanzhi.service;

import com.xieguanzhi.domain.SysLog;

import java.util.List;

public interface SysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll();
}
