package samples;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JavaComponent implements Runnable {

	public void run() {
		try {
			ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection = factory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("java2ruby");

			MessageProducer producer = session.createProducer(destination);
			
			for (int i = 1; i <= 10; i++) {
				Thread.sleep(1000);
				TextMessage message = session.createTextMessage("message #" + i);
				producer.send(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws JMSException, InterruptedException {
		System.out.println("java component is up and running ...");
		new Thread(new JavaComponent()).start();
	}

}
