

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import StreamTool;

public class ImageService
{
	public static byte[] getImage(String path)
	{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectionTimeout(5000);
		conn.setRequestMethod("GET");
		
		if(conn.getResponseCode==200)
		{
			InputStream inStream = conn.getInputStream();
			return StreamTool.read(inStream);
		}
		
		return null;
	}
}