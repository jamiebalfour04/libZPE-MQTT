
import java.util.HashMap;

import jamiebalfour.HelperFunctions;
import jamiebalfour.zpe.core.ZPERuntimeEnvironment;
import jamiebalfour.zpe.core.ZPEObject;
import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPEPropertyWrapper;

import org.fusesource.mqtt.client.*;

public class MQTTObject extends ZPEStructure {
  MQTT mqtt = new MQTT();
  BlockingConnection connection = null;

  private static final long serialVersionUID = -9192255670493429584L;

  private void addMethods() {
    addNativeMethod("connect", new connect_Command());
    addNativeMethod("publish", new publish_Command());
  }

  public MQTTObject(ZPERuntimeEnvironment z, ZPEPropertyWrapper parent) {
    super(z, parent, "MQTTObject");
    addMethods();
  }

  public MQTTObject(ZPERuntimeEnvironment z, ZPEPropertyWrapper parent, String name) {
    super(z, parent, "MQTTObject");
    addMethods();
  }

  class connect_Command implements jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod {

    @Override
    public String[] ParameterNames() {
      String[] n = new String[4];
      n[0] = "ip";
      n[1] = "port";
      n[2] = "username";
      n[3] = "password";
      return n;
    }

    @Override
    public Object MainMethod(HashMap<String, Object> parameters, ZPEObject parent) {

      try {

        mqtt.setHost(parameters.get("ip").toString(), HelperFunctions.StringToInteger(parameters.get("port").toString()));
        mqtt.setUserName(parameters.get("username").toString());
        mqtt.setPassword(parameters.get("password").toString());


        connection = mqtt.blockingConnection();
        connection.connect();

        return true;
      } catch (Exception e) {
        return false;
      }
    }

    @Override
    public int RequiredPermissionLevel() {
      return 0;
    }

    public String name() {
      return "connect";
    }

  }

  class publish_Command implements jamiebalfour.zpe.interfaces.ZPEObjectNativeMethod {

    @Override
    public String[] ParameterNames() {
      String[] n = new String[2];
      n[0] = "topic";
      n[1] = "message";
      return n;
    }

    @Override
    public Object MainMethod(HashMap<String, Object> parameters, ZPEObject parent) {

      try {

        mqtt.setWillTopic(parameters.get("topic").toString());
        connection.publish(parameters.get("topic").toString(), parameters.get("message").toString().getBytes(), QoS.AT_LEAST_ONCE, false);

        return true;
      } catch (Exception e) {
        return false;
      }
    }

    @Override
    public int RequiredPermissionLevel() {
      return 0;
    }

    public String name() {
      return "publish";
    }

  }
}
