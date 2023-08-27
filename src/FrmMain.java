import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Button;
import components.ComboBox;
import components.TextField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FrmMain extends JFrame {

	private JPanel contentPane;
	private boolean timerStarted = false;
	private Timer timer;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public FrmMain(List<Coin> coins) {

		// *************** SORT BY CURRENCY ABBREVIATION ***************
		Collections.sort(coins, (a, b) -> {
			return a.getAbbreviation().compareTo(b.getAbbreviation());
		});

		// *************** PANEL ***************
		setIconImage(Toolkit.getDefaultToolkit().getImage("icons/icon.png"));
		setBackground(new Color(255, 255, 255));
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 600, 562);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// *************** MESSAGES ***************

		JLabel lblTitleApp = new JLabel("Currency Converter");
		lblTitleApp.setBounds(0, 5, 584, 47);
		lblTitleApp.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 35));
		lblTitleApp.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblAmount = new JLabel("Enter Amount");
		lblAmount.setBounds(41, 97, 125, 27);
		lblAmount.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));

		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(41, 215, 62, 27);
		lblFrom.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));

		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(358, 215, 62, 27);
		lblTo.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));

		JLabel lblCurrencyConverter = new JLabel("");
		lblCurrencyConverter.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrencyConverter.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
		lblCurrencyConverter.setBounds(41, 358, 497, 27);

		JLabel lblError = new JLabel("");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setFont(new Font("Leelawadee UI", Font.PLAIN, 12));
		lblError.setBounds(41, 476, 497, 21);

		TextField tfAmount = new TextField();
		tfAmount.setBounds(41, 135, 497, 62);
		tfAmount.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 20));
		tfAmount.setColumns(10);
		tfAmount.setShadowColor(Color.gray);

		// *************** COMBOBOX ***************

		ComboBox cbxFrom = new ComboBox();
		cbxFrom.setBounds(93, 258, 120, 55);
		cbxFrom.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));

		ComboBox cbxTo = new ComboBox();
		cbxTo.setBounds(410, 258, 120, 55);
		cbxTo.setFont(new Font("Leelawadee UI Semilight", Font.PLAIN, 18));

		for (Coin coin : coins) {
			cbxFrom.addItem(coin);
			cbxTo.addItem(coin);
		}
		selectedCbxByAbrv(coins, "PEN", cbxFrom);
		selectedCbxByAbrv(coins, "USD", cbxTo);

		// *************** IMAGES ***************

		JLabel imgFrom = new JLabel("");
		imgFrom.setBounds(41, 260, 55, 55);
		changeIcon(imgFrom, "flag/"+cbxFrom.getSelectedItem().toString(), 55);

		JLabel imgTo = new JLabel("");
		imgTo.setBounds(358, 260, 55, 55);
		changeIcon(imgTo, "flag/"+cbxTo.getSelectedItem().toString(), 55);

		JLabel imgExchange = new JLabel("");
		imgExchange.setBounds(276, 270, 35, 35);
		changeIcon(imgExchange, "exchange", 25);

		// *************** BUTTON ***************

		Button btnExchange = new Button();
		btnExchange.setBounds(41, 420, 497, 60);
		btnExchange.setText("Get Exchange Rate");
		btnExchange.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
		btnExchange.setBackground(new Color(29, 162, 253));
		btnExchange.setForeground(new Color(245, 245, 245));
		btnExchange.setRippleColor(new Color(255, 255, 255));
		btnExchange.setShadowColor(new Color(29, 162, 253));
		btnExchange.setFocusable(false);
		
		
		ImageIcon ic = new ImageIcon(new ImageIcon("icons/icon.png").getImage().getScaledInstance(21,21, Image.SCALE_SMOOTH));
		
		ic = new ImageIcon(new ImageIcon("icons/degree.png").getImage().getScaledInstance(21,21, Image.SCALE_SMOOTH));
		
		ic = new ImageIcon(new ImageIcon("icons/distance.png").getImage().getScaledInstance(21,21, Image.SCALE_SMOOTH));
		
		ic = new ImageIcon(new ImageIcon("icons/weight.png").getImage().getScaledInstance(21,21, Image.SCALE_SMOOTH));
		
		ic = new ImageIcon(new ImageIcon("icons/ENG.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));
		
		ic = new ImageIcon(new ImageIcon("icons/ES.png").getImage().getScaledInstance(30,30, Image.SCALE_SMOOTH));

		// *************** LISTENERS ***************

		cbxFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeIcon(imgFrom, "flag/"+cbxFrom.getSelectedItem().toString(), 55);
			}
		});

		cbxTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeIcon(imgTo, "flag/"+cbxTo.getSelectedItem().toString(), 55);
			}
		});

		btnExchange.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (tfAmount.getText().isEmpty()) {
					tfAmount.setShadowColor(Color.red);
					
					errorMsg(lblError, "Enter an amount to convert");
				} else {
					Coin fromCoin = (Coin) cbxFrom.getSelectedItem();
					Coin toCoin = (Coin) cbxTo.getSelectedItem();
					double amount = Double.parseDouble(tfAmount.getText());

					DecimalFormat df = new DecimalFormat("#,###.####");

					double currencyConverter = (amount / fromCoin.getExchange()) * toCoin.getExchange();

					lblCurrencyConverter.setText(df.format(amount) + " " + fromCoin.getAbbreviation() + " = "
							+ df.format(currencyConverter) + " " + toCoin.getAbbreviation());
				}
			}

		});

		tfAmount.addKeyListener(new KeyListener() {
			boolean dotTyped = false;

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();

				if (c == '.' && !dotTyped) {
					dotTyped = true;
				} else if (!((c >= '0' && c <= '9') || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					errorMsg(lblError, "Only decimal numbers are alloweds");
					e.consume();
				}else{
					tfAmount.setShadowColor(Color.gray);
				}

				// LIMIT THE NUMBER OF DECIMAL PLACES TO 4
				String text = tfAmount.getText();
				if (text.contains(".") && text.substring(text.indexOf(".") + 1).length() >= 4) {
					errorMsg(lblError, "Only 4 decimal places are allowed");
					e.consume();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// RESET POINT COUNTER WHEN CONTENT IS DELETED
				if (tfAmount.getText().isEmpty() || !tfAmount.getText().contains(".")) {
					dotTyped = false;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});

		// *************** ADD CONTENT ***************

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(lblTitleApp);
		contentPane.add(lblAmount);
		contentPane.add(lblFrom);
		contentPane.add(lblTo);
		contentPane.add(lblCurrencyConverter);
		contentPane.add(lblError);
		contentPane.add(tfAmount);
		contentPane.add(cbxTo);
		contentPane.add(cbxFrom);
		contentPane.add(imgTo);
		contentPane.add(imgFrom);
		contentPane.add(imgExchange);
		contentPane.add(btnExchange);
	}

	private void changeIcon(JLabel img, String path, int x) {
		ImageIcon icon = new ImageIcon(
				new ImageIcon("icons/" + path + ".png").getImage().getScaledInstance(x, x, Image.SCALE_SMOOTH));
		img.setIcon(icon);
		contentPane.requestFocus();
	}

	public static void selectedCbxByAbrv(List<Coin> coinList, String abrv, ComboBox<Coin> cbx) {
		for (Coin coin : coinList) {
			if (coin.getAbbreviation().equals(abrv)) {
				cbx.setSelectedItem(coin);
				break;
			}
		}
	}

	private void errorMsg(JLabel label, String msg) {
		label.setText(msg);
		if (timerStarted) {
			timer.stop();
		}
		timer = new Timer(45, new ActionListener() {
			private float alpha = 1.0f; // *** INITIAL OPACITY ***

			@Override
			public void actionPerformed(ActionEvent e) {
				alpha -= 0.02f; // *** GRADUALLY DECREASE OPACITY ***

				if (alpha <= 0.0f) {
					((Timer) e.getSource()).stop(); // *** STOP TIMER ***
					alpha = 0f;
					label.setText("");
					timerStarted = false;
				}

				label.setForeground(new Color(1, 0, 0, alpha)); // *** SET NEW OPACITY ***
			}
		});

		timer.start();
		timerStarted = true;
	}

}
