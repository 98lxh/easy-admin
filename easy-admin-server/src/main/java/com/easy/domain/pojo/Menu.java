package com.easy.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author liuxuehan
 * @since 2023-10-26
 */
@TableName("sys_menu")
@ApiModel(value = "Menu对象", description = "")
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("路径")
    private String path;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}
