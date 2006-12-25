package org.freedesktop.dbus.test;
import cx.ath.matthew.debug.Debug;
import cx.ath.matthew.utils.Hexdump;
import org.freedesktop.dbus.BusAddress;
import org.freedesktop.dbus.DBusSignal;
import org.freedesktop.dbus.Message;
import org.freedesktop.dbus.MethodCall;
import org.freedesktop.dbus.Transport;

public class test_low_level
{
   public static void main(String[] args) throws Exception
   {
      Message test = new MethodCall(":1.0", "/", "org.foo", "Hiii", (byte) 0, null);

      //System.exit(0);

      Debug.setHexDump(true);
      BusAddress address = new BusAddress(System.getenv("DBUS_SESSION_BUS_ADDRESS"));
      Debug.print(address);

      Transport conn = new Transport(address);

      Message m = new MethodCall("org.freedesktop.DBus", "/org/freedesktop/DBus", "org.freedesktop.DBus", "Hello", (byte) 0, null);
      conn.mout.writeMessage(m);
      m = conn.min.readMessage();
      Debug.print(m.getClass());
      Debug.print(m);
      m = conn.min.readMessage();
      Debug.print(m.getClass());
      Debug.print(m);
      m = new MethodCall("org.freedesktop.DBus", "/", null, "Hello", (byte) 0, null);
      conn.mout.writeMessage(m);
      m = conn.min.readMessage();
      Debug.print(m);

      m = new MethodCall("org.freedesktop.DBus", "/org/freedesktop/DBus", "org.freedesktop.DBus", "RequestName", (byte) 0, "s", "org.testname");
      conn.mout.writeMessage(m);
      m = conn.min.readMessage();
      Debug.print(m);
      m = new DBusSignal("/foo", "org.foo", "Foo", null);
      conn.mout.writeMessage(m);
      m = conn.min.readMessage();
      Debug.print(m);
   }
}