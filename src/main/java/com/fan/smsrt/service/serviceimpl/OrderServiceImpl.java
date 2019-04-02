package com.fan.smsrt.service.serviceimpl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fan.smsrt.config.AlipayConfig;
import com.fan.smsrt.dao.GoodDao;
import com.fan.smsrt.entity.Goods;
import com.fan.smsrt.util.IdsUtil;
import com.fan.smsrt.util.RandonNumberUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.fan.smsrt.dao.OrderDao;
import com.fan.smsrt.dao.ShopCartDao;
import com.fan.smsrt.dao.UserDao;
import com.fan.smsrt.entity.Order;
import com.fan.smsrt.entity.User;
import com.fan.smsrt.service.OrderService;
import com.fan.smsrt.util.result.Result;
import com.fan.smsrt.util.result.ResultCodeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final String[] status = {"已付款", "已收货", "已退款"};
    @Resource
    private UserDao userDao;
    @Resource
    private OrderDao orderDao;
    @Resource
    private GoodDao goodDao;
    @Resource
    private ShopCartDao shopCartDao;

    @Override
    public void ali(String total_money, HttpServletResponse response) throws AlipayApiException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String orderNumber = RandonNumberUtils.getOrderIdByUUId();
        //付款金额、订单名称，必填
        String subject = "支付宝沙箱支付";
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + orderNumber + "\","
                + "\"total_amount\":\"" + total_money + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = alipayClient.pageExecute(aliPayRequest).getBody();
        //输出
        out.println(result);
    }

    @Override
    public Result addOrder(String total_money, String goodsIds, String shoppingCounts, int userId) {
        String orderNumber = RandonNumberUtils.getOrderIdByUUId();
        System.out.println(userId+"--"+goodsIds+"--"+shoppingCounts);

        Result result = new Result(ResultCodeEnum.FAIL);
        int[] ids = IdsUtil.intIds(goodsIds);
        int[] counts = IdsUtil.intIds(shoppingCounts);
        if(ids.length == 0 || counts.length == 0) {
            result.setMessage("未知id信息");
            return result;
        }
        User user = userDao.selectUserById(userId);
        if (user.getPhone() == null || user.getCity() == null) {
            result.setMessage("请填写完整手机号或者地址信息");
            return result;
        }

        for (int i = 0; i < ids.length; i++) {
            //修改商品
            Goods goods = goodDao.selectById(ids[i]);
            goods.setAlreadySell(goods.getAlreadySell()+counts[i]);
            goods.setInventory(goods.getInventory()-counts[i]);
            goodDao.orderUpdate(goods);
            //创建订单
            Order order = new Order(userId,user.getUserName(),status[0],new Date(),user.getPhone(),user.getAcceptAddress(),
                    counts[i],goods.getName(),goods.getImagePath(),goods.getMoney(),goods.getSpecification());
            orderDao.saveOrder(order);
            //删除购物车
            shopCartDao.deleteShopCartByIdAndUserId(ids[i],userId);
        }
        result = new Result(ResultCodeEnum.SUCCESS);
        result.setMessage("支付成功");
        return result;
    }

    @Override
    public Result updateOrderStatus(int orderId, int userId) {
        Order order = orderDao.findOrderById(userId, orderId);
        Result result;
        if (order.getOrderStatus().equals(status[0])) {
            order.setOrderStatus(status[1]);
            orderDao.orderUpdate(order);
            result = new Result(ResultCodeEnum.SUCCESS);
            result.setMessage("收货成功");
            return result;
        }
        return new Result(ResultCodeEnum.FAIL);
    }

    @Override
    public Result selectOrderList(int userId, int pageNum, int pageSize, int sta) {
        String orderStatus = "";
        if(sta==1) orderStatus = status[0];
        if(sta==2) orderStatus = status[1];
        if(sta==3) orderStatus = status[2];
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders;
        if(orderStatus.equals("")){
            orders = orderDao.getAll(userId);
        } else {
            orders = orderDao.getAllByStatus(userId,orderStatus);
        }
        PageInfo pageInfo = new PageInfo<>(orders);
        return new Result(ResultCodeEnum.SUCCESS, pageInfo);
    }

    @Override
    public Result selectOrder(int id, int pageNum, int userId, int orderId) {
        Result result;
        Order order = orderDao.findOrderById(userId, orderId);
        if(order==null){
            result = new Result(ResultCodeEnum.FAIL);
            result.setMessage("该订单不存在！");
            return result;
        }
        result = new Result(ResultCodeEnum.SUCCESS,order);
        result.setMessage("查询成功");
        return result;
    }

    @Override
    public Result selectStoreOrder(int pageNum, int pageSize, int storeId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orders = orderDao.selectStoreOrder(storeId);
        PageInfo pageInfo = new PageInfo<>(orders);
        return new Result(ResultCodeEnum.SUCCESS, pageInfo);
    }
}
