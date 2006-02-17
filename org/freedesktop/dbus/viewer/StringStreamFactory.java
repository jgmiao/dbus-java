package org.freedesktop.dbus.viewer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.freedesktop.dbus.CreateInterface.PrintStreamFactory;

/**
 * A factory using a byte array input stream
 * 
 * 
 * @author pete
 * @since 10/02/2006
 */
final class StringStreamFactory extends PrintStreamFactory
{
	Map<String, ByteArrayOutputStream> streamMap = new HashMap<String, ByteArrayOutputStream>();

	/** {@inheritDoc} */
	public void init(String file, String path)
	{

	}

	/** {@inheritDoc} */
	@SuppressWarnings("unused")
	public PrintStream createPrintStream(final String file) throws IOException
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		streamMap.put(file, stream);
		return new PrintStream(stream);

	}
}