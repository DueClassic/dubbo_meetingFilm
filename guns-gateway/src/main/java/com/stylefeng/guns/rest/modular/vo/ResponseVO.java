package com.stylefeng.guns.rest.modular.vo;

import lombok.Data;

@Data
public class ResponseVO<T> {

    //返回状态
    private int status;
    //返回信息
    private String msg;
    //返回数据实体
    private T data;
    //图片前缀
    private String imgPre;
    //当前页码
    private int nowPage;
    //总页数
    private int totalPage;

    public static<T> ResponseVO success(int nowPage,int totalPage,String imgPre,T t){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);
        responseVO.setImgPre(imgPre);
        responseVO.setNowPage(nowPage);
        responseVO.setTotalPage(totalPage);
        return responseVO;
    }

    public static<T> ResponseVO success(String imgPre,T t){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);
        responseVO.setImgPre(imgPre);
        return responseVO;
    }

    public static<T> ResponseVO success(T t){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setData(t);
        return responseVO;
    }

    public static<T> ResponseVO success(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(0);
        responseVO.setMsg(msg);
        return responseVO;
    }

    public static<T> ResponseVO serviceFail(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(1);
        responseVO.setMsg(msg);
        return responseVO;
    }

    public static<T> ResponseVO appFail(String msg){
        ResponseVO responseVO=new ResponseVO();
        responseVO.setStatus(999);
        responseVO.setMsg(msg);
        return responseVO;
    }
}
