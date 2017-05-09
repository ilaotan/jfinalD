package com.jfinalD.framework.kits;

/*
一、StrKit字符串处理

// 首字母变小写
StrKit.firstCharToLowerCase("ABC"); //aBC
// 首字母变大写
StrKit.firstCharToUpperCase("abc"); //Abc
// 字符串为 null 或者为  "" 时返回 true
StrKit.isBlank(String str);
// 字符串不为 null 而且不为  "" 时返回 true
StrKit.notBlank(String str);
// 字符串数组不为 null 而且不为  "" 时返回 true
StrKit.notBlank(String... str);
// 对象数组不为 null
StrKit.notNull(Object... paras)
二、HashKit加密算法，默认UTF-8

HashKit.md5(srcStr)
HashKit.sha1(srcStr)
HashKit.sha256(srcStr)
HashKit.sha384(srcStr)
HashKit.sha512(srcStr)
三、JsonKit字符串处理

JsonKit.setConvertDepth(int convertDepth); //设置全局深度，默认15
JsonKit.setDatePattern(String datePattern); //设置全局日期格式，默认yyyy-MM-dd
JsonKit.setTimestampPattern(String timestampPattern); //设置全局timestamp格式，默认yyyy-MM-dd HH:mm:ss
JsonKit.toJson(Object value); //对象转化成json字符串
JsonKit.toJson(Object value, int depth); //对象转化成json字符串并指定深度
四、PathKit路径获取，列举了2个

PathKit.getWebRootPath(); //获取WebRoot的路径
PathKit.getRootClassPath(); //获取classes路径
五、PropKit配置文件读取

// 默认从主配置文件，在JFinal web项目中会从WebConfig中的loadPropertyFile(String fileName);配置文件中读取
// 其他则默认从第一个use的配置文件中读取
PropKit.get(String key);
//使用配置文件，use之后会缓存该配置文件
Prop prop = PropKit.use(String fileName);
prop.get(String key); // 从prop中获取参数
// 使用配置文件，并卸载该文件的缓存
Prop propLess = PropKit.useless(String fileName);

六、HttpKit，http请求工具类

//Send GET request
HttpKit.get(String url);
        HttpKit.get(String url, Map<String, String> queryParas);
        HttpKit.get(String url, Map<String, String> queryParas, Map<String, String> headers);

 // Send POST request
        HttpKit.post(String url, String data);
        HttpKit.post(String url, String data, Map<String, String> headers);
        HttpKit.post(String url, Map<String, String> queryParas, String data);
        HttpKit.post(String url, Map<String, String> queryParas, String data, Map<String, String> headers);

 // 从request中读取post过来的数据
        HttpKit.readIncommingRequestData(HttpServletRequest request);
*/

/**
 * Char操作
 *
 * @author L.cm
 * @date 2013-7-22 下午5:05:05
 * JFinal中自带了不少好用的工具类，方便我们使用，下面我列举几个最常用的。
 */
public class CharKit {


    /**
     * 字符串全角转半角
     *
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     */
    public static String togglecase(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            sb.append(CharKit.regularize(string.charAt(i)));
        }
        return sb.toString();
    }

    /**
     * 进行字符规格化（全角转半角，大写转小写处理）
     *
     * @param input
     * @return char
     */
    private static char regularize(char input) {
        if (input == 12288) {
            input = (char) 32;
        } else if (input > 65280 && input < 65375) {
            input = (char) (input - 65248);
        } else if (input >= 'A' && input <= 'Z') {
            input += 32;
        }
        return input;
    }
}
