package com.company.project.service.custom.impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.company.project.api.LoginByPhoneInDto;
import com.company.project.api.LoginInDto;
import com.company.project.api.LoginOutDto;
import com.company.project.core.ServiceException;
import com.company.project.model.WxUser;
import com.company.project.service.WxUserService;
import com.company.project.service.custom.LoginService;
import com.company.project.utils.AesCbcUtil;
import com.company.project.utils.HttpRequest;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;


/**
 * Created by CodeGenerator on 2020/03/22.
 */
@Service
@Transactional
public class LoginServiceImpl implements LoginService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Resource
    private WxUserService wxUserService;


    @Override
    public LoginOutDto login(LoginInDto inDto) {

        //通过获取微信的

        if (inDto.getCode() == null || inDto.getCode().length() == 0) {
            logger.error("code is null");
           throw new ServiceException("code is null");
        }

      // 小程序唯一标识 (在微信小程序管理后台获取)
        String wxspAppid = "wxb749603500ac070b";
        // 小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "b04af401020cf44faf2d2c6114c1aa23";
        // 授权（必填）
        String grant_type = "authorization_code";

        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        //////////////// ////////////////
        // 请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + inDto.getCode() + "&grant_type="
                + grant_type;
        // 发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        // 解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);

        // 获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        // 用户的唯一标识（openid）
        String openId = (String) json.get("openid");

        //查询数据库是否存在登录记录
        PageHelper.startPage(1,1);
        Condition condition= new Condition(WxUser.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("openId",openId);
        List<WxUser > wxUserList=wxUserService.findByCondition(condition);

        WxUser wxUser=null;
        if(!CollectionUtils.isEmpty(wxUserList)){
            wxUser=wxUserList.get(0);
       }

        LoginOutDto outDto=new LoginOutDto();

        if(wxUser == null){
            wxUser=new WxUser();
            //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
            try {
                String result = AesCbcUtil.decrypt(inDto.getEncryptedData(), session_key, inDto.getIv(), "UTF-8");
                if (null != result && result.length() > 0) {
                    JSONObject userInfoJSON = JSONObject.parseObject(result);
                    wxUser.setOpenId(openId);
                    wxUser.setNickName((String) userInfoJSON.get("nickName"));
                    wxUser.setAvatarUrl((String) userInfoJSON.get("avatarUrl"));
                    wxUser.setGender((Integer) userInfoJSON.get("gender"));
                    wxUser.setCountry((String) userInfoJSON.get("country"));
                    wxUser.setProvince((String) userInfoJSON.get("province"));
                    wxUser.setCity((String) userInfoJSON.get("city"));
                    wxUser.setCtime(new Date());
                    wxUser.setTs(new Date());
                    wxUser.setPassWord(openId);
                    wxUserService.save(wxUser);
                   String agent_id= randomAlphanumeric(8);
                } else {
                    logger.error("解密失败");
                    throw new ServiceException("解密失败");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServiceException("微信解密失败");
            }
        }else {
            wxUser.setTs(new Date());
            wxUserService.update(wxUser);
        }
        outDto.setToken(getToken(wxUser));
        outDto.setUserInfo(wxUser);

        return outDto;
    }

    @Override
    public LoginOutDto loginByPhone(LoginByPhoneInDto inDto) {
        LoginOutDto outDto=new LoginOutDto();
        if(StringUtils.isBlank(inDto.getPhone())){
            throw new ServiceException(" 手机号码必须输入");

        }

        WxUser wxUser=wxUserService.findBy("telNum",inDto.getPhone());
        if(wxUser !=null){
            if(StringUtils.equals(wxUser.getPassWord(),inDto.getPassWord())){
                outDto.setUserInfo(wxUser);
                outDto.setToken(getToken(wxUser));

            }else{
                throw new ServiceException("密码错误");
            }
        }else {
            throw new ServiceException("用户不存在");
        }

        return outDto;
    }

    private  static String getToken(WxUser wxUser) {

        String token="";
        token= JWT.create()
                .withKeyId(wxUser.getId().toString())
                .withIssuer("www.ikertimes.com")
                .withIssuedAt(new Date())
                .withJWTId("jwt.ikertimes.com")
                .withClaim("session_key", wxUser.getOpenId())
                .withAudience(wxUser.getId().toString())
                .sign(Algorithm.HMAC256(wxUser.getPassWord()));
        return token;
    }

    public static void main(String[] args) {
        WxUser wxUser=new WxUser();
        wxUser.setId(100);
        wxUser.setOpenId("3");
        wxUser.setPassWord("3");
        System.out.println(getToken(wxUser));
    }

}
