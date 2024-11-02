import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.List;

public class ScientificCalculator {
    public static void main(String[] args) {
        Calculator cal =new Calculator();
        cal.setTitle("计算器");
        cal.setSize(440,500);
        cal.setVisible(true);
        cal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
class Calculator extends JFrame implements ActionListener{
    JTextField input,output;
    JButton zero,one,two,three,four,five,six,seven,eight,nine;
    JButton add,sub,mul,div,eql;
    JButton CE,C,Del,Point,neg;
    String exp,out;
    public static final String ADD = "+";
    public static final String SUBTRACTION = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
    Calculator(){//创建组件
        input= new JTextField("");
        output = new JTextField();
        zero = new JButton("0");
        one = new JButton("1");
        two = new JButton("2");
        three = new JButton("3");
        four = new JButton("4");
        five = new JButton("5");
        six = new JButton("6");
        seven = new JButton("7");
        eight = new JButton("8");
        add = new JButton("+");
        sub = new JButton("-");
        mul = new JButton("*");
        div = new JButton("/");
        eql = new JButton("=");
        nine = new JButton("9");
        CE = new JButton("CE");
        C = new JButton("C");
        Point = new JButton(".");
        Del = new JButton("DEL");
        neg = new JButton("");
        out="";
        exp="";
        init();
    }
    void init()
    {//设置布局和监听器
        input.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        input.setBorder(BorderFactory.createEmptyBorder());
        input.setHorizontalAlignment(SwingConstants.RIGHT);
        input.setEnabled(false);
        output.setBorder(BorderFactory.createEmptyBorder());
        output.setHorizontalAlignment(SwingConstants.RIGHT);
        output.setEnabled(false);
        input.setFont(new Font(Font.SERIF, Font.BOLD, 40));
        GridBagLayout gridBagLayout = new GridBagLayout();
        getContentPane().setLayout(gridBagLayout);
        GridBagConstraints g = new GridBagConstraints();
        g.fill=GridBagConstraints.BOTH;
        g.weightx=1;
        g.weighty=1;
        g.gridx=0;
        g.gridy=0;
        g.gridwidth=GridBagConstraints.REMAINDER;
        g.gridheight=1;
        g.insets=new Insets(5,5,0,5);
        gridBagLayout.setConstraints(output,g);
        g.gridx=0;
        g.gridy=1;
        g.gridheight=2;
        g.insets=new Insets(0,5,5,5);
        gridBagLayout.setConstraints(input,g);
        g.insets=new Insets(5, 5, 5, 5);
        g.gridwidth=1;
        g.gridheight=1;
        g.gridy=3;
        g.gridx=0;
        gridBagLayout.setConstraints(CE, g);
        g.gridx=1;
        gridBagLayout.setConstraints(C, g);
        g.gridx=2;
        gridBagLayout.setConstraints(Del, g);
        g.gridx=3;
        gridBagLayout.setConstraints(div, g);

        g.gridy=4;
        g.gridx=0;
        gridBagLayout.setConstraints(seven, g);
        g.gridx=1;
        gridBagLayout.setConstraints(eight, g);
        g.gridx=2;
        gridBagLayout.setConstraints(nine, g);
        g.gridx=3;
        gridBagLayout.setConstraints(mul, g);

        g.gridy=5;
        g.gridx=0;
        gridBagLayout.setConstraints(four, g);
        g.gridx=1;
        gridBagLayout.setConstraints(five, g);
        g.gridx=2;
        gridBagLayout.setConstraints(six, g);
        g.gridx=3;
        gridBagLayout.setConstraints(sub, g);

        g.gridy=6;
        g.gridx=0;
        gridBagLayout.setConstraints(one, g);
        g.gridx=1;
        gridBagLayout.setConstraints(two, g);
        g.gridx=2;
        gridBagLayout.setConstraints(three, g);
        g.gridx=3;
        gridBagLayout.setConstraints(add, g);

        g.gridy=7;
        g.gridx=0;
        gridBagLayout.setConstraints(neg, g);
        g.gridx=1;
        gridBagLayout.setConstraints(zero, g);
        g.gridx=2;
        gridBagLayout.setConstraints(Point, g);
        g.gridx=3;
        gridBagLayout.setConstraints(eql, g);

        getContentPane().add(input);
        getContentPane().add(output);
        getContentPane().add(one);
        getContentPane().add(two);
        getContentPane().add(three);
        getContentPane().add(four);
        getContentPane().add(five);
        getContentPane().add(six);
        getContentPane().add(seven);
        getContentPane().add(eight);
        getContentPane().add(nine);
        getContentPane().add(add);
        getContentPane().add(sub);
        getContentPane().add(mul);
        getContentPane().add(div);
        getContentPane().add(Point);
        getContentPane().add(neg);
        getContentPane().add(C);
        getContentPane().add(CE);
        getContentPane().add(zero);
        getContentPane().add(eql);
        getContentPane().add(Del);
        one.addActionListener(this);
        zero.addActionListener(this);
        two.addActionListener(this);
        three.addActionListener(this);
        four.addActionListener(this);
        five.addActionListener(this);
        six.addActionListener(this);
        seven.addActionListener(this);
        eight.addActionListener(this);
        nine.addActionListener(this);
        add.addActionListener(this);
        sub.addActionListener(this);
        div.addActionListener(this);
        mul.addActionListener(this);
        Point.addActionListener(this);
        C.addActionListener(this);
        CE.addActionListener(this);
        neg.addActionListener(this);
        Del.addActionListener(this);
        eql.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) throws ArithmeticException {
        if(e.getSource()==CE){//清除上一个运算符
            int pos=LastOperator(exp);
            if(pos>=0)
                exp=exp.substring(0, pos+1);
            else
                exp="";
            input.setText(exp);
        }
        else if(e.getSource()==C){//清空
            exp="";
            input.setText(exp);
        }
        else if(e.getSource()==Del){//清除最后一个字符
            exp=exp.replaceFirst(".$","");
            if(exp.length()==0){
                exp="";
            }
            input.setText(exp);
        }
        else if((e.getSource()==add)||(e.getSource()==sub)||(e.getSource()==mul)||(e.getSource()==div)){
            if(exp.length()!=0&&!isOperator(exp.charAt(exp.length()-1))){
                exp+=e.getActionCommand();
            }
            input.setText(exp);
        }
        else if(e.getSource()==Point){
            int pos=exp.lastIndexOf('.');
            if(pos>=0)
            {
                if(isDigit(exp.charAt(exp.length()-1))&&!isDigitSring(exp.substring(pos+1)))
                    exp+=e.getActionCommand();
            }
            else {
                if(isDigit(exp.charAt(exp.length()-1)))
                    exp+=e.getActionCommand();
            }
            input.setText(exp);
        }
        else if(e.getSource()==eql){//运算支持混合运算，将中缀表达式转为后缀表达式再进行运算
            if(exp.equals("")){
                exp="0";
            }
            Stack<Character> stack = new Stack<>();
            StringBuilder suff = new StringBuilder();
            String buff="";
            for(int i=0;i<exp.length();i++){
                char temp;
                char ch =exp.charAt(i);
                switch(ch){
                    case '(':
                        stack.push(ch);
                        break;
                    case '+':
                    case '-':
                        while(stack.size()!=0){
                            temp=stack.pop();
                            if(temp=='('){
                                stack.push('(');
                                break;
                            }
                            suff.append(temp);
                        }
                        stack.push(ch);
                        break;
                    case '*':
                    case '/':
                        while (stack.size() != 0) {
                            temp = stack.pop();
                            if (temp == '+' || temp == '-' || temp == '(') {
                                stack.push(temp);
                                break;
                            }
                            suff.append(temp);
                        }
                        stack.push(ch);
                        break;
                    case ')':
                        while (!stack.isEmpty()) {
                            temp = stack.pop();
                            if (temp == '(') {
                                break;
                            }
                            suff.append(temp);
                        }
                        break;
                    default:
                        suff.append(ch);
                        break;
                }
            }
            while (!stack.isEmpty()) {
                suff.append(stack.pop());
            }
            buff=suff.toString();
            String []values = exp.split("[^.0-9]");
            String []value=new String[200];
            double sum=0;
            int k=0;
            int m=0;
            int z=10;
            int flag=0;
            for(int i=0;i<buff.length();i++){
                if(flag==1){
                    sum+=(buff.charAt(i)-'0')*1.0/z;
                    z*=10;
                }
                if((buff.charAt(i)!='+')&&(buff.charAt(i)!='-')&&(buff.charAt(i)!='*')&&buff.charAt(i)!='/'&&buff.charAt(i)!='.'&&flag==0){
                    sum=sum*10+(buff.charAt(i)-'0');
                }
                if(buff.charAt(i)=='.'){
                    flag=1;
                }

                if((buff.charAt(i)=='+')||(buff.charAt(i)=='-')||(buff.charAt(i)=='*')||buff.charAt(i)=='/'){
                    value[m]=String.valueOf(buff.charAt(i));
                    m++;
                }
                if(k==values.length){
                    continue;
                }
                if(sum==Double.parseDouble(values[k])){
                    value[m]=sum+"";
                    m++;
                    k++;
                    sum=0;
                    flag=0;
                    z=10;
                }
            }
            value=deleteArrayNull(value);
            List<String>  operatorList = Arrays.asList("+","-","*","/");
            Stack<BigDecimal> stack1 = new Stack<>();
            String value3;
            for (String s : value) {
                value3 = s;
                if (!operatorList.contains(value3)) {
                    stack1.push(new BigDecimal((value3)));
                } else {
                    BigDecimal x = stack1.pop();
                    BigDecimal y = stack1.pop();
                    stack1.push(calculate(x, y, value3));
                }
            }
            out=stack1.pop()+"";
            output.setText(exp);
            input.setText(out);
        }
        else{
            exp+=e.getActionCommand();
            input.setText(exp);
        }
    }

    public boolean isOperator(char c){
        return (c=='+')||(c=='-')||(c=='*')||(c=='/')||(c=='.');
    }

    public int LastOperator(String s){
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!='.'&&isOperator(s.charAt(i))){
                return i;
            }
        }
        return -1;
    }
    public static boolean isDigit(char ch)
    {
        return (ch >= '0'&&ch <= '9');
    }

    public  boolean isDigitSring(String s)
    {
        for(int i=0;i<s.length();i++)
        {
            if(!isDigit(s.charAt(i)))
                return false;
        }
        return true;
    }
    private String[] deleteArrayNull(String string[]) {
        String strArr[] = string;
        ArrayList<String> strList = new ArrayList<String>();
        for (int i = 0; i < strArr.length; i++) {
            strList.add(strArr[i]);
        }
        while (strList.remove(null));
        while (strList.remove(""));
        String strArrLast[] = strList.toArray(new String[strList.size()]);

        return strArrLast;
    }
    private static BigDecimal calculate(BigDecimal x, BigDecimal y, String operator) {
        BigDecimal calculateResult;
        switch (operator.trim()){
            case Calculator.ADD:
                calculateResult = y.add(x);
                break;
            case Calculator.SUBTRACTION:
                calculateResult = y.subtract(x);
                break;
            case Calculator.MULTIPLICATION:
                calculateResult = y.multiply(x);
                break;
            case Calculator.DIVISION:
                if (x.intValue() == 0){
                    throw new ArithmeticException("被除数为0,无法计算！");
                }else {
                    calculateResult = y.divide(x,2, RoundingMode.HALF_UP);
                }
                break;
            default:
                throw new ArithmeticException("无法运算的运算符！");
        }
        return calculateResult;
    }
}

