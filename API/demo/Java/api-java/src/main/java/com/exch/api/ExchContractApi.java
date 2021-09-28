package com.exch.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exch.constants.ConstantKey;
import com.exch.exchApiUtils.ExchApiUtil;
import com.exch.request.ContractEntrustSaveRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExchContractApi {

    private static Logger logger = LoggerFactory.getLogger(ExchContractApi.class);


    /**
     * 获取全部合约的交易行情
     */
    public void getAllTickers(){
        String method = "/contract/market/tickers";
        String param = "accessKey=" + URLEncoder.encode(ConstantKey.ACCESS_KEY);
        String returnJson = ExchApiUtil.sendGet(method, param);
        JSONObject object = JSON.parseObject(returnJson);
        System.out.println("getAllTickers:" + object.toString());
    }

    /**
     * 获取单个symbol的交易行情
     */
    public void getTicker(String symbol){
        String method = "/contract/market/ticker";
        String param = "symbol=" + symbol + "&accessKey=" + URLEncoder.encode(ConstantKey.ACCESS_KEY);
        String returnJson = ExchApiUtil.sendGet(method, param);
        JSONObject object = JSON.parseObject(returnJson);
        System.out.println("getTicker:" + object.toString());
    }

    /**
     * 获取单个交易对的历史成交
     */
    public void getTrade(String symbol){
        String method = "/contract/market/trade";
        String param = "symbol=" + symbol + "&accessKey=" + URLEncoder.encode(ConstantKey.ACCESS_KEY);
        String returnJson = ExchApiUtil.sendGet(method, param);
        JSONObject object = JSON.parseObject(returnJson);
        System.out.println("getTrade:" + object.toString());
    }

    /**
     * 获取单个交易对的k线
     */
    public void getKline(String symbol, String klineType, String klineStep){
        String method = "/contract/market/kline";
        String param = "symbol=" + symbol + "&klineType=" + klineType + "&klineStep=" + klineStep + "&accessKey=" + URLEncoder.encode(ConstantKey.ACCESS_KEY);
        String returnJson = ExchApiUtil.sendGet(method, param);
        JSONObject object = JSON.parseObject(returnJson);
        System.out.println("getKline:" + object.toString());
    }


    /**
     * 获取单个交易对的深度
     */
    public void getDepth(String symbol, String step){
        String method = "/contract/market/depth";
        String param = "symbol=" + symbol + "&step=" + step + "&accessKey=" + URLEncoder.encode(ConstantKey.ACCESS_KEY);
        String returnJson = ExchApiUtil.sendGet(method, param);
        JSONObject object = JSON.parseObject(returnJson);
        System.out.println("getDepth:" + object.toString());
    }

    /**
     * 新增合约委单
     * @param request 参数
     */
    public void saveEntrust(ContractEntrustSaveRequest request){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/saveEntrust";
        jsonObject.put("symbol", request.getSymbol());
        jsonObject.put("positionsType", request.getPositionsType());
        jsonObject.put("entrustMode", request.getEntrustMode());
        jsonObject.put("tradeMode", request.getTradeMode());
        jsonObject.put("matchType", request.getMatchType());
        jsonObject.put("price", request.getPrice());
        jsonObject.put("count", request.getCount());
//        jsonObject.put("clientOrderId", request.getClientOrderId()); 此字段暂时不适配
        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("saveEntrust:"+json);
    }

    /**
     * 撤销合约委单
     * @param entrustId 委单id
     */
    public void cancelEntrust(String entrustId){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/cancelEntrust";
        jsonObject.put("entrustId", entrustId);
        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("cancelEntrust:"+json);
    }

    /**
     * 根据委单id集合撤销委托单
     *
     * @param entrustIds 委单id集合（字符串）
     */
    public void cancelEntrusts(String entrustIds){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/batchCancelEntrustByIds";
        jsonObject.put("entrustIds", entrustIds);
        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("batchCancel:"+json);
    }

    /**
     * 条件批量撤销委托单
     *
     * @param symbol
     */
    public void cancelEntrustsParam(String symbol,String positionsType,String tradeMode,
                                    String entrustType,String matchType
            ,String sortFiled,String sortDirection){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/batchCancelEntrust";
        jsonObject.put("symbol", symbol);
        jsonObject.put("positionsType", positionsType);
        jsonObject.put("tradeMode", tradeMode);
        jsonObject.put("entrustType", entrustType);
        jsonObject.put("matchType", matchType);
//        jsonObject.put("sortFiled", sortFiled); 撤销最近的单子，这两个字段
//        jsonObject.put("sortDirection", sortDirection);
        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("cancelEntrustsParam:"+json);
    }


    /**
     * 查询用户当前委托单
     * @param ids  委单id集合
     * @param symbol 交易对标识
     * @param positionsType 仓位类型(BY=逐仓, TOTAL=全仓)
     * @param tradeMode 交易模式(OPEN_UP=开多 OPEN_DOWN=开空，CLOSE_UP=平多 CLOSE_DOWN=平空 )
     * @param entrustTypes 委托类型(LIMIT=限价, MARKET=市价,FULL_STOP=止盈止损,PLAN_FULL_STOP=计划委托)
     * @param matchTypes 撮合类型(LIMIT_DEFAULT=默认,MATCH_ONE_WAY=单向止盈止损,MATCH_TWO_WAY=双向止盈止损,POST_ONLY=只做maker, FOK=全部成交或立即取消, IOC=立即成交并取消剩余)
     * @param requestMatchType 请求撮合类型(DEFAULT=限价/市价, ADVANCED_PRICE_LIMIT=高级限价)
     * @param status 状态
     * @param startCreateTime 开始创建时间
     * @param endCreateTime 结束创建时间
     */
    public void currentEntrust(String ids, String symbol, String positionsType, String tradeMode, List<String> entrustTypes
    , List<String> matchTypes, String requestMatchType, String status, Date startCreateTime, Date endCreateTime){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/currentEntrust";
        jsonObject.put("ids", ids);
        jsonObject.put("symbol", symbol);
        jsonObject.put("positionsType", positionsType);
        jsonObject.put("tradeMode", tradeMode);
        jsonObject.put("entrustTypes", entrustTypes);
        jsonObject.put("matchTypes", matchTypes);
        jsonObject.put("requestMatchType", requestMatchType);
        jsonObject.put("status", status);
        jsonObject.put("startCreateTime", startCreateTime); // 开始创建时间
        jsonObject.put("endCreateTime", endCreateTime);  // 结束创建时间

        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("currentEntrust:"+json);

    }


    /**
     * 查询用户历史前委托单
     * @param ids  委单id集合
     * @param symbol 交易对标识
     * @param positionsType 仓位类型(BY=逐仓, TOTAL=全仓)
     * @param tradeMode 交易模式(OPEN_UP=开多 OPEN_DOWN=开空，CLOSE_UP=平多 CLOSE_DOWN=平空 )
     * @param entrustTypes 委托类型(LIMIT=限价, MARKET=市价,FULL_STOP=止盈止损,PLAN_FULL_STOP=计划委托)
     * @param matchTypes 撮合类型(LIMIT_DEFAULT=默认,MATCH_ONE_WAY=单向止盈止损,MATCH_TWO_WAY=双向止盈止损,POST_ONLY=只做maker, FOK=全部成交或立即取消, IOC=立即成交并取消剩余)
     * @param requestMatchType 请求撮合类型(DEFAULT=限价/市价, ADVANCED_PRICE_LIMIT=高级限价)
     * @param status 状态
     * @param startCreateTime 开始创建时间
     * @param endCreateTime 结束创建时间
     */
    public void historyEntrust(String ids, String symbol, String positionsType, String tradeMode, List<String> entrustTypes
            , List<String> matchTypes, String requestMatchType, String status, Date startCreateTime, Date endCreateTime){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/historyEntrust";
        jsonObject.put("ids", ids);
        jsonObject.put("symbol", symbol);
        jsonObject.put("positionsType", positionsType);
        jsonObject.put("tradeMode", tradeMode);
        jsonObject.put("entrustTypes", entrustTypes);
        jsonObject.put("matchTypes", matchTypes);
        jsonObject.put("requestMatchType", requestMatchType);
        jsonObject.put("status", status);
        jsonObject.put("startCreateTime", startCreateTime); // 开始创建时间
        jsonObject.put("endCreateTime", endCreateTime);  // 结束创建时间

        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("historyEntrust:"+json);
    }


    /**
     * 查询用户成交明细
     * @param ids  委单id集合
     * @param symbol 交易对标识
     * @param positionsType 仓位类型(BY=逐仓, TOTAL=全仓)
     * @param tradeMode 交易模式(OPEN_UP=开多 OPEN_DOWN=开空，CLOSE_UP=平多 CLOSE_DOWN=平空 )
     * @param entrustType 委托类型(LIMIT=限价, MARKET=市价,FULL_STOP=止盈止损,PLAN_FULL_STOP=计划委托)
     * @param matchType 撮合类型(LIMIT_DEFAULT=默认,MATCH_ONE_WAY=单向止盈止损,MATCH_TWO_WAY=双向止盈止损,POST_ONLY=只做maker, FOK=全部成交或立即取消, IOC=立即成交并取消剩余)
     * @param startCreateTime 开始创建时间
     * @param endCreateTime 结束创建时间
     */
    public void entrustOrder(String ids, String symbol, String positionsType, String tradeMode, String entrustType
            , String matchType, Date startCreateTime, Date endCreateTime){
        JSONObject jsonObject = new JSONObject();
        String method = "/contract/entrust/entrustOrder";
        jsonObject.put("ids", ids);
        jsonObject.put("symbol", symbol);
        jsonObject.put("positionsType", positionsType);
        jsonObject.put("tradeMode", tradeMode);
        jsonObject.put("entrustType", entrustType);
        jsonObject.put("matchType", matchType);
        jsonObject.put("startCreateTime", startCreateTime); // 开始创建时间
        jsonObject.put("endCreateTime", endCreateTime);  // 结束创建时间
        jsonObject.put("accessKey", ConstantKey.ACCESS_KEY);
        String json = ExchApiUtil.getJsonPost(jsonObject, method, ConstantKey.SECRET_KEY);
        System.out.println("entrustOrder:"+json);
    }


    /**
     * 合约账户动态数据
     */
    public void getActualAccount(String coinName, String positionsType){
        String method = "/personal/getActualAccount";
        String param = "coinName=" + coinName + "&positionsType=" + positionsType + "&accessKey=" + URLEncoder.encode(ConstantKey.ACCESS_KEY);
        String returnJson = ExchApiUtil.sendGet(method, param);
        JSONObject object = JSON.parseObject(returnJson);
        System.out.println("getActualAccount:" + object.toString());
    }


    public static void main(String[] args) {
        ExchContractApi api = new ExchContractApi();
//        api.getAllTickers();
//        api.getTicker("btc_usdt_sustainable");
//        api.getTrade("btc_usdt_sustainable");
//        api.getDepth("btc_usdt_sustainable", "STEP0");
        api.getKline("btc_usdt_sustainable", "MIN5", "STEP0");
//        ContractEntrustSaveRequest request = new ContractEntrustSaveRequest();
//        request.setSymbol("btc_usdt_sustainable");
//        request.setPositionsType("BY"); // 逐仓
//        request.setEntrustMode("TWO_WAY"); // 双向
//        request.setTradeMode("OPEN_UP"); // 不支持买入卖出
//        request.setMatchType("LIMIT_DEFAULT");  // LIMIT_DEFAULT  MARKET_DEFAULT POST_ONLY FOK IOC
//        request.setPrice(new BigDecimal("43000"));
//        request.setCount(new BigDecimal("0.23"));
//        api.saveEntrust(request);

//        api.cancelEntrust("891011203923726336622078536970145793");

//        String a = "[\"891003182082445312622078536970145793\",\"891003248616689664622078536970145793\"]";
//        api.cancelEntrusts(a);
//        api.cancelEntrustsParam("btc_usdt_sustainable", null, null, null, null, null, null);
//            api.currentEntrust(null, "btc_usdt_sustainable", null, null, null, null, null, null, null, null);
//        api.historyEntrust(null, "btc_usdt_sustainable", null, null, null, null, null, null, null, null);
//        api.entrustOrder(null, "btc_usdt_sustainable",  null, null, null, null, null, null);
//            api.getActualAccount("BTC", "BY");
    }


}
