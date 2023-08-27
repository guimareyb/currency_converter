import java.awt.EventQueue;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

	public static void main(String[] args) throws URISyntaxException {

		//*************** READ JSON FILE PATH ***************
		Main reader = new Main();
		File file = reader.getFileFromResource("divisas.json");
		ObjectMapper objectMapper = new ObjectMapper();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//*************** INITIALIZE THE LIST WITH THE DATA FROM THE JSON FILE ***************
					List<Coin> coins = objectMapper.readValue(file, new TypeReference<List<Coin>>() {});
					
					//*************** SHOW FRAME ***************
					FrmMain frame = new FrmMain(coins);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	//*************** GET FILE PATH ***************
	private File getFileFromResource(String file) throws URISyntaxException {
		ClassLoader charger = getClass().getClassLoader();
		URL resource = charger.getResource(file);
		if (resource == null) {
			throw new IllegalArgumentException("File not found: " + file);
		} else {
			return new File(resource.toURI());
		}
	}

}
