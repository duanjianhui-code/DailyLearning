package dataStructure.stack;

/**
 * @author Duanjianhui
 * @date 2022-03-29 2:32 下午
 * @describe 栈测试
 */
public class StackTest {
    public static void main(String[] args) {
        System.out.println(isPalindrome("hello"));
        System.out.println(isPalindrome("aba"));

        System.out.println(calculate("1+11111"));
    }

    /**
     * 判断是否为回文数
     * @param val
     * @return
     */
    public static boolean isPalindrome(String val) {
        ArrayStack arrayStack = new ArrayStack(val.length());
        /**
         * 将val入栈
         */
        for (int i=0;i<val.length();i++) {
            arrayStack.push(val.charAt(i));
        }
        /**
         * 出栈
         */
        String value = "";
        int length = arrayStack.length();
        for (int i=0;i<length;i++) {
            if (!arrayStack.isEmpty()) {
                value = value+(char) arrayStack.pop();
            }
        }
        if (value.equals(val)) {
            return true;
        }

        return false;
    }

    /**
     * 1.需要遍历字符串，获取每一个字符
     * 2.判断当前字符是一个运算符还是一个数字
     * 3.把数字存放在数字栈中，把运算符放在运算符栈
     * 4.运算符栈：  如果是一个空栈，那么直接运算符入栈，如果运算符栈中已经了其他运算符
     * 就需要先对比运算符优先级，新进来的运算符如果小于等于原栈中运算符，那么需要把原运算符弹栈
     * ，数字栈中数字进行弹栈，进行运算，运算后的结果重新放入数字栈中，新运算符入栈。
     * 如果新运算符优先级大于原符号栈中运算符，那么新的符号直接入栈
     */
    public static int calculate(String str) {
        // 数字栈
        ArrayStack numStack = new ArrayStack(10);
        // 符号栈
        ArrayStack symbolStack = new ArrayStack(10);

        // 出栈的第一个元素
        int temp1 = 0;
        // 出栈的第二个元素
        int temp2 = 0;
        // 最终结果
        int result = 0;
        int symbolChar = 0;
        // 需要遍历字符串，获取每一个字符
        String value = "";
        for (int i=0;i<str.length();i++) {
            char one = str.charAt(i);
            /**
             * 判断是否是一个运算符
             */
            if (symbolStack.isOper(one)) {
                /**
                 * 是否为空栈，空就直接压栈
                 */
                if (symbolStack.isEmpty()) {
                    symbolStack.push(one);
                }else {
                    /**
                     * 计算优先级,如果新入栈的优先级>栈顶的优先级，则直接入栈
                     */
                    if (symbolStack.priority(one) >= symbolStack.priority(symbolStack.peek())) {
                        symbolStack.push(one);
                    }else {
                        /**
                         * 反之，取出数字栈的两位数字，同时取出符号栈的一个进行运算，将计算结果入栈，再将运算符入栈
                         */
                        temp1 = numStack.pop();
                        temp2 = numStack.pop();
                        symbolChar = symbolStack.pop();
                        result = numStack.calculate(temp1,temp2,symbolChar);

                        numStack.push(result);

                        symbolStack.push(one);
                    }
                }
            }else {
                /**
                 * 数字的处理逻辑
                 */
                value += one;
                if (i==str.length()-1) {
                    numStack.push(Integer.parseInt(value));
                    value = "";
                }else {
                    char data = str.substring(i+1,i+2).charAt(0);
                    if (symbolStack.isOper(data)) {
                        numStack.push(Integer.parseInt(value));
                        value = "";
                    }
                }

            }
        }
        while (true) {
            /**
             * 计算栈
             */
            if (symbolStack.isEmpty()) {
                break;
            }
            temp1 = numStack.pop();
            temp2 = numStack.pop();
            symbolChar = symbolStack.pop();
            result = numStack.calculate(temp1,temp2,symbolChar);

            numStack.push(result);
        }

        return result;
    }
}
