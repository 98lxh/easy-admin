package com.easy.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collection;
import java.util.Collections;

/**
 * mybatis-plus代码生成器
 * @author liuxuehan
 * @since 0.0.1
 * */
public class CodeGenerator {
    public static void main(String[] args) {
        generate();

    }
    private static void generate(){
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/easy_admin_test?serverTimezone=GMT%2b8","root","functor")
                .globalConfig(builder -> {
                    builder.author("liuxuehan") //设置作者
                            .enableSwagger() // 开启swagger模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\Administrator\\Desktop\\project\\easy-admin\\easy-admin-server\\src\\main\\java\\"); //指定输出目录
                })
                .packageConfig(builder -> {
                    // 设置父包名
                    // 设置父包模块名
                    builder.parent("com.easy") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\Administrator\\Desktop\\project\\easy-admin\\easy-admin-server\\src\\main\\resources\\mapper\\")); // 设置mapperXML生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sys_role") // 设置生成的表名
                            .addTablePrefix("sys_","t_"); // 生成过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认使用Velocity引擎
                .execute();
    }
}
