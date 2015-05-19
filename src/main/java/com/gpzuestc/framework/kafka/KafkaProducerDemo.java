package com.gpzuestc.framework.kafka;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class KafkaProducerDemo {
	public static void main(String[] args) {
//		long events = Long.parseLong(args[0]);
		long events = 20;
		Random rnd = new Random();

		Properties props = new Properties();
		props.put("metadata.broker.list",
				"10.10.22.68:9092,10.10.22.68:9093,10.10.22.68:9094");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "com.gpzuestc.framework.kafka.SimplePartitioner");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		for (long nEvents = 0; nEvents < events; nEvents++) {
			long runtime = new Date().getTime();
			String ip = "192.168.2." + rnd.nextInt(255);
			String msg = runtime + ",www.example.com," + ip;
			KeyedMessage<String, String> data = new KeyedMessage<String, String>(
					"cluster", ip, msg);
			producer.send(data);
		}
		producer.close();
	}
}

