package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {

    ValidateCode createImageCode(HttpServletRequest request);
}
