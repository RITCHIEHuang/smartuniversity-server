package com.njtech.smartuniversity.util;

import java.util.Random;

/**
 * Created by ritchie on 7/2/18
 */
public class MailUtil {

    private static final int CODE_LENGTH = 6;
    public static final String SUBJECT_REGISTER = "smartUniversity邮箱注册";

    private static final String MESSAGE = "欢迎注册SmartUniversity智能校园项目，您本次收到的验证码为: ";


    public static String generateCode() {
        char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        //动态字符串
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int count = 0;
        while (true) {
            int index = random.nextInt(codeSequence.length); //定义随机数的范围并且产生一个随机的下标
            //随机的取出一个数
            char c = codeSequence[index];
            if (sb.indexOf(c + "") == -1) { //不让字符重复,并且把一个字符变成字符串
                sb.append(c);
                count++;
                if (count == CODE_LENGTH) {
                    break;
                }
            }
        }
        return sb.toString();

    }


    public static String generateMessgae(String code) {
        return MESSAGE + code;
    }
}
