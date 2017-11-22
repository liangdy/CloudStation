package com.cloud.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Project: CloudStation
 * FileName: PerService.java
 * Description:
 * Creator: ldy
 * Email: 1020118243@qq.com
 * Crete Date: 2/22/17 4:07 PM
 * Editor: ldy
 * Modify Date: 2/22/17 4:07 PM
 * Remark:
 */
@Scope
@Retention(RUNTIME)
public @interface PerService {
}
