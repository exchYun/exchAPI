package com.exch.request;

import java.math.BigDecimal;

/**
 * 新增合约委托单参数
 *
 * @author I508
 */
public class ContractEntrustSaveRequest  {

    /**
     * 交易对标识
     */
    private String symbol;

    /**
     * 仓位类型(BY=逐仓, TOTAL=全仓)
     */
    private String positionsType;

    /**
     * 类型(ONE_WAY=单向, TWO_WAY=双向)
     */
    private String entrustMode;

    /**
     * 交易模式(BUY=买入, SELL=卖出, OPEN_UP=开多 OPEN_DOWN=开空，CLOSE_UP=平多 CLOSE_DOWN=平空 )
     */
    private String tradeMode;

    /**
     * 撮合类型(LIMIT_DEFAULT=默认,MATCH_ONE_WAY=单向止盈止损,MATCH_TWO_WAY=双向止盈止损,POST_ONLY=只做maker, FOK=全部成交或立即取消, IOC=立即成交并取消剩余)
     */
    private String matchType;

    /**
     * 委托价
     */
    private BigDecimal price;

    /**
     * 委托量
     */
    private BigDecimal count;

    /**
     * 客户端订单id
     */
    private String clientOrderId;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPositionsType() {
        return positionsType;
    }

    public void setPositionsType(String positionsType) {
        this.positionsType = positionsType;
    }

    public String getEntrustMode() {
        return entrustMode;
    }

    public void setEntrustMode(String entrustMode) {
        this.entrustMode = entrustMode;
    }

    public String getTradeMode() {
        return tradeMode;
    }

    public void setTradeMode(String tradeMode) {
        this.tradeMode = tradeMode;
    }

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }
}
