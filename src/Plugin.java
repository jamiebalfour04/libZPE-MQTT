import jamiebalfour.zpe.core.ZPEStructure;
import jamiebalfour.zpe.interfaces.ZPECustomFunction;
import jamiebalfour.zpe.interfaces.ZPELibrary;
import org.fusesource.mqtt.client.MQTT;

import java.util.HashMap;
import java.util.Map;

public class Plugin implements ZPELibrary {

  @Override
  public Map<String, ZPECustomFunction> getFunctions() {
    return new HashMap<>();
  }

  @Override
  public Map<String, Class<? extends ZPEStructure>> getObjects() {
    Map<String, Class<? extends ZPEStructure>> m = new HashMap<>();
    m.put("mqtt_object", MQTTObject.class);

    return m;
  }

  @Override
  public String getName() {
    return "libZPE-MQTT";
  }

  @Override
  public String getVersionInfo() {
    return "1.0";
  }
}