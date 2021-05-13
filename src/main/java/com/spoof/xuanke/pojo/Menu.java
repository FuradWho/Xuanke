package com.spoof.xuanke.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Menu {

    private int id;
    private String authName;
    private String path;
    private List<Menu> children;

}
