package com.sucl.sbjms.security.service;

import com.sucl.sbjms.security.config.ShiroProperties;
import com.sucl.sbjms.security.exception.VerificationCodeException;
import com.sucl.sbjms.security.token.UserToken;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author sucl
 * @date 2019/4/16
 */
@Component
@NoArgsConstructor
@EnableConfigurationProperties(ShiroProperties.class)
public class VerifyCodeTokenCheckStrategy implements TokenCheckStrategy{

    private ShiroProperties properties;

    @Autowired(required = false)
    public VerifyCodeTokenCheckStrategy(ShiroProperties properties){
        this.properties = properties;
    }

    @Override
    public void doCheck(UserToken token) {
        if(properties!=null && properties.isVerify()){
            if(token instanceof VerifyCodeAble && !((VerifyCodeAble) token).verify()){
                throw new VerificationCodeException();
            }
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
