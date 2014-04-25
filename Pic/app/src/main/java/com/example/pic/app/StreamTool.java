package com.example.pic.app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool
{
    public static byte[] read(InputStream inStream) throws Exception
	{
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;

		while(len=inStream.read(buffer) !=-1)
		{
			outputStream.write(buffer,0,len);
		}
		
		byte[] data = outputStream.toByteArray();
		outputStream.close();
		inStream.close();
		return data;
	}
}