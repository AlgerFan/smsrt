package com.fan.smsrt.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Auther: 阿杰
 * @Date: 2018/5/1 10:06
 * @Description: 沙箱配置
 */
public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016091600523365";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCqsgUiRWr/IHJHLN0JCRr2gLrH7FXCR4SzMKJ5mCbrB2g4/m/cqGuZrIp4Bo4JDqJ5dvfOw9kkRAI+nHRsyeeGbD0maF7SieIIyTnt2MKnrsuyLmOuoPee6/h1CgYbfqHQneQ2jSgRiPI16NnknKh2haDP/L3WalOgaNxoxQ5QVSTgGTiW21ZN9rpgCOq7d9hAy19mg2IUVxGbLKk4choQzEmIYTwgy7bty8twawhT3Jn/cvKC8BzHMVQO9n/LbTlI83Kmsjy4huzXNlromc4p+47+Jf9gEmNLi1otDijcZNYFV46+e9j8l4XYq8zlhGzOCL37KhAz6eZ6pqi7wVM5AgMBAAECggEBAICrtQcCgQdUdBCHFw+Kl5Kdsy6lTngm5TcWQEOcmCmu6becxMH3CzVKRQ75EfRt3bfmICLyqyqVQYPk+DZRdH919BqRrnjSi1rIJSzPqtwv/UiRg37NxBs+EYpYRgZPzLavbqpYeY3OR5avsiCMnsjJLlHaWVfZyrQul4c6k/GlIWucnhe5HeCOE5ih/2PMc6Y09h0pYlb1Kusrjt0gqsHO7qF+omWIEGpnlBn2c0jff+7eE+d4hE1rpWNeocnQiZs7mSTgdxd2hy1e+U8jyjg/3F4yr5iCb5o+e4SCdIO2Qo3hMLgaepVaHsK/cH3euFRlkUHNYhKp3BHz6decXQECgYEA3igH6jXtwGQoCXICzJdGvjp9REgDR3PwUkmmfzNlcv6J+BIkIDOrF6bPDGdVYmQD33lT9cG1qApNrCgDb5iuvdrAlPEleogmInkZEu+EHaAQAIzbcBaoUdsTsQxbXZ/sd0nEjMR+NZdosstBxK4i5ishFMTX6TBOW4JXjyH2WlECgYEAxLMKd61dxwef7Z4OyCm/aXX+h6OzVeq+mmvIq2EILQ3V03m25X5yybHMWA0fpz8LOlyCfNW53yJvgLtD6rWg9U/OKv2fbz8jaW8hlspOd3bZnPudDCflqwTrlSwGvzk45lrMBu8k1MfFhAXTpQwYRcCRLPOgJExqKkkd50P3yGkCgYEA0t8spCSMiRKI7m5KOelePTNHtIimlCx48aL3Z8ZhhcYXMGIH6Iki3lB4OE3dvocxJe8Ew0IcLGWbVM6mdEd5lr0OEFLWQHj7ub05D8BKp9RHXD5YDQ10uD47ctNH5pbm3lQnPXdGVZhiyVQmGYsxlUlbHVu7lp1Dw5j1g+e48AECgYEAprpYcg8KwUzuO9zRDMuOSxdQM6sHtRjwjTY1d0cdRgtHJyUxaZ/gLqS94Bjnrr0aiFKn7KbBEak/uG+DZyyjjawixoAKp6JuIs2nPAipsEuWs6D3grlCzM6z3dLeuTII0AFAldQZdR8MhhbmSi1RWbabl+293WbESqmFZmVPX5kCgYARdBW+Sv4ucxwGnF188HpUaKoINhGUgK6kadqlH7qsALzrUaBbA9poTLgEzgdB2bOtgqFtBIkma4ygChRM5gTvBjZKdM27UdpQEF7y+TviiY8AMKiK43fa/jPN9UmXfVgAZpRJPfbdQU3Lrvdy54l1T0R5d4mIR9GmwVnITrQz0A==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsyNZTYoS8C33zULSIvFMGTUiD/QPZRKt4aH0CttbHR47usPZFujSm8wgEZunn+SqPLQVbJYBi9ucVvjDm7HiezGtYtXgHF19QAdiUJH47Olz4v4/yKZVFFvEqlM4mKljkyiLzsqH2P7X5BNIIFF7s2iXamZU3w06chcOaB+4Sz1GBsJ7637YwTgZppaLwXx0siUkHKrAwOETLo/23xGjfoAP5bP67OukDODMt+52U/BEiFmZ2kLxeuc7rYpm0rBvMLMB15vo87CdMqHLCav6nb8iHj96p3yrj+1ZCC/sMK9KUjyfJxMEFvTQf54ZMJKO12H3JMbKwkLyZNqPnAcnLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    //http://127.0.0.1:10012/order/buildOrder
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/#/placingOrder";

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
