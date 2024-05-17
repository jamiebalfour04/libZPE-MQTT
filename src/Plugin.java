import jamiebalfour.zpe.interfaces.ZPECustomFunction;
import jamiebalfour.zpe.interfaces.ZPELibrary;
import org.fusesource.mqtt.client.MQTT;

import java.util.HashMap;
import java.util.Map;

public class Plugin implements ZPELibrary {

  @Override
  public ZPECustomFunction[] functions() {
    return new ZPECustomFunction[0];
  }

  @Override
  public Map<String, Class<?>> objects() {
    Map<String, Class<?>> m = new HashMap<>();
    m.put("mqtt_object", MQTTObject.class);
  }

  @Override
  public String GetName() {
    return "libMQTT";
  }

  @Override
  public String GetVersionInfo() {
    return "1.0";
  }
}