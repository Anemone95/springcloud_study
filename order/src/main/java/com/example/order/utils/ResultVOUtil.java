package com.example.order.utils;

import com.example.order.VO.ResultVO;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class ResultVOUtil {
    public static ResultVO success(Object object) {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("Success");
        resultVO.setData(object);
        return resultVO;
    }
}
