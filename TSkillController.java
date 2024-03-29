package com.boot.security.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.security.server.page.table.PageTableRequest;
import com.boot.security.server.page.table.PageTableHandler;
import com.boot.security.server.page.table.PageTableResponse;
import com.boot.security.server.page.table.PageTableHandler.CountHandler;
import com.boot.security.server.page.table.PageTableHandler.ListHandler;
import com.boot.security.server.dao.TSkillDao;
import com.boot.security.server.model.TSkill;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tSkills")
public class TSkillController {

    @Autowired
    private TSkillDao tSkillDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public TSkill save(@RequestBody TSkill tSkill) {
        tSkillDao.save(tSkill);

        return tSkill;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public TSkill get(@PathVariable String id) {
        return tSkillDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public TSkill update(@RequestBody TSkill tSkill) {
        tSkillDao.update(tSkill);

        return tSkill;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return tSkillDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TSkill> list(PageTableRequest request) {
                return tSkillDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable String id) {
        tSkillDao.delete(id);
    }
}
