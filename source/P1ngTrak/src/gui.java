import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class gui {

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
					gui window = new gui();
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
	public gui() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
//		Pinging p = new Pinging();
//		p.setIP("facebook.com");
//		p.start();
		frmPngLook = new JFrame();
		frmPngLook.setResizable(false);
		frmPngLook.setTitle("P1NG Trak");
		frmPngLook.setBounds(100, 100, 320, 150);
		frmPngLook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPngLook.getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBackground(UIManager.getColor("ProgressBar.background"));
		progressBar.setMinimum(0);
		progressBar.setMaximum(320);
		progressBar.setBounds(10, 45, 294, 23);
		frmPngLook.getContentPane().add(progressBar);
		
		JButton btn = new JButton("Start");
		btn.setBounds(242, 14, 62, 20);
		frmPngLook.getContentPane().add(btn);
		
		url = new JTextField();
		url.setText("google.com");
		url.setBounds(10, 14, 222, 20);
		frmPngLook.getContentPane().add(url);
		url.setColumns(10);
		
		JLabel status = new JLabel("Ready.");
		
		status.setFont(new Font("Tahoma", Font.PLAIN, 11));
		status.setBounds(10, 74, 294, 14);
		frmPngLook.getContentPane().add(status);
		
		JLabel t_avg = new JLabel("-ms");
		t_avg.setForeground(new Color(255, 140, 0));
		t_avg.setHorizontalAlignment(SwingConstants.TRAILING);
		t_avg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		t_avg.setBounds(42, 99, 40, 14);
		frmPngLook.getContentPane().add(t_avg);
		
		JLabel t_min = new JLabel("-ms");
		t_min.setForeground(new Color(0, 128, 0));
		t_min.setHorizontalAlignment(SwingConstants.TRAILING);
		t_min.setFont(new Font("Tahoma", Font.PLAIN, 10));
		t_min.setBounds(124, 99, 40, 14);
		frmPngLook.getContentPane().add(t_min);
		
		JLabel t_max = new JLabel("-ms");
		t_max.setForeground(Color.RED);
		t_max.setHorizontalAlignment(SwingConstants.TRAILING);
		t_max.setFont(new Font("Tahoma", Font.PLAIN, 10));
		t_max.setBounds(206, 99, 40, 14);
		frmPngLook.getContentPane().add(t_max);
		
		JLabel lblAvg = new JLabel("AVG.");
		lblAvg.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblAvg.setBounds(10, 99, 32, 14);
		frmPngLook.getContentPane().add(lblAvg);
		
		JLabel lblMin = new JLabel("MIN.");
		lblMin.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMin.setBounds(92, 99, 32, 14);
		frmPngLook.getContentPane().add(lblMin);
		
		JLabel lblMax = new JLabel("MAX.");
		lblMax.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMax.setBounds(174, 99, 32, 14);
		frmPngLook.getContentPane().add(lblMax);
		        
		btn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				
				
				Thread doRefresh = new Thread() {
					public void run() {
						try {
							status.setToolTipText(null);
							min = 1000;
							max = 0;
							Process p = Runtime.getRuntime().exec(
									"ping " + url.getText() + " -t -w 1000");
							BufferedReader inputStream = new BufferedReader(
									new InputStreamReader(p.getInputStream()));

							String s = "";
							int times = 0;
							long totalms = 0;

							// reading output stream of the command
							while ((s = inputStream.readLine()) != null
									&& i == 1) {
								try {
									ip = s.split(" ")[2].split(":")[0];
									try {
										s = s.split("time=")[1].split("ms")[0];
									} catch (Exception e) {
										// TODO Auto-generated catch block
										s = s.split("time<")[1].split("ms")[0];
									}
									// toGetPing-----------------
									// System.out.println(s);
									status.setText("[Pinging " + ip + "] " + s
											+ "ms");
									// ping = Integer.parseInt(s);
									progressBar.setBackground(UIManager
											.getColor("ProgressBar.background"));
									progressBar.setValue(Integer.parseInt(s));
									if (Integer.parseInt(s) <= 80) {
										progressBar.setForeground(Color.green);
									} else if (Integer.parseInt(s) <= 160) {
										progressBar.setForeground(Color.yellow);
									} else if (Integer.parseInt(s) <= 240) {
										progressBar.setForeground(Color.orange);
									} else if (Integer.parseInt(s) < 320) {
										progressBar.setForeground(Color.red);
									} else if (Integer.parseInt(s) >= 320) {
										progressBar.setForeground(new Color(128,0,0));
									}
									status.setToolTipText(null);
									totalms += Long.parseLong(s);
									times++;
									t_avg.setText(totalms/times+"ms");
									if(Integer.parseInt(s)<min){
										min = Integer.parseInt(s);
										t_min.setText(s+"ms");
									}
									if(Integer.parseInt(s)>max){
										max = Integer.parseInt(s);
										t_max.setText(s+"ms");
									}
									// --------------------------
								} catch (Exception e) {
									// TODO Auto-generated catch block
									status.setText(s);
									if(s.length()>70){
										status.setToolTipText(s);
									}
									progressBar.setValue(0);
									progressBar.setBackground(new Color(255,
											102, 102));
									if (s.contains("try again")) {

										i = 0;
										btn.setText("Start");
										running = false;
										progressBar.setValue(0);

										url.setEnabled(!running);
										break;
									}

								}
							}

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
					//t_min.setText("-ms");
					//t_max.setText("-ms");
				}

			}});
				
	}
}
