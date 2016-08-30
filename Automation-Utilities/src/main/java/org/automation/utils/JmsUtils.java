

import com.google.common.base.Joiner;
import com.google.common.base.Throwables;
import com..datait.dist.orator.linguamapping.MessageType;
import com..datait.dist.orator.linguamapping.MessageVersion;
import com..lingua.businessstructure.SystemHostRegion;
import oracle.jms.AQjmsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;

import static com.google.common.base.Ascii.toLowerCase;
import static javax.naming.Context.*;


public final class JmsUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FunctionalTestUtils.class);
    private static final String SELECTOR_PREFIX = "selector";
    private static final String ORCA_JNDI_PROVIDER_URL_KEY = "orca.jndi.providerUrl";
    private static final String CONNECTION_FACTORY_KEY = "subscriber.connectionfactory";
    private static final String USERNAME_KEY = "subscriber.username";
    private static final String PASSWORD_KEY = "subscriber.password";
    public static final String CONTEXT_PACKAGE = "com.tibco.tibjms.naming";
    public static final String CONTEXT_FACTORY = CONTEXT_PACKAGE + ".TibjmsInitialContextFactory";
    public static final Joiner dotJoiner = Joiner.on(".");

    public static Hashtable<String, Object> createContextConfig(Properties properties) {
        Hashtable<String, Object> contextConfig = new Hashtable<>();

        contextConfig.put(PROVIDER_URL, properties.getProperty(ORCA_JNDI_PROVIDER_URL_KEY));
        contextConfig.put(INITIAL_CONTEXT_FACTORY, CONTEXT_FACTORY);
        contextConfig.put(URL_PKG_PREFIXES, CONTEXT_PACKAGE);
        return contextConfig;
    }

    public static Connection getConnection(Properties properties, Hashtable<String, Object> contextConfig) throws NamingException, JMSException {
        ConnectionFactory factory = (ConnectionFactory) new InitialContext(contextConfig).lookup(properties.getProperty(CONNECTION_FACTORY_KEY));
        //System.out.println(factory.toString());
        for(String item : contextConfig.keySet()){
            System.out.println("contextConfig : "+item+" >> "+contextConfig.get(item));
        }
        for(Object item : properties.keySet()){
            System.out.println("properties : "+item.toString()+" >> "+properties.get(item));
        }
        //factory =  new InitialContext()
        System.out.println("factory --- >> "+factory.toString());
        return factory.createConnection(properties.getProperty(USERNAME_KEY), properties.getProperty(PASSWORD_KEY));
    }

    public static String getDestinationName(MessageType messageType, MessageVersion version, Properties props) {
        String destinationNameKey = dotJoiner.join(messageType.getProtocol(), toLowerCase(version.toString()));
        String destinationName = props.getProperty(destinationNameKey);
        if (null == destinationName) {
            LOG.error("Current properties:\n {}", props);
            throw new RuntimeException("Missing destination name (JMS topic name) for property key: " + destinationNameKey);
        }
        return destinationName;
    }

    public static String getJmsSelectorName(MessageType messageType, MessageVersion version, Properties props) {
        String destinationNameKey = dotJoiner.join(SELECTOR_PREFIX, messageType.toString());
        return props.getProperty(destinationNameKey);
    }

    public static MessageConsumer createTopicConsumer(
            Session session, String destinationName,
            String jmsSelector,
            Hashtable<String, Object> contextConfig
    ) throws NamingException, JMSException {
        return createTopicConsumer(session, destinationName, jmsSelector, null, contextConfig);
    }

    public static MessageConsumer createTopicConsumer(
            Session session,
            String destinationName,
            String jmsSelector,
            String durable,
            Hashtable<String, Object> contextConfig
    ) throws NamingException, JMSException {
        Topic destination;
        try {
            destination = (Topic) new InitialContext(contextConfig).lookup(destinationName);
        } catch (NameNotFoundException e) {
            LOG.error(" {}  {}  {}  {}  {}. ", new Object[]{session, destinationName, jmsSelector, durable, contextConfig});
            throw Throwables.propagate(e);
        }


        MessageConsumer consumer;
        if (!FunctionalTestUtils.isEmptyString(durable)) {
            if (!FunctionalTestUtils.isEmptyString(jmsSelector)) {
                consumer = session.createDurableSubscriber(destination, durable, jmsSelector, false);
            } else {
                consumer = session.createDurableSubscriber(destination, durable);
            }
        } else {
            if (!FunctionalTestUtils.isEmptyString(jmsSelector)) {
                consumer = session.createConsumer(destination, jmsSelector);
            } else {
                consumer = session.createConsumer(destination);
            }
        }
        return consumer;
    }


    public static Session getSession(Connection jmsConnection) throws JMSException {
        return jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static void closeConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (JMSException ignore) {
                LOG.debug("connection close failed");
            }
        }
    }

    public static void closeSession(Session session) {
        if (null != session) {
            try {
                session.close();
            } catch (JMSException ignore) {
                LOG.debug("session close failed");
            }
        }
    }

    public static void closePublisher(MessageProducer publisher) {
        if (null != publisher) {
            try {
                publisher.close();
            } catch (JMSException ignore) {
                LOG.debug("publisher close failed");
            }
        }
    }
}
