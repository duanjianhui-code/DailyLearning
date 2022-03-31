package dataStructure.stack;

/**
 * @author Duanjianhui
 * @date 2022-03-29 2:27 下午
 * @describe 数组实现栈
 */
public class ArrayStack {
    /**
     * 设置栈的大小
     */
    private int maxStack;

    /**
     * 数组存储数据
     */
    private int[] stack;

    /**
     * 记录栈顶的位置
     */
    private int top = -1;

    /**
     * 初始化栈
     */
    public ArrayStack(int maxStack){
        this.maxStack = maxStack;
        this.stack = new int[maxStack];
    }

    /**
     * 判断栈是否满栈
     * @return
     */
    public boolean isFull() {
        return this.top == maxStack-1;
    }

    /**
     * 判断栈是否空
     * @return
     */
    public boolean isEmpty() {
        return this.top == -1;
    }

    /**
     * 压栈
     * @param val
     */
    public void push(int val) {
        if (isFull()) {
            throw new RuntimeException("栈已满");
        }
        top++;
        stack[top] = val;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈已空");
        }
        int val = stack[top];
        top--;
        return val;
    }

    /**
     * 栈的元素个数
     * @return
     */
    public int length() {
        return this.top+1;
    }

    /**
     * 判断是否是一个运算符
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val=='+' || val=='-' || val=='*' || val=='/';
    }

    /**
     * 判断运算符的优先级，优先级越大，返回的数值越大
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper=='/') {
            return 1;
        }else if (oper == '+' || oper == '-') {
            return 0;
        }else {
            return -1;
        }
    }

    /**
     * 获取栈顶元素
     * @return
     */
    public int peek() {
        return this.stack[top];
    }

    /**
     * 计算栈的容量
     * @return
     */
    public int stackLength() {
        return this.stack.length;
    }

    /**
     * 计算两个数的值
     * @param num1
     * @param num2
     * @param oper
     * @return
     */
    public int calculate(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num2-num1;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                result = num2/num1;
                break;
            default:
                break;
        }
        return result;
    }


}
