import java.awt.Color;
import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
//import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class mainwindow {

	private String default_url = "google.com";
	private JFrame frmPngLook;
	boolean running = false;
	int i = 1;
	private JTextField url;
	String ip = "";
	int min;
	int max;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainwindow window = new mainwindow();
					window.frmPngLook.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the application.
	 */
	public mainwindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmPngLook = new JFrame();
		frmPngLook.setResizable(false);
		frmPngLook.setTitle("P1NG Trak");
		frmPngLook.setBounds(100, 100, 320, 136);
		frmPngLook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPngLook.getContentPane().setLayout(null);
		try {
			frmPngLook.setIconImage(ImageIO.read(new File("icon.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(UIManager.getColor("ProgressBar.background"));
		progressBar.setMaximum(360);
		progressBar.setBounds(10, 45, 222, 23);
		frmPngLook.getContentPane().add(progressBar);

		JButton btn = new JButton("Start");
		btn.setBounds(242, 13, 62, 23);
		frmPngLook.getContentPane().add(btn);

		url = new JTextField();
		url.setText(default_url);
		url.setBounds(10, 14, 222, 20);
		frmPngLook.getContentPane().add(url);
		url.setColumns(10);

		JLabel status = new JLabel("Ready.");

		status.setFont(new Font("Tahoma", Font.PLAIN, 10));
		status.setBounds(10, 71, 294, 14);
		frmPngLook.getContentPane().add(status);

		JLabel t_avg = new JLabel("-ms");
		t_avg.setForeground(Color.ORANGE);
		t_avg.setHorizontalAlignment(SwingConstants.TRAILING);
		t_avg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		t_avg.setBounds(35, 88, 32, 10);
		frmPngLook.getContentPane().add(t_avg);

		JLabel t_min = new JLabel("-ms");
		t_min.setForeground(new Color(60, 179, 113));
		t_min.setHorizontalAlignment(SwingConstants.TRAILING);
		t_min.setFont(new Font("Tahoma", Font.PLAIN, 9));
		t_min.setBounds(102, 88, 32, 10);
		frmPngLook.getContentPane().add(t_min);

		JLabel t_max = new JLabel("-ms");
		t_max.setForeground(Color.RED);
		t_max.setHorizontalAlignment(SwingConstants.TRAILING);
		t_max.setFont(new Font("Tahoma", Font.PLAIN, 9));
		t_max.setBounds(170, 88, 32, 10);
		frmPngLook.getContentPane().add(t_max);

		JLabel lblAvg = new JLabel("AVG.");
		lblAvg.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblAvg.setBounds(10, 88, 25, 10);
		frmPngLook.getContentPane().add(lblAvg);

		JLabel lblMin = new JLabel("MIN.");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMin.setBounds(78, 88, 25, 10);
		frmPngLook.getContentPane().add(lblMin);

		JLabel lblMax = new JLabel("MAX.");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblMax.setBounds(144, 88, 25, 10);
		frmPngLook.getContentPane().add(lblMax);

		JLabel time_dis = new JLabel("-");
		time_dis.setVerticalAlignment(SwingConstants.BOTTOM);
		time_dis.setHorizontalAlignment(SwingConstants.TRAILING);
		time_dis.setFont(new Font("Tahoma", Font.PLAIN, 24));
		time_dis.setBounds(242, 45, 48, 23);
		frmPngLook.getContentPane().add(time_dis);

		JLabel lblms = new JLabel("ms");
		lblms.setVerticalAlignment(SwingConstants.BOTTOM);
		lblms.setHorizontalAlignment(SwingConstants.TRAILING);
		lblms.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblms.setBounds(289, 45, 15, 23);
		frmPngLook.getContentPane().add(lblms);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Always on top");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 10));
		chckbxNewCheckBox.setHorizontalTextPosition(SwingConstants.LEFT);
		chckbxNewCheckBox.setHorizontalAlignment(SwingConstants.TRAILING);
		chckbxNewCheckBox.setBounds(208, 82, 100, 23);
		frmPngLook.getContentPane().add(chckbxNewCheckBox);
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (chckbxNewCheckBox.isSelected()) {
					frmPngLook.setAlwaysOnTop(true);
				} else {
					frmPngLook.setAlwaysOnTop(false);
				}
			}
		});

		btn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if (url.getText().contains(" ") || url.getText().startsWith("-")
						|| url.getText().startsWith("/")) {
					status.setText("Invalid URL!");
				} 
				else if (url.getText().isEmpty()){
					status.setText("Address must be specified.");
				}
				else {
					Thread doRefresh = new Thread() {
						public void run() {
							try {
								status.setToolTipText(null);
								min = 1000;
								max = 0;
								Process p;
								if (System.getProperty("os.name").toLowerCase()
										.contains("windows")) {
									p = Runtime.getRuntime().exec(
											"ping " + url.getText()
													+ " -t -w 1000");
								} else {
									p = Runtime.getRuntime()
											.exec("ping " + "-w 1000"
													+ url.getText());
								}

								BufferedReader inputStream = new BufferedReader(
										new InputStreamReader(p
												.getInputStream()));

								String s = "";
								int times = 0;
								long totalms = 0;

								// reading output stream of the command
								while (((s = inputStream.readLine()) != null && i == 1)) {
									try {
										ip = s.split(": ")[0].split(" ")[2];
										try {
											s = s.split("time=")[1].split("ms")[0];
										} catch (Exception e) {
											// TODO Auto-generated catch block
											s = s.split("time<")[1].split("ms")[0];
										}
										// toGetPing-----------------
										status.setText("Pinging " + ip);
										time_dis.setText(s);
										progressBar.setBackground(UIManager
												.getColor("ProgressBar.background"));
										progressBar.setValue(361 - (int) Double
												.parseDouble(s));
										if (Integer.parseInt(s) <= 50) {
											progressBar
													.setForeground(new Color(
															60, 179, 113));
											time_dis.setForeground(new Color(
													60, 179, 113));
										} else if (Integer.parseInt(s) <= 100) {
											progressBar
													.setForeground(new Color(
															154, 205, 0));
											time_dis.setForeground(new Color(
													154, 205, 0));
										} else if (Integer.parseInt(s) <= 180) {
											progressBar
													.setForeground(new Color(
															255, 215, 0));
											time_dis.setForeground(new Color(
													255, 215, 0));
										} else if (Integer.parseInt(s) <= 250) {
											progressBar
													.setForeground(Color.orange);
											time_dis.setForeground(Color.orange);
										} else if (Integer.parseInt(s) < 360) {
											progressBar
													.setForeground(Color.red);
											time_dis.setForeground(Color.red);
										} else if (Integer.parseInt(s) >= 360) {
											progressBar
													.setForeground(new Color(
															180, 0, 0));
											time_dis.setForeground(new Color(
													180, 0, 0));
										}
										status.setToolTipText(null);
										totalms += (long) Double.parseDouble(s);
										times++;
										t_avg.setText(totalms / times + "ms");
										if (Double.parseDouble(s) < min) {
											min = (int) Double.parseDouble(s);
											t_min.setText(s + "ms");
										}
										if (Double.parseDouble(s) > max) {
											max = (int) Double.parseDouble(s);
											t_max.setText(s + "ms");
										}
										// --------------------------
									} catch (Exception e) {
										// TODO Auto-generated catch block
										status.setText(s);
										time_dis.setText("-");
										time_dis.setForeground(null);
										if (s.length() > 70) {
											status.setToolTipText(s);
										}
										progressBar.setValue(0);
										if (s.contains("try again")) {

											i = 0;
											btn.setText("Start");
											running = false;
											progressBar.setValue(0);
											url.setEnabled(!running);
											p.destroy();
											break;

										}

									}
								}
								p.destroy();

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					};
					if (!running) {
						i = 1;
						status.setText("Connecting...");
						progressBar.setBackground(UIManager
								.getColor("ProgressBar.background"));
						doRefresh.start();
						btn.setText("Stop");
						running = true;
						url.setEnabled(!running);
					} else {
						i = 0;
						doRefresh.interrupt();
						btn.setText("Start");
						running = false;
						progressBar.setValue(0);
						progressBar.setBackground(UIManager
								.getColor("ProgressBar.background"));
						status.setText("Ready.");
						url.setEnabled(!running);
						time_dis.setText("-");
						time_dis.setForeground(null);
					}

				}
			}
		});
		frmPngLook.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (running) {
					frmPngLook.setVisible(false);
					btn.doClick();
					frmPngLook.setTitle("P1NG Trak (Stopping)");
					try {
						TimeUnit.MILLISECONDS.sleep(1000);
					} catch (InterruptedException e) {
						// Handle exception
					}

				}
			}
		});

	}
}
