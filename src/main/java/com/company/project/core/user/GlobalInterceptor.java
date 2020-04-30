package com.company.project.core.user;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.company.project.configurer.WebMvcConfigurer;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.model.WxUser;
import com.company.project.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by huangyelong on 2020/3/28.
 */
public class GlobalInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(WebMvcConfigurer.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        //判断路径需要拦截
        //....code
        //如果token有效
//        if(!TokenUtil.isExpire(token)){
//            User user = TokenUtil.getUser(token);
//            //我们将解析的用户结果先放入session中
//            request.getSession().setAttribut("currentUser",user);
//        }

        WxUser  wxUser=getUser(token);
        if(wxUser==null){
            Result result = new Result();
            result.setCode(ResultCode.UNAUTHORIZED).setMessage("get token fail!");
            responseResult(response, result);
            return false;
        }
        request.getSession().setAttribute("CurrentUser",wxUser);
        return true;
    }

    private String getPostData(HttpServletRequest request) {
        String json="";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            br.close();
            json = sb.toString();
        }catch (Exception e){
            logger.error("getPostData error:"+e.getMessage());
        }

        return json;
    }

    private WxUser getUser(String tolen){
        if(StringUtils.isBlank(tolen)){
            return null;
        }
        WxUser wxUser=new WxUser();
        try {
            String id=JWT.decode(tolen).getKeyId();
            wxUser.setId(StrUtils.strToInt(id));
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

        return wxUser;
    }
    private void responseResult(HttpServletResponse response, Result result) {
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {}
}
