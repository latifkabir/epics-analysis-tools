// Filename: PvaClientDemo.java
// Description: 
// Author: Latif Kabir < latifkabir.github.io >

import org.epics.pva.client.PVAClient;
import org.epics.pva.client.PVAChannel;
import org.epics.pva.data.PVAStructure;
import java.util.concurrent.TimeUnit;

public class PvaClientDemo
{
    public static void main(String[] args) throws Exception
    {
	try
	{
	    PVAClient client = new PVAClient();
	    PVAChannel channel = client.getChannel("demo:circle:x");
	    channel.connect().get(5, TimeUnit.SECONDS);
	    PVAStructure value = channel.read("").get(5, TimeUnit.SECONDS);
	    System.out.println(channel.getName() + " = " + value);
	    channel.close();
	    client.close();
	}
	finally
	{
	    //
	}
    }
}
