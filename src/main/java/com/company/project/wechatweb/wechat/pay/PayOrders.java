package com.company.project.wechatweb.wechat.pay;

import com.company.project.wechatweb.common.util.Wechats;
import com.company.project.wechatweb.common.util.XmlUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * 支付订单
 *
 * @author wangzhj
 */
public class PayOrders {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayOrders.class);

    private static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    private static final String APP_ID;

    private static final String MCH_ID;

    private static final String KEY;

    private static final String NOTIFY_URL;

    static {
        APP_ID = Wechats.getAppId();
        MCH_ID = Wechats.getMchId();
        KEY = Wechats.getKey();
        NOTIFY_URL = Wechats.getPayNotifyUrl();
    }

    /**
     * 生成订单
     *
     * @param outTradeNo
     * @param totalFee
     * @param openId
     */
    public static void createOrder(String outTradeNo, Integer totalFee,
                                   String openId, String createIp) {
        //验证
        Preconditions.checkNotNull(outTradeNo);
        Preconditions.checkNotNull(totalFee);
        Preconditions.checkNotNull(openId);
        //参数
        PayOrder order = new PayOrder();
        order.setAppid(APP_ID);
        order.setMch_id(MCH_ID);
        order.setDevice_info("");
        order.setNonce_str(RandomStringUtils.random(10, true, true));
        order.setBody("微信支付-订单");
        order.setDetail("");
        order.setAttach("");
        order.setOut_trade_no(outTradeNo);
        order.setFee_type("CNY");
        order.setTotal_fee(totalFee);
        order.setSpbill_create_ip(createIp);
        order.setTime_start("");
        order.setTime_expire("");
        order.setGoods_tag("");
        order.setNotify_url(NOTIFY_URL);
        order.setTrade_type("JSAPI");
        order.setProduct_id("");
        order.setLimit_pay("");
        order.setOpenid(openId);
        //签名
        order.setSign_type("MD5");
        order.setSign(getSign(order));
        String xml = XmlUtil.toXML(order);
        LOGGER.info("请求报文===>{}", xml);
        //请求
//        HttpRequest request = HttpRequest.post(URL).contentType("").send(xml);
//        if (request.ok()) {
//            String body = request.body();
//            LOGGER.info("{}", body);
//        }
    }

    private static String getSign(PayOrder order) {
        Map<String, String> data = null;
        String signValue = null;
        try {
            //bean ==> map
            Map<String, String> propMap = BeanUtils.describe(order);
            //过滤
            data = Maps.filterEntries(propMap, new Predicate<Map.Entry<String, String>>() {
                @Override
                public boolean apply(Map.Entry<String, String> input) {
                    return !Strings.isNullOrEmpty(input.getValue())
                            && !Objects.equal("class", input.getKey());
                }
            });
            //生成签名串
            data = new TreeMap<>(data);
            String part1 = Joiner.on("&").withKeyValueSeparator("=").join(data);
            String part2 = "key=" + KEY;
            String sign = Joiner.on("&").join(part1, part2);
            LOGGER.info("签名串===>{}", sign);
            //计算签名
            signValue = DigestUtils.md5Hex(sign).toUpperCase();
            LOGGER.info("签名值===>{}", signValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return signValue;
    }

    public static void main(String[] args) throws Exception {

        createOrder("111111111", 100, "321321", "123123");

    }
}
