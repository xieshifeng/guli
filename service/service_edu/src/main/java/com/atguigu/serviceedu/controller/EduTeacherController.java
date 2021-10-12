package com.atguigu.serviceedu.controller;


import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.serviceedu.entity.EduTeacher;
import com.atguigu.serviceedu.entity.vo.TeacherQuery;
import com.atguigu.serviceedu.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-22
 */
@Api(description = "讲师管理")
@RestController
@CrossOrigin
@RequestMapping("/serviceedu/edu-teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //1 所有讲师列表
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R list(){
//        return eduTeacherService.list(null);
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("item",list);
    }

    //2 根据Id删除讲师
    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name="id",value = "讲师ID",required = true) @PathVariable String id){
        boolean flag = eduTeacherService.removeById(id);
        return R.ok();
    }

    //3 分页查询
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page",value = "当前页码",required = true)
                          @PathVariable Long page
                            ,@ApiParam(name = "limit",value = "每页记录数",required = true )
                          @PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        eduTeacherService.page(pageTeacher,null);
        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("records",records);
    }

    //4 条件查询带分页的方法
    @ApiOperation(value = "条件查询带分页的方法")
    @PostMapping ("pageTeacherCondition/{current}/{limit}")//从Get变成Post，因为@RequestBody的原因
    public R pageTeacherCondition(@PathVariable Long current,@PathVariable Long limit,
                                  @RequestBody(required = false) TeacherQuery teacherQuery){//数据源需要时json，将json数据变为对象
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        //创建page对象
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //wrapper，多条件组合查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_modified",end);
        }

        //调用方法实现条件查询
        eduTeacherService.page(pageTeacher,queryWrapper);
        List<EduTeacher> records = pageTeacher.getRecords();
        long total = pageTeacher.getTotal();
        return R.ok().data("total",total).data("records",records);

    }

    //5 添加讲师接口的方法
    @ApiOperation(value = "添加讲师接口的方法")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if(save){
            return R.ok();
        }else {
            return R.error();
        }
    }

    //6 根据讲师id进行查询
    @ApiOperation(value = "根据讲师id进行查询")
    @GetMapping("getTeacher/{id}")
    public R getTeacher(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        System.out.println(teacher);
        return R.ok().data("teacher",teacher);
    }

    //6 修改功能
    @ApiOperation(value = "修改功能")
    @PostMapping("updateTeacher")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

