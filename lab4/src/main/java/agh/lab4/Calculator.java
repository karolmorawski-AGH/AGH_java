package agh.lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.MessageFormat;
import org.mariuszgromada.math.mxparser.*;


public class Calculator extends JFrame {
	private JPanel mainPanel;
	private JButton submit;
	private JTextField textfield;
	private JTextArea textArea1;
	private JList<MathOperations> list1;

	//setting last entry and values
	private String lastEntry = "";
	private String lastResult = "";


	public Calculator() {
		setTitle("Calculator");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(600, 400));

		//Adding main panel
		add(mainPanel);

		//Adding menu bar
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);

		JMenu options = new JMenu("Options");
		menubar.add(options);

		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem reset = new JMenuItem("Reset");
		options.add(reset);
		options.add(exit);

		//Exit in menubar
		class ExitAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		}
		exit.addActionListener(new ExitAction());

		//Reset in menubar
		class ResetAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				textArea1.setText("");
				textfield.setText("");
			}
		}
		reset.addActionListener(new ResetAction());

		//create list model and add elements
		DefaultListModel<MathOperations> listModel = new DefaultListModel<MathOperations>();
		listModel.addElement(new MathOperations("[ + ] <Add>", " + ",0));
		listModel.addElement(new MathOperations("[ * ] <Multiply>", " * ",0));
		listModel.addElement(new MathOperations("[ / ] <Divide>", " / ",0));
		listModel.addElement(new MathOperations("[π] <Pi>", "*pi",0));
		listModel.addElement(new MathOperations("[e] <Euler number>", "*e",0));
		listModel.addElement(new MathOperations("[γ] <Euler–Mascheroni>", "*[gam]",0));
		listModel.addElement(new MathOperations("Sin(x)", "sin()", 4));
		listModel.addElement(new MathOperations("Cos(x)", "cos()",4));
		listModel.addElement(new MathOperations("Exp(x)", "exp()", 4));
		listModel.addElement(new MathOperations("Rad(x)", "rad()",4));
		listModel.addElement(new MathOperations("Sqrt(x)", "sqrt()", 5));
		listModel.addElement(new MathOperations("<Last result>", "lastresult",0));

		//create list
		list1.setModel(listModel);

		//list1 + textfield
		list1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if(e.getClickCount() == 2) {

					String selection = list1.getSelectedValue().getFunctionname();

					if(!selection.equals("lastresult")) {
						textfield.setText(selection);
						textfield.grabFocus();
						textfield.setCaretPosition(list1.getSelectedValue().getPosition());
					}
					else {
						textfield.setText(lastResult);
					}

				}
			}
		});

		textfield.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				super.keyReleased(e);

				//Previous command executed - using arrow up
				if(e.getKeyCode() == 38) {
					textfield.setText(lastEntry);
				}

				//Executing command with enter
				if(e.getKeyCode() == 10) {
					expression(textfield.getText());
				}
			}
		});


		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expression(textfield.getText());
			}

		});

		//Styling
		menubar.setBackground(new Color(255, 255, 255));
		options.setFont(new Font("sans-serif", Font.PLAIN, 13));

		exit.setFont(new Font("sans-serif", Font.PLAIN, 13));
		reset.setFont(new Font("sans-serif", Font.PLAIN, 13));

		UIManager.put("OptionPane.messageFont", new Font("sans-serif", Font.PLAIN, 12));
		UIManager.put("OptionPane.buttonFont", new Font("sans-serif", Font.PLAIN, 12));

		UIManager.put("Button.background", Color.white);

		UIManager UI=new UIManager();
		UI.put("OptionPane.background", Color.white);
		UI.put("Panel.background", Color.white);

		//Sets window centered
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}


	private void expression(String input) {

		//prevents annoying message popup
		if(input.length()==0) {
			return;
		}

		//clears textboxes
		if(input.equals("clear")) {
			textArea1.setText("");
			textfield.setText("");
			return;
		}

		//exits program
		if(input.equals("exit")) {
			System.exit(0);
		}

		//sets input as last executed command
		lastEntry = input;
		textfield.setText("");

		//parser calculates output
		MathParser parser = new MathParser();
		String[] calculationresult;
		calculationresult = parser.execute(input);

		if(calculationresult == null) {
			JOptionPane.showMessageDialog(null, "Incorrect input, check your syntax\n" , "mXparser Error", JOptionPane.ERROR_MESSAGE);
		}
		else {
			lastResult = calculationresult[0];
			textArea1.append(calculationresult[1]);
		}
	}

	//Main
	public static void main(String[] args) {
		Calculator form = new Calculator();
		form.setVisible(true);
	}
}
