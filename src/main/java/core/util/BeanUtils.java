package core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * 功能描述：实例化Bean工具类
 *
 * @Author: wuyachong
 * @Date: 2021/1/5
 */
@Component
public class BeanUtils implements BeanFactoryAware {

    private static BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory factory) throws BeansException {
        beanFactory = factory;
    }

    public static Object getBean(Class beanClass){
        return beanFactory.getBean(beanClass);
    }

}
