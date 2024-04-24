import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    //In this code some variables may have been declared but not used,
    //those variables and implementations are there to convey the concept of a real working ATM,
    //so in this simple demo we haven't really gone deep in those concepts; they're just there!
    public static void main(String[] args){
        //Initializing frame
        JFrame frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(new Dimension(300,360));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JPanel bigPanel = new JPanel();
        bigPanel.setLayout(new GridLayout(3,1));
        bigPanel.setBackground(Color.CYAN);

        //Initializing the Language Panel and its components
        JPanel langPanel = new JPanel();
        langPanel.setLayout(new FlowLayout());
        JButton engButton = new JButton("English");//This button doesn't do anything
        JButton perButton = new JButton("فارسی");
        JLabel cLangEN = new JLabel("Choose Language");
        JLabel cLangPR = new JLabel("زبان خود را انتخاب کنید");
        langPanel.setBackground(Color.CYAN);
        langPanel.add(engButton);langPanel.add(cLangEN);
        langPanel.add(cLangPR);langPanel.add(perButton);
        JPanel nullPanel = new JPanel();
        nullPanel.setBackground(Color.CYAN);
        bigPanel.add(nullPanel); //Adding null panel for layout
        bigPanel.add(langPanel);

        frame.add(bigPanel);
        frame.revalidate();
        frame.repaint();

        //Initializing Password Panel
        JPanel passwordPanel = new JPanel();
        passwordPanel.setBackground(Color.CYAN);
        passwordPanel.setLayout(new BoxLayout(passwordPanel,BoxLayout.Y_AXIS));
        JLabel instruction = new JLabel("رمز خود را وارد کنید");
        JPasswordField inputPassword = new JPasswordField();
        JButton submit = new JButton("ثبت");
        passwordPanel.add(instruction);passwordPanel.add(inputPassword);
        passwordPanel.add(submit);

        //Initializing Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(new GridLayout(2,2,1,1));
        JButton deposit = new JButton("برداشت وجه");
        JButton changePass = new JButton("تغییر رمز");
        JButton transfer = new JButton("کارت به کارت");
        JButton seeBalance = new JButton("اعلام موجودی");
        mainPanel.add(deposit);mainPanel.add(changePass);
        mainPanel.add(transfer);mainPanel.add(seeBalance);

        //Initializing ChangePassword Panel
        JPanel changePasswordPanel = new JPanel();
        changePasswordPanel.setBackground(Color.CYAN);
        changePasswordPanel.setLayout(new BoxLayout(changePasswordPanel,BoxLayout.Y_AXIS));
        JLabel instruction2 = new JLabel("رمز جدید را وارد کنید");
        JPasswordField newPasswordInput = new JPasswordField();
        JButton submitChangePassword = new JButton("تایید");
        changePasswordPanel.add(instruction2);
        changePasswordPanel.add(newPasswordInput);
        changePasswordPanel.add(submitChangePassword);

        //Initializing SuccessPanel
        JPanel successPanel = new JPanel();
        successPanel.setBackground(Color.CYAN);
        successPanel.setLayout(new BoxLayout(successPanel,BoxLayout.Y_AXIS));
        JLabel successMessage = new JLabel("عملیات با موفقیت انجام شد!");
        successPanel.add(successMessage);

        //Initializing Balance Panel
        JPanel balancePanel = new JPanel();
        balancePanel.setBackground(Color.CYAN);
        balancePanel.setLayout(new BoxLayout(balancePanel,BoxLayout.Y_AXIS));
        double randBalance = Math.random() * 1000000;
        String labelValueOfBalance;
        labelValueOfBalance = String.valueOf("موجودی حساب شما: "+(int)randBalance) + "000" + "ریال";
        JLabel balanceValue = new JLabel(labelValueOfBalance);
        balancePanel.add(balanceValue);

        //Initializing transferPanel
        JPanel transferPanel = new JPanel();
        transferPanel.setBackground(Color.CYAN);
        transferPanel.setLayout(new GridLayout(3,2));
        JLabel enterValueLabel = new JLabel("مبلغ مورد نظر را وارد کنید:");
        JTextField valueTextField = new JTextField();
        JLabel CCNoLabel = new JLabel("شماره کارت مقصد را وارد کنید:");
        JTextField CCNoTextField = new JTextField();
        JButton transferSubmit = new JButton("ثبت");
        transferPanel.add(valueTextField);transferPanel.add(enterValueLabel);
        transferPanel.add(CCNoTextField);transferPanel.add(CCNoLabel);
        transferPanel.add(transferSubmit);
        //Real quick adding event listener to transferSubmit button
        transferSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Possible handling here for cc validation and user input validation
                bigPanel.remove(transferPanel);
                bigPanel.add(successPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        //Initializing depositPanel
        JPanel depositPanel = new JPanel();
        depositPanel.setBackground(Color.CYAN);
        depositPanel.setLayout(new GridLayout(2,2));
        JButton[] depositOptions = new JButton[4];
        long tempMoney = 100000;
        for(int i = 5, j = 0; i <= 20; i += 5,j++){
            depositOptions[j] = new JButton(String.valueOf(tempMoney*i));
            depositPanel.add(depositOptions[j]);
        }
        //Real quick adding some event listener to the depositOptions buttons
        for(int i = 0 ; i < 4; i++){
            depositOptions[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bigPanel.remove(depositPanel);
                    bigPanel.add(successMessage);
                    frame.revalidate();
                    frame.repaint();
                }
            });
        }

        //Adding event listener to Persian button
        final LanguageFlag lngFlag;
        lngFlag = new LanguageFlag();
        perButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lngFlag.perFlag = true;
                bigPanel.remove(langPanel);
                bigPanel.setLayout(new FlowLayout());
                bigPanel.add(passwordPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        //Adding event listener to submit
        final Authentication passwordHandler;
        passwordHandler = new Authentication();
        passwordHandler.password = null; //In this demo we don't consider password validation
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] temp = inputPassword.getPassword(); //Getting password from user
                //Possible input handling here..

                boolean flagPss = passwordHandler.authenticate(String.copyValueOf(temp));
                if(flagPss){
                    bigPanel.remove(passwordPanel);
                    bigPanel.add(mainPanel);
                    frame.revalidate();
                    frame.repaint();
                } //else {
                    //Possible handling
               //}
            }
        });

        //Adding event listener to changePass button
        changePass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigPanel.remove(mainPanel);
                bigPanel.add(changePasswordPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        //Adding event listener to submitChangePassword
        submitChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] temp = newPasswordInput.getPassword();  //Getting new password from user
                //Possible input handling here..
                bigPanel.remove(changePasswordPanel);
                bigPanel.add(successPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        //Adding event listener to seeBalance button
        seeBalance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigPanel.remove(mainPanel);
                bigPanel.add(balanceValue);
                frame.revalidate();
                frame.repaint();
            }
        });

        //Adding event listener to deposit
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigPanel.remove(mainPanel);
                bigPanel.add(depositPanel);
                frame.revalidate();
                frame.repaint();
            }
        });

        //Adding event listener to transfer button
        transfer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bigPanel.remove(mainPanel);
                bigPanel.add(transferPanel);
                frame.revalidate();
                frame.repaint();
            }
        });
    }

    static class LanguageFlag{
        //This class can be used for choosing between English or Persian
        //But in this demo, we don't use it
        boolean perFlag = false;
        boolean engFlag = false;
    }
    static class Authentication{
        String password;
        public boolean authenticate(String password2){
            return true; //In this demo we don't consider password validation
//            return password.equals(password2); // possible validation
        }
    }
}
