package mycalculator_group6;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;
import java.text.DecimalFormat;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.ActionMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

public class CalculatorJFrame extends javax.swing.JFrame {

    private Double num, result, memorySum = 0.0;
    private char operator;
    private Stack<Double> stackNum = new Stack<>();
    private Stack<Character> stackOperator = new Stack<>();
    private ArrayList<String> history = new ArrayList<>();
    private String Expression = " ";
    private boolean isResultDisplayed = false;
    private boolean isHistoryVisible = false;
    private Font defaultDisplayFont;
    private Font defaultButtonFont;

    public CalculatorJFrame() {
        initComponents();
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 24));
        setVisible(true);
        txtHistory.setFont(new Font("Arial", Font.BOLD, 24));
        defaultDisplayFont = txtDisplay.getFont();
        defaultButtonFont = btn1.getFont();
        addKeyboardShortcuts();
    }

    private void addKeyboardShortcuts() { //cho phép nhập dữ liệu từ bàn phím
        InputMap im = getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getRootPane().getActionMap();

        // Số 0-9
        im.put(KeyStroke.getKeyStroke('0'), "press0");
        im.put(KeyStroke.getKeyStroke('1'), "press1");
        im.put(KeyStroke.getKeyStroke('2'), "press2");
        im.put(KeyStroke.getKeyStroke('3'), "press3");
        im.put(KeyStroke.getKeyStroke('4'), "press4");
        im.put(KeyStroke.getKeyStroke('5'), "press5");
        im.put(KeyStroke.getKeyStroke('6'), "press6");
        im.put(KeyStroke.getKeyStroke('7'), "press7");
        im.put(KeyStroke.getKeyStroke('8'), "press8");
        im.put(KeyStroke.getKeyStroke('9'), "press9");
        am.put("press0", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                num0.doClick();
            }
        });
        am.put("press1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn1.doClick();
            }
        });
        am.put("press2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn2.doClick();
            }
        });
        am.put("press3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn3.doClick();
            }
        });
        am.put("press4", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn4.doClick();
            }
        });
        am.put("press5", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn5.doClick();
            }
        });
        am.put("press6", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn6.doClick();
            }
        });
        am.put("press7", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn7.doClick();
            }
        });
        am.put("press8", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn8.doClick();
            }
        });
        am.put("press9", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn9.doClick();
            }
        });

        // Dấu thập phân
        im.put(KeyStroke.getKeyStroke('.'), "decimal");
        am.put("decimal", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_demical.doClick();
            }
        });

        // Toán tử
        im.put(KeyStroke.getKeyStroke('+'), "add");
        im.put(KeyStroke.getKeyStroke('-'), "sub");
        im.put(KeyStroke.getKeyStroke('*'), "mul");
        im.put(KeyStroke.getKeyStroke('/'), "div");
        im.put(KeyStroke.getKeyStroke('%'), "pct");
        im.put(KeyStroke.getKeyStroke('^'), "pow");
        // (nếu muốn hỗ trợ √ qua tổ hợp khác, thêm tương tự)
        am.put("add", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_plus.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });
        am.put("sub", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_subtract.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });
        am.put("mul", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_multi.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });
        am.put("div", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_divide.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });
        am.put("pct", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_percen.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });
        am.put("pow", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_ab.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });

        // Phím = và Enter
        im.put(KeyStroke.getKeyStroke('='), "equals");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "equals");
        am.put("equals", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_equal.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });

        // Backspace = xóa 1 ký tự
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backspace");
        am.put("backspace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_del.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });

        // Delete = Forward Delete
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "fdelete");
        am.put("fdelete", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_FDel.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });

        // Esc = Clear
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "clear");
        am.put("clear", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btn_C.doClick();
                txtDisplay.requestFocusInWindow();
            }
        });
    }

    private void save() {
        if (!txtDisplay.getText().isEmpty()) {
            num = Double.parseDouble(txtDisplay.getText()); // Lấy số hiện tại từ txtDisplay
            stackNum.push(num); // đẩy num vào stack
            //debug
            System.err.println("Dẩy vào stack " + num);

            if (Expression.isEmpty()) {
                Expression = txtDisplay.getText() + " " + operator + " "; // gắn giá trị trên txtDisplay vào Expression
            } else {
                Expression += txtDisplay.getText() + " " + operator + " ";
            }
            // Nếu toán tử trước là *, /, %, tính ngay lập tức
            if (!stackOperator.isEmpty() && (stackOperator.peek() == 'x' || stackOperator.peek() == '/' || stackOperator.peek() == '%' || stackOperator.peek() == '^' || stackOperator.peek() == '√')) {
                calculateImmediate(); // Tính ngay nếu có nhân/chia trước đó
            }
        }
        txtDisplay.setText(""); // Xóa màn hình để nhập số tiếp theo
        stackOperator.push(operator);
        //debug
        System.err.println("Dẩy vào operator " + operator);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        num0 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn_equal = new javax.swing.JButton();
        btn_demical = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn_plus = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn_subtract = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn_multi = new javax.swing.JButton();
        btn_divide = new javax.swing.JButton();
        btn_sqrt = new javax.swing.JButton();
        btn_ab = new javax.swing.JButton();
        btn_percen = new javax.swing.JButton();
        btn_C = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        txtDisplay = new java.awt.TextField();
        label1 = new java.awt.Label();
        btn_history = new javax.swing.JButton();
        txtHistory = new java.awt.TextArea();
        btn_FDel = new javax.swing.JButton();
        btn_CE = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_changeMode = new javax.swing.JMenu();
        jMenuItem_darkMode = new javax.swing.JMenuItem();
        jMenuItem_LightMode = new javax.swing.JMenuItem();
        JMenu_changeFontColor = new javax.swing.JMenu();
        jMenuItem_changeFont = new javax.swing.JMenuItem();
        jMenuItem_changeColor = new javax.swing.JMenuItem();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        num0.setBackground(new java.awt.Color(204, 204, 255));
        num0.setText("0");
        num0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn2.setBackground(new java.awt.Color(204, 204, 255));
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(204, 204, 255));
        btn4.setText("4");
        btn4.setAlignmentX(0.2F);
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn_equal.setText("=");
        btn_equal.setName("btn="); // NOI18N
        btn_equal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_equalActionPerformed(evt);
            }
        });

        btn_demical.setBackground(new java.awt.Color(204, 204, 255));
        btn_demical.setText(".");
        btn_demical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_demicalActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(204, 204, 255));
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(204, 204, 255));
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(204, 204, 255));
        btn5.setText("5");
        btn5.setAlignmentX(0.2F);
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn6.setBackground(new java.awt.Color(204, 204, 255));
        btn6.setText("6");
        btn6.setAlignmentX(0.2F);
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn_plus.setBackground(new java.awt.Color(204, 204, 255));
        btn_plus.setText("+");
        btn_plus.setName("btn+"); // NOI18N
        btn_plus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_plusActionPerformed(evt);
            }
        });

        btn8.setBackground(new java.awt.Color(204, 204, 255));
        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn_subtract.setBackground(new java.awt.Color(204, 204, 255));
        btn_subtract.setText("-");
        btn_subtract.setName("btn-"); // NOI18N
        btn_subtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_subtractActionPerformed(evt);
            }
        });

        btn9.setBackground(new java.awt.Color(204, 204, 255));
        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn7.setBackground(new java.awt.Color(204, 204, 255));
        btn7.setText("7");
        btn7.setName("btn7"); // NOI18N
        btn7.setOpaque(true);
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numberButtonActionPerformed(evt);
            }
        });

        btn_multi.setBackground(new java.awt.Color(204, 204, 255));
        btn_multi.setText("x");
        btn_multi.setName("btnX"); // NOI18N
        btn_multi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_multiActionPerformed(evt);
            }
        });

        btn_divide.setBackground(new java.awt.Color(204, 204, 255));
        btn_divide.setText("/");
        btn_divide.setName("btn/"); // NOI18N
        btn_divide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_divideActionPerformed(evt);
            }
        });

        btn_sqrt.setBackground(new java.awt.Color(204, 204, 255));
        btn_sqrt.setText("√");
        btn_sqrt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sqrtActionPerformed(evt);
            }
        });

        btn_ab.setBackground(new java.awt.Color(204, 204, 255));
        btn_ab.setText("^");
        btn_ab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_abActionPerformed(evt);
            }
        });

        btn_percen.setBackground(new java.awt.Color(204, 204, 255));
        btn_percen.setText("%");
        btn_percen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_percenActionPerformed(evt);
            }
        });

        btn_C.setBackground(new java.awt.Color(255, 153, 0));
        btn_C.setText("C");
        btn_C.setName("btnC"); // NOI18N
        btn_C.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CActionPerformed(evt);
            }
        });

        btn_del.setBackground(new java.awt.Color(255, 204, 0));
        btn_del.setText("D");
        btn_del.setToolTipText("");
        btn_del.setName("btnDel"); // NOI18N
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        txtDisplay.setName("txtDisplay"); // NOI18N
        txtDisplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDisplayActionPerformed(evt);
            }
        });

        label1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        label1.setText("Group_6");

        btn_history.setBackground(new java.awt.Color(153, 153, 153));
        btn_history.setText("HIS");
        btn_history.setName("btnMR"); // NOI18N
        btn_history.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_historyActionPerformed(evt);
            }
        });

        txtHistory.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtHistory.setEditable(false);
        txtHistory.setName("txtHistory"); // NOI18N
        txtHistory.setVisible(false);

        btn_FDel.setText("FDel");
        btn_FDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_FDelActionPerformed(evt);
            }
        });

        btn_CE.setText("CE");
        btn_CE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CEActionPerformed(evt);
            }
        });

        jButton1.setText("sin");
        jButton1.setToolTipText("");
        jButton1.setName("btnSin"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("cos");
        jButton2.setToolTipText("");
        jButton2.setName("btnCos"); // NOI18N
        jButton2.setOpaque(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("tan");
        jButton3.setName("btnTan"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("cot");
        jButton4.setName("btnCot"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("n!");
        jButton5.setName("btnFactorial"); // NOI18N
        jButton5.setOpaque(true);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jMenu_changeMode.setText("Change Mode");

        jMenuItem_darkMode.setText("Dark Mode");
        jMenuItem_darkMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_darkModeActionPerformed(evt);
            }
        });
        jMenu_changeMode.add(jMenuItem_darkMode);

        jMenuItem_LightMode.setText("Light Mode");
        jMenuItem_LightMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_LightModeActionPerformed(evt);
            }
        });
        jMenu_changeMode.add(jMenuItem_LightMode);

        jMenuBar1.add(jMenu_changeMode);

        JMenu_changeFontColor.setText("Change Font/Color");

        jMenuItem_changeFont.setText("Change Font");
        jMenuItem_changeFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_changeFontActionPerformed(evt);
            }
        });
        JMenu_changeFontColor.add(jMenuItem_changeFont);

        jMenuItem_changeColor.setText("Change Color");
        jMenuItem_changeColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_changeColorActionPerformed(evt);
            }
        });
        JMenu_changeFontColor.add(jMenuItem_changeColor);

        jMenuBar1.add(JMenu_changeFontColor);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                    .addComponent(btn_ab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_sqrt, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_percen, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addComponent(btn_history, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(num0, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_demical, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_equal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btn_plus, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btn_subtract, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btn_multi, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(btn_divide, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_C, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_CE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_FDel)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ab)
                    .addComponent(btn_sqrt)
                    .addComponent(btn_percen)
                    .addComponent(btn_history)
                    .addComponent(btn_FDel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(btn_CE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_C, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_multi, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_divide, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_subtract, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_plus, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(btn_equal, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(num0, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_demical, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        txtDisplay.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_plusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_plusActionPerformed
        num = Double.parseDouble(txtDisplay.getText());
        operator = '+';
        save();
    }//GEN-LAST:event_btn_plusActionPerformed

    private void btn_subtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_subtractActionPerformed
        if (txtDisplay.getText().isEmpty() || txtDisplay.getText().equals("0")) {
            txtDisplay.setText("-");
        } else {
            num = Double.parseDouble(txtDisplay.getText());
            operator = '-';
            save();
        }
    }//GEN-LAST:event_btn_subtractActionPerformed

    private void btn_multiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_multiActionPerformed
        num = Double.parseDouble(txtDisplay.getText());
        operator = 'x';
        save();
    }//GEN-LAST:event_btn_multiActionPerformed

    private void btn_divideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_divideActionPerformed
        num = Double.parseDouble(txtDisplay.getText());
        operator = '/';
        save();
    }//GEN-LAST:event_btn_divideActionPerformed

    private void btn_abActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_abActionPerformed
        num = Double.parseDouble(txtDisplay.getText());
        operator = '^';
        save();
    }//GEN-LAST:event_btn_abActionPerformed

    private void btn_sqrtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sqrtActionPerformed
        System.out.println("txtDisplay.getText()" + txtDisplay.getText());
        if (txtDisplay.getText().isEmpty()) {
            txtDisplay.setText("1");
        }
        num = Double.parseDouble(txtDisplay.getText());
        operator = '√';
        save();
    }//GEN-LAST:event_btn_sqrtActionPerformed

    private void btn_percenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_percenActionPerformed
        num = Double.parseDouble(txtDisplay.getText());
        operator = '%';
        save();
    }//GEN-LAST:event_btn_percenActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
        String text = txtDisplay.getText();
        if (!text.isEmpty()) {
            txtDisplay.setText(text.substring(0, text.length() - 1));
        }
    }//GEN-LAST:event_btn_delActionPerformed

    private void btn_demicalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_demicalActionPerformed
        if (!txtDisplay.getText().contains(".")) {
            txtDisplay.setText(txtDisplay.getText() + btn_demical.getText());
        }
    }//GEN-LAST:event_btn_demicalActionPerformed

    private void numberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numberButtonActionPerformed
        if (isResultDisplayed) {
            txtDisplay.setText(""); // Xóa nội dung nếu trước đó đã hiển thị kết quả
            isResultDisplayed = false; // Reset cờ
        }
        JButton btn = (JButton) evt.getSource();  // Lấy nút được nhấn
        txtDisplay.setText(txtDisplay.getText() + btn.getText());
    }//GEN-LAST:event_numberButtonActionPerformed

    private void btn_equalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_equalActionPerformed
        DecimalFormat df = new DecimalFormat("#.########");

        if (!txtDisplay.getText().isEmpty()) {
            num = Double.parseDouble(txtDisplay.getText()); // Đảm bảo số cuối cùng cũng vào stack
            stackNum.push(num); //đẩy số cuối vào stack

            //update expression
            Expression += txtDisplay.getText();
            //debug
            System.err.println("Dẩy vào stack " + num);
        }

        if (!stackOperator.isEmpty()
                && (stackOperator.peek() == 'x'
                || stackOperator.peek() == '/'
                || stackOperator.peek() == '^'
                || stackOperator.peek() == '%'
                || stackOperator.peek() == '√')) {
            calculateImmediate(); // Tính toán nếu cần   
        }
        while (!stackOperator.isEmpty()) {
            calculateFinal();
            // Xử lý các phép toán còn lại (cộng/trừ)
        }
        if (!stackNum.isEmpty()) {
            result = stackNum.pop(); // lấy phần tử từ stack
            txtDisplay.setText(df.format(result)); // Hiển thị kết quả

            history.add(Expression + " = " + df.format(result)); // lưu vào history
            Expression = "";
            isResultDisplayed = true;

            if (result == Double.POSITIVE_INFINITY) {
                txtDisplay.setText("Math ERROR");
                return;
            }
            //DEBUG 
            System.out.println("kqa cuoi cung" + result);
            System.out.println("hiiii");

        }
    }//GEN-LAST:event_btn_equalActionPerformed

    private void calculateImmediate() {
        if (stackOperator.isEmpty()) {
            return;
        }
        // lấy b trước vì stack là LIFO
        double b = (stackNum.isEmpty()) ? 1 : stackNum.pop();  // Lấy số trước đó từ stackNum
        double a = (stackNum.isEmpty()) ? b : stackNum.pop();  // 
        char op = stackOperator.pop();

        System.out.println("Tính toán ngay: " + a + " " + op + " " + b); //Debug
        System.out.println("show: " + a + " " + op + " " + b);
        try {
            switch (op) {
                case 'x':
                    result = a * b;
                    if (Double.isInfinite(a * b)) {
                        txtDisplay.setText("Overflow ERROR");
                        return;
                    }
                    break;
                case '/':
                    if (b == 0) {
                        throw new ArithmeticException("Math ERROR");
                    }
                    result = a / b;
                    break;
                case '%':
                    if (stackNum.isEmpty()) {
                        result = a / 100; // Nếu chỉ có một số, tính phần trăm
                    } else {
                        result = a * (b / 100); // Nếu có hai số: tính phần trăm của số trước đó
                    }
                    break; //tính phần trăm của a
                case '^':
                    try {
                        result = Math.pow(a, b);
                        if (Double.isInfinite(result)) {
                            throw new ArithmeticException("Overflow ERROR");
                        }
                    } catch (ArithmeticException e) {
                        System.err.println("Lỗi toán học: " + e.getMessage());
                    } catch (StackOverflowError e) {
                        System.err.println("LỖI NGHIÊM TRỌNG: Stack Overflow! Chương trình sẽ dừng ngay!");
                        System.exit(1); // Thoát chương trình với mã lỗi 1
                    }
                    break;
                case '√':
//                    b = stackNum.pop();
//                    a = stackNum.pop() + 1;
//                     System.out.println("b" + b);
//                      System.out.println("a" + a);
//                    if(a<0){
//                        throw new ArithmeticException("Math ERROR");
//                    }else{
//                        result = Math.sqrt(a);
//                    }break;
                    if (b < 0) {
                        throw new ArithmeticException("Math ERROR");
                    }
                    if (a == 1) {
                        result = Math.sqrt(b);
                    } else {
                        result = a * Math.sqrt(b);
                    }
                    break;
            }
            stackNum.push(result); // Đẩy kết quả vào stackNum
            txtDisplay.setText(String.valueOf(result)); // Xóa màn hình để nhập số tiếp theo
        } catch (ArithmeticException e) {
            txtDisplay.setText(e.getMessage());
        } catch (Exception e) {
            txtDisplay.setText("Systan ERROR");
        }
    }

    private void calculateFinal() {
        try {
            if (stackNum.size() < 2 || stackOperator.isEmpty()) {
                return;
            }

            // Đảo ngược thứ tự lấy số và toán tử
            double a = stackNum.remove(0);  // Lấy số đầu (54)
            double b = stackNum.remove(0);  // Lấy số tiếp theo (15)
            char op = stackOperator.remove(0);  // Lấy toán tử đầu ('-')

            switch (op) {
                case '+':
                    result = a + b;
                    break;
                case '-':
                    result = a - b;
                    break;
            }
            System.out.println("result: " + result);
            stackNum.push(result); // Đẩy kết quả vào stack để tính tiếp
        } catch (Exception e) {
            txtDisplay.setText("ERROR");
        }
    }
    private void btn_CActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CActionPerformed
        txtDisplay.setText("");
        stackNum.clear();
        stackOperator.clear();
    }//GEN-LAST:event_btn_CActionPerformed

    private void btn_historyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_historyActionPerformed
//       if (history.isEmpty()) {
//            txtDisplay.setText("NULL");
//       } else {
//            historyIndex--;
//            if (historyIndex < 0) { // Nếu đã vượt quá đầu danh sách, quay lại phần tử cuối
//                historyIndex = history.size() - 1;
//            }
//            txtDisplay.setText(history.get(historyIndex));
//        }

        if (!isHistoryVisible) {
            StringBuilder historyText = new StringBuilder();

            // Duyệt qua danh sách history và ghép các phép tính thành chuỗi
            for (String record : history) {
                historyText.append(record).append("\n");
            }
            txtHistory.setText(historyText.toString()); // Gán nội dung vào JTextArea
            txtHistory.setVisible(true); // Hiển thị JTextArea
            txtDisplay.setVisible(false);
            isHistoryVisible = true;
        } else {
            txtHistory.setVisible(false); // Ẩn JTextArea nếu đã mở
            txtDisplay.setVisible(true);
            isHistoryVisible = false;
        }
        txtHistory.revalidate();
        txtHistory.repaint();
        txtDisplay.revalidate();
        txtDisplay.repaint();
    }//GEN-LAST:event_btn_historyActionPerformed

    private void txtDisplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDisplayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDisplayActionPerformed

    private void btn_FDelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_FDelActionPerformed
        //xóa các số ở bên phải con trỏ
        String text = txtDisplay.getText();
        int pos = txtDisplay.getCaretPosition();
        if (!text.isEmpty() && pos < text.length()) {
            txtDisplay.setText(text.substring(0, pos) + text.substring(pos + 1));
            txtDisplay.setCaretPosition(pos);
        }
    }//GEN-LAST:event_btn_FDelActionPerformed

    private void btn_CEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CEActionPerformed
        //xóa toán hạng hiện tại và vẫn giữ nguyên các toán tử và toán hạng trước đó
        txtDisplay.setText("");
        isResultDisplayed = false;
    }//GEN-LAST:event_btn_CEActionPerformed

    private void jMenuItem_darkModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_darkModeActionPerformed
        // Đặt màu nền tối và màu chữ sáng
        java.awt.Color darkBg = new java.awt.Color(40, 40, 40);
        java.awt.Color darkText = new java.awt.Color(255, 255, 255);

        // Cập nhật màu cho các thành phần
        getContentPane().setBackground(darkBg);
        txtDisplay.setBackground(darkBg);
        txtDisplay.setForeground(darkText);
        txtHistory.setBackground(darkBg);
        txtHistory.setForeground(darkText);

        // Cập nhật màu cho các nút
        for (java.awt.Component comp : getContentPane().getComponents()) {
            if (comp instanceof javax.swing.JButton) {
                comp.setBackground(darkBg);
                comp.setForeground(darkText);
            }
        }
    }//GEN-LAST:event_jMenuItem_darkModeActionPerformed

    private void jMenuItem_LightModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_LightModeActionPerformed
        // Màu nền và chữ mặc định của các thành phần
        java.awt.Color defaultBg = new java.awt.Color(240, 240, 240); // Màu nền mặc định của Swing
        java.awt.Color defaultText = new java.awt.Color(0, 0, 0); // Màu chữ mặc định

        // 1. Reset màu cho JFrame và các thành phần chính
        getContentPane().setBackground(defaultBg);
        txtDisplay.setBackground(java.awt.Color.WHITE); // Màu nền trắng như code gốc
        txtDisplay.setForeground(defaultText);
        txtHistory.setBackground(defaultBg);
        txtHistory.setForeground(defaultText);

        // 2. Reset màu cho các nút theo code khởi tạo ban đầu
        Color buttonBg = new Color(204, 204, 255); // Màu nền nút số từ code gốc
        Color operatorButtonBg = new Color(255, 153, 0); // Màu nút C
        Color delButtonBg = new Color(255, 204, 0); // Màu nút D

        // Duyệt qua tất cả các thành phần và đặt lại màu
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                // Xác định loại nút để đặt màu chính xác
                if (btn == btn_C) {
                    btn.setBackground(operatorButtonBg);
                } else if (btn == btn_del) {
                    btn.setBackground(delButtonBg);
                } else if (btn.getBackground().equals(new Color(204, 204, 255))) {
                    btn.setBackground(buttonBg); // Nút số và toán tử thông thường
                } else {
                    btn.setBackground(defaultBg); // Các nút khác
                }
                btn.setForeground(defaultText);
            }
        }

        // 3. Reset các thành phần khác (nếu có)
        label1.setBackground(defaultBg);
        label1.setForeground(defaultText);

    }//GEN-LAST:event_jMenuItem_LightModeActionPerformed

    private void jMenuItem_changeFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_changeFontActionPerformed
        // Danh sách toàn bộ font hệ thống
    String[] fonts = GraphicsEnvironment
        .getLocalGraphicsEnvironment()
        .getAvailableFontFamilyNames();

    // Hiển thị dialog chọn font
    String chosen = (String) JOptionPane.showInputDialog(
        this,
        "Select font:",
        "Font Chooser",
        JOptionPane.PLAIN_MESSAGE,
        null,
        fonts,
        txtDisplay.getFont().getFamily()
    );
    if (chosen == null) return;

    // Lấy style & size
    int style = defaultDisplayFont.getStyle();
    int size  = defaultDisplayFont.getSize();
    Font newFont = new Font(chosen, style, size);

    // Tập các ký tự mẫu cần render
    String sampleChars = "0123456789.+-x/ %^=√CE";

    // Kiểm tra xem font mới có thể render hết sampleChars không
    boolean fontOk = true;
    for (char ch : sampleChars.toCharArray()) {
        if (!newFont.canDisplay(ch)) {
            fontOk = false;
            break;
        }
    }

    if (!fontOk) {
        // Thông báo và không áp dụng font mới
        JOptionPane.showMessageDialog(this,
            "Font \"" + chosen + "\" không hỗ trợ một số ký tự đặc biệt.\n" +
            "Chương trình sẽ giữ lại font mặc định.",
            "Font not supported",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // 3. Áp dụng cho txtDisplay và tất cả JButton
    txtDisplay.setFont(newFont);
    for (Component c : getContentPane().getComponents()) {
        if (c instanceof javax.swing.JButton) {
            ((javax.swing.JButton)c).setFont(newFont);
        }
    }
    }//GEN-LAST:event_jMenuItem_changeFontActionPerformed

    private void jMenuItem_changeColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_changeColorActionPerformed
        Color newBg = JColorChooser.showDialog(
                this,
                "Select background color",
                getContentPane().getBackground()
        );
        if (newBg != null) {
            // Áp dụng cho background của frame, txtDisplay, txtHistory và các nút
            getContentPane().setBackground(newBg);
            txtDisplay.setBackground(newBg);
            txtHistory.setBackground(newBg);
            for (Component c : getContentPane().getComponents()) {
                if (c instanceof javax.swing.JButton) {
                    c.setBackground(newBg);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem_changeColorActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            double value = Double.parseDouble(txtDisplay.getText());
            double result = Math.sin(Math.toRadians(value));  // đổi sang radian
            txtDisplay.setText(String.valueOf(result));
            txtHistory.setText("sin(" + value + ")");
        } catch (NumberFormatException e) {
            txtDisplay.setText("Error");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            double value = Double.parseDouble(txtDisplay.getText());
            double result = Math.cos(Math.toRadians(value));
            txtDisplay.setText(String.valueOf(result));
            txtHistory.setText("cos(" + value + ")");
        } catch (NumberFormatException e) {
            txtDisplay.setText("Error");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            double value = Double.parseDouble(txtDisplay.getText());
            double result = Math.tan(Math.toRadians(value));
            txtDisplay.setText(String.valueOf(result));
            txtHistory.setText("tan(" + value + ")");
        } catch (NumberFormatException e) {
            txtDisplay.setText("Error");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            double value = Double.parseDouble(txtDisplay.getText());
            double tanValue = Math.tan(Math.toRadians(value));
            if (tanValue == 0) {
                txtDisplay.setText("Infinity");
            } else {
                double result = 1 / tanValue;
                txtDisplay.setText(String.valueOf(result));
            }
            txtHistory.setText("cot(" + value + ")");
        } catch (NumberFormatException e) {
            txtDisplay.setText("Error");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            int number = Integer.parseInt(txtDisplay.getText());
            long result = 1;
            for (int i = 1; i <= number; i++) {
                result *= i;
            }
            txtDisplay.setText(String.valueOf(result));
        } catch (Exception e) {
            txtDisplay.setText("Error");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalculatorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculatorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculatorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculatorJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalculatorJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu JMenu_changeFontColor;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btn_C;
    private javax.swing.JButton btn_CE;
    private javax.swing.JButton btn_FDel;
    private javax.swing.JButton btn_ab;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_demical;
    private javax.swing.JButton btn_divide;
    private javax.swing.JButton btn_equal;
    private javax.swing.JButton btn_history;
    private javax.swing.JButton btn_multi;
    private javax.swing.JButton btn_percen;
    private javax.swing.JButton btn_plus;
    private javax.swing.JButton btn_sqrt;
    private javax.swing.JButton btn_subtract;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem_LightMode;
    private javax.swing.JMenuItem jMenuItem_changeColor;
    private javax.swing.JMenuItem jMenuItem_changeFont;
    private javax.swing.JMenuItem jMenuItem_darkMode;
    private javax.swing.JMenu jMenu_changeMode;
    private java.awt.Label label1;
    private javax.swing.JButton num0;
    private java.awt.TextField textField1;
    private java.awt.TextField txtDisplay;
    private java.awt.TextArea txtHistory;
    // End of variables declaration//GEN-END:variables
}
