package org.example.GroupSystem.bean.res;

import lombok.Data;

@Data
public class ResultDTO<T> {
    private  String errCode;
    private  String errMsg;
    private  Boolean success = Boolean.TRUE;
    private T data;
    private Long total;
    private ResultDTO(){}
    public static <T> ResultDTO<T> buildSuccess(T t)
    {
        ResultDTO<T> resultDTO = new ResultDTO<>();
        resultDTO.data = t;
        return resultDTO;
    }

}
