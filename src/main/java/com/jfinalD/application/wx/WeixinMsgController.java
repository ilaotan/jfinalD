package com.jfinalD.application.wx;

import com.jfinal.ext.plugin.route.ControllerBind;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.weixin.sdk.api.ApiConfig;
import com.jfinal.weixin.sdk.jfinal.MsgControllerAdapter;
import com.jfinal.weixin.sdk.msg.in.*;
import com.jfinal.weixin.sdk.msg.in.event.InFollowEvent;
import com.jfinal.weixin.sdk.msg.in.event.InLocationEvent;
import com.jfinal.weixin.sdk.msg.in.event.InMenuEvent;
import com.jfinal.weixin.sdk.msg.in.event.InQrCodeEvent;
import com.jfinal.weixin.sdk.msg.in.speech_recognition.InSpeechRecognitionResults;
import com.jfinal.weixin.sdk.msg.out.*;

/**
 * 将此 DemoController 在YourJFinalConfig 中注册路由，
 * <p/>
 * 并设置好weixin开发者中心的 URL 与 token ，使 URL 指向该
 * <p/>
 * DemoController 继承自父类 WeixinController 的 index
 * <p/>
 * 方法即可直接运行看效果，在此基础之上修改相关的方法即可进行实际项目开发
 */
@ControllerBind(controllerKey = "/wx/msg", viewPath = "")
public class WeixinMsgController extends MsgControllerAdapter {

    private static final String helpStr = "\t发送 help 可获得帮助，发送\"视频\" 可获取视频教程，发送 \"美女\" 可看美女，发送 music 可听音乐 ，发送新闻可看JFinal新版本消息。公众号功能持续完善中";
    static Log logger = Log.getLog(WeixinMsgController.class);

    /**
     * 如果要支持多公众账号，只需要在此返回各个公众号对应的  ApiConfig 对象即可
     * <p/>
     * 可以通过在请求 url 中挂参数来动态从数据库中获取 ApiConfig 属性值
     */
    public ApiConfig getApiConfig() {
        ApiConfig ac = new ApiConfig();

        // 配置微信 API 相关常量


        ac.setToken(PropKit.get("token"));
        ac.setAppId(PropKit.get("appId"));
        ac.setAppSecret(PropKit.get("appSecret"));

        /**
         *  是否对消息进行加密，对应于微信平台的消息加解密方式：
         *  1：true进行加密且必须配置 encodingAesKey
         *  2：false采用明文模式，同时也支持混合模式
         */
        ac.setEncryptMessage(PropKit.getBoolean("encryptMessage", false));
        ac.setEncodingAesKey(PropKit.get("encodingAesKey", "setting it in config file"));
        return ac;
    }

    protected void processInTextMsg(InTextMsg inTextMsg) {
        //转发给多客服PC客户端
        String msgContent = inTextMsg.getContent().trim();
        // 帮助提示
        if ("help".equalsIgnoreCase(msgContent)) {
            OutTextMsg outMsg = new OutTextMsg(inTextMsg);
            outMsg.setContent(helpStr);
            render(outMsg);
        }
        // 图文消息测试
        else if ("news".equalsIgnoreCase(msgContent)) {
            OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
            outMsg.addNews("图文消息title", "图文消息description", "图文消息片 url", "图文消息 url");
            render(outMsg);
        }
        // 音乐消息测试
        else if ("music".equalsIgnoreCase(msgContent)) {
            OutMusicMsg outMsg = new OutMusicMsg(inTextMsg);
            outMsg.setTitle("Day By Day");
            outMsg.setDescription("建议在 WIFI 环境下流畅欣赏此音乐");
            outMsg.setMusicUrl("http://www.jfinal.com/DayByDay-T-ara.mp3");
            outMsg.setHqMusicUrl("http://www.jfinal.com/DayByDay-T-ara.mp3");
            outMsg.setFuncFlag(true);
            render(outMsg);
        } else if ("美女".equalsIgnoreCase(msgContent)) {
            OutNewsMsg outMsg = new OutNewsMsg(inTextMsg);
            outMsg.addNews("秀色可餐", "JFinal Weixin 极速开发就是这么爽，有木有 ^_^", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq2GJLC60ECD7rE7n1cvKWRNFvOyib4KGdic3N5APUWf4ia3LLPxJrtyIYRx93aPNkDtib3ADvdaBXmZJg/0", "http://mp.weixin.qq.com/s?__biz=MjM5ODAwOTU3Mg==&mid=200987822&idx=1&sn=7eb2918275fb0fa7b520768854fb7b80#rd");
            render(outMsg);
        }
        // 其它文本消息直接返回原值 + 帮助提示
        else {
            OutTextMsg outMsg = new OutTextMsg(inTextMsg);
            outMsg.setContent("\t文本消息已成功接收，内容为： " + inTextMsg.getContent() + "\n\n" + helpStr);
            render(outMsg);
        }
    }

    protected void processInImageMsg(InImageMsg inImageMsg) {
        OutImageMsg outMsg = new OutImageMsg(inImageMsg);
        // 将刚发过来的图片再发回去
        outMsg.setMediaId(inImageMsg.getMediaId());
        render(outMsg);
    }

    protected void processInVoiceMsg(InVoiceMsg inVoiceMsg) {
        OutVoiceMsg outMsg = new OutVoiceMsg(inVoiceMsg);
        // 将刚发过来的语音再发回去
        outMsg.setMediaId(inVoiceMsg.getMediaId());
        render(outMsg);
    }

    protected void processInVideoMsg(InVideoMsg inVideoMsg) {
        /* 腾讯 api 有 bug，无法回复视频消息，暂时回复文本消息代码测试
        OutVideoMsg outMsg = new OutVideoMsg(inVideoMsg);
        outMsg.setTitle("OutVideoMsg 发送");
        outMsg.setDescription("刚刚发来的视频再发回去");
        // 将刚发过来的视频再发回去，经测试证明是腾讯官方的 api 有 bug，待 api bug 却除后再试
        outMsg.setMediaId(inVideoMsg.getMediaId());
        render(outMsg);
        */
        OutTextMsg outMsg = new OutTextMsg(inVideoMsg);
        outMsg.setContent("\t视频消息已成功接收，该视频的 mediaId 为: " + inVideoMsg.getMediaId());
        render(outMsg);
    }

    protected void processInLocationMsg(InLocationMsg inLocationMsg) {
        OutTextMsg outMsg = new OutTextMsg(inLocationMsg);
        outMsg.setContent("已收到地理位置消息:" +
                "\nlocation_X = " + inLocationMsg.getLocation_X() +
                "\nlocation_Y = " + inLocationMsg.getLocation_Y() +
                "\nscale = " + inLocationMsg.getScale() +
                "\nlabel = " + inLocationMsg.getLabel());
        render(outMsg);
    }

    protected void processInLinkMsg(InLinkMsg inLinkMsg) {
        OutNewsMsg outMsg = new OutNewsMsg(inLinkMsg);
        outMsg.addNews("链接消息已成功接收", "链接使用图文消息的方式发回给你，还可以使用文本方式发回。点击图文消息可跳转到链接地址页面，是不是很好玩 :)", "http://mmbiz.qpic.cn/mmbiz/zz3Q6WSrzq1ibBkhSA1BibMuMxLuHIvUfiaGsK7CC4kIzeh178IYSHbYQ5eg9tVxgEcbegAu22Qhwgl5IhZFWWXUw/0", inLinkMsg.getUrl());
        render(outMsg);
    }

    protected void processInFollowEvent(InFollowEvent inFollowEvent) {
        OutTextMsg outMsg = new OutTextMsg(inFollowEvent);
        outMsg.setContent("感谢关注 JFinal Weixin 极速开发，为您节约更多时间，去陪恋人、家人和朋友 :) \n\n\n " + helpStr);
        // 如果为取消关注事件，将无法接收到传回的信息
        render(outMsg);
    }

    protected void processInQrCodeEvent(InQrCodeEvent inQrCodeEvent) {
        OutTextMsg outMsg = new OutTextMsg(inQrCodeEvent);
        outMsg.setContent("processInQrCodeEvent() 方法测试成功");
        render(outMsg);
    }

    protected void processInLocationEvent(InLocationEvent inLocationEvent) {
        OutTextMsg outMsg = new OutTextMsg(inLocationEvent);
        outMsg.setContent("processInLocationEvent() 方法测试成功");
        render(outMsg);
    }

    protected void processInMenuEvent(InMenuEvent inMenuEvent) {
        OutTextMsg outMsg = new OutTextMsg(inMenuEvent);
        outMsg.setContent("processInMenuEvent() 方法测试成功");
        render(outMsg);
    }

    protected void processInSpeechRecognitionResults(InSpeechRecognitionResults inSpeechRecognitionResults) {
        OutTextMsg outMsg = new OutTextMsg(inSpeechRecognitionResults);
        outMsg.setContent("processInSpeechRecognitionResults() 方法测试成功");
        render(outMsg);
    }
}

