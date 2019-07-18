package com.select.wuliu.shiro;
import org.springframework.context.annotation.Configuration;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 支付宝配置
 * @title: AliPayConfig
 * @projectName demo
 * @description:
 * @date 2019/6/1014:20
 */
@Configuration
public class AliPayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ g5kike
 //    netapp映射的地址，，，根据自己的需要修改  server.natappfree.cc
    private static String neturl = "http://ph2gpm.natappfree.cc";

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号 按照我文章图上的信息填写
    public static String app_id = "2016101000656644";

    // 商户私钥，您的PKCS8格式RSA2私钥  刚刚生成的私钥直接复制填写
    public static String merchant_private_key ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFxT7cmFOTubxt6XKAg1wgMGFspYsTsmI51PPLC9bHy6vfPDcTD2+uGdgMfUkhYkm1PenvbNGFYkk2JR5y4i/if/okmaKlQ0T3G9JNV75gLJmhA+2wCnkEQN7HzETEC1e5nCXKSo9JKS5drbrnOjLK0unzocBGQwmP6jDUIoQN4QkHA20yboL8AmyPexReTfHrhWxwz1YsRNIYlVyqM6DyuUnLsvKTYHlLqKCMRZaIQt024cpgmGfeSeGSz/jfJfg4Q8bW9WNIFXuKZ5YDwyG34uPI9lyvwbGYuY3Wcis10hc4FfU2WPidK7K298TZ0XG2AQJX98pv4f88S19XTa/1AgMBAAECggEANapwEae6v+UfxyiEmzIX34IJU8EGDpzd8z2PoMHbJMR8604I+kiTEVGk7z3D/PSlhWSVWUxashxAKsyxbn13oHKfM9e+/HYEUO6EbCrrYUIDUCl3TcylyCMOPTGh1NSIGRqfvowy97LhuwtblDAw+FWtLeSScp2qVGYruK8wMzCjO+lR8cTxc+lOfDL2SZNGTIQiUO8LX9lkK4S/XRbLQsAeHZElitWprqbo5+H8/SElfNmM/Fvedw+PWP2v6vRnzPpEQeClTXLHni8mu7xe5j9dr7UEBmbddBwNwWAvKOLIf4o0yG6NSSi2cSZQCOzpEIvhUTEZuXyVBcr8j1FD8QKBgQDO0VhAc5s0KdAmSQj2GgAdWZyu8cFr7WHuF2ENDeXgdLJeZjNowAIle281u4R/hJYi2b+PqwgdZFDuD6bftnHXkNpDBUWXJXU83rwyHskKHHR3731DF4s+ayTGq2fc5HHB37F3N37yo20ZxyMd3c9Kjv3PIph4C5kCnxSzJMpInwKBgQCllO2ZxHvNFGE4tPa/ir6Ld9Rr0Ej0u7RjMoqg/gpKvWZy1qWjLjN6/b9wW5KoBf2igjiHOM6yIBn+c35iQbRflETag6CxoXnpDiPYUYgRbZGDa+1cKTlVWr7IrFN7hnFDDnHFIFpMeQsh71sgHh3xHlFtRLGqsuWlnzMk/Tc66wKBgDUCl5o9h4SBSyblBXT/CRR8yZpVwPwG5lj+iROtgkRb5PQpQfgRbvtofgLJw5Zjx4+7PEx4zCWsdsnuHRmuS9Oy/x66lTG+miQ1+wrltSXwUTIEopi1yiz/jXeWq5KEw1usGHK0OMe8d9RppGidV+qGaCvVdDsgzeFMISQhw++FAoGBAI1PLf7+mhEJBXeZqMMKq1bnKxffp4OE9Yt6xKhkZXAnd1kmKjG66iBakZhX7i8K/cxEie2M44lMsCJhekhsfnzbUm+RygAOKLZmZNzlp9QHyBl6cBUo8U8QpKUpvA09TUfG+f0bgTSmUIPje8dLLI5sK12vNFjTV/0igklc+FeFAoGAS4+NMf3svznjqKKNYXVGuBoFXcne8dH6FoAoBR3CtTixlCgqzENf9AIAo96EsDaGCUUBPBG85AvMjD2hpZUtSOw/CfZj8A/g5g5B8FUjOqsedF9qH2dH+9g1jklbqbHn7TVMn4kWc2PkZAk742gM6wWiazdkC19TVYT2lZq10Dg=";

    // 支付宝公钥,对应APPID下的支付宝公钥。 按照我文章图上的信息填写支付宝公钥，别填成商户公钥
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhcU+3JhTk7m8belygINcIDBhbKWLE7JiOdTzywvWx8ur3zw3Ew9vrhnYDH1JIWJJtT3p72zRhWJJNiUecuIv4n/6JJmipUNE9xvSTVe+YCyZoQPtsAp5BEDex8xExAtXuZwlykqPSSkuXa265zoyytLp86HARkMJj+ow1CKEDeEJBwNtMm6C/AJsj3sUXk3x64VscM9WLETSGJVcqjOg8rlJy7Lyk2B5S6igjEWWiELdNuHKYJhn3knhks/43yX4OEPG1vVjSBV7imeWA8Mht+LjyPZcr8GxmLmN1nIrNdIXOBX1Nlj4nSuytvfE2dFxtgECV/fKb+H/PEtfV02v9QIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String notify_url = neturl+"/alipay/notify_url";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，其实就是你的一个支付完成后返回的页面URL
    public static String return_url = neturl+"/alipay/return_url";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

