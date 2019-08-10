package com.shubham.www.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author shsethi
 */
@NoArgsConstructor
@AllArgsConstructor
public class BuildInfo {
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String version;
}
