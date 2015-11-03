package com.jfinalD.framework.kits;

/**
 * Char操作
 * @author L.cm
 * @date 2013-7-22 下午5:05:05
 */
public class CharKit {
   
	
    /**
     * 字符串全角转半角
     * @param @return    设定文件
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
     * @param input
     * @return char
     */
    private static char regularize(char input){
        if (input == 12288) {
            input = (char) 32;
        }else if (input > 65280 && input < 65375) {
            input = (char) (input - 65248);
        }else if (input >= 'A' && input <= 'Z') {
            input += 32;
        }
        return input;
    }
}
