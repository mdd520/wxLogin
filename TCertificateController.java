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
import com.boot.security.server.dao.TCertificateDao;
import com.boot.security.server.model.TCertificate;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tCertificates")
public class TCertificateController {

    @Autowired
    private TCertificateDao tCertificateDao;

    @PostMapping
    @ApiOperation(value = "保存")
    public TCertificate save(@RequestBody TCertificate tCertificate) {
        tCertificateDao.save(tCertificate);

        return tCertificate;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id获取")
    public TCertificate get(@PathVariable String id) {
        return tCertificateDao.getById(id);
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public TCertificate update(@RequestBody TCertificate tCertificate) {
        tCertificateDao.update(tCertificate);

        return tCertificate;
    }

    @GetMapping
    @ApiOperation(value = "列表")
    public PageTableResponse list(PageTableRequest request) {
        return new PageTableHandler(new CountHandler() {

            @Override
            public int count(PageTableRequest request) {
                return tCertificateDao.count(request.getParams());
            }
        }, new ListHandler() {

            @Override
            public List<TCertificate> list(PageTableRequest request) {
                return tCertificateDao.list(request.getParams(), request.getOffset(), request.getLimit());
            }
        }).handle(request);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除")
    public void delete(@PathVariable String id) {
        tCertificateDao.delete(id);
    }
}
