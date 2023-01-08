package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDTO {

    private Integer idMenu;
    private String icon;
    private String name;
    private String url;
    private List<RolDTO> roles;
}

